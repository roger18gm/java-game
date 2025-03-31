package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private Character soldier;
    private MovePerson movePerson;  // Add a MovePerson instance
    private Background background;
    private NPC npc;
    private MoveNPC moveNPC;
//    private Character orc;
//    private Enemy enemy;
    private Sound sound;
    private Music music;

    // The default is that the soldier hasn't attacked yet
    private boolean attackSoundPlayed = false;

    @Override
    public void create() {

        // UI components
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("ui/skin/plain-james-ui.json"));
//        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        TextButton playButton = new TextButton("Play", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // Add the MovePerson and Enemy InputProcessors to the InputMultiplexer
//                InputMultiplexer inputMultiplexer = new InputMultiplexer();
//
//                inputMultiplexer.addProcessor(movePerson);
//                inputMultiplexer.addProcessor(enemy);
//                Gdx.input.setInputProcessor(inputMultiplexer);
//
//
//                Gdx.app.log("Main", "InputProcessors added: MovePerson & Enemy");
                // Start the game
                startGame();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Exit the game
                Gdx.app.exit();
            }
        });

        // Create a table to organize the UI elements
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(playButton).padBottom(20).row();
        table.add(exitButton);

        // Add the table to the stage
        stage.addActor(table);

        // physics
        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        //FileHandle solderWalkFile = Gdx.files.internal("assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png");
        // Create MovePerson instance and pass the soldierWalking character to it
        soldier = new Character(world, "characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Walk.png", 100, 50, 6);
        movePerson = new MovePerson(soldier);

        background = new Background(world);

//        orc = new Character(world, "characters\\Characters(100x100)\\Orc\\Orc\\Orc-Idle.png", 200, 200, 6);
//        enemy = new Enemy(orc);


        npc = new NPC(world, "characters\\Characters(100x100)\\Orc\\Orc\\Orc-Walk.png", 200, 200, 8, soldier);
        moveNPC = new MoveNPC(npc);

        // Music
        sound = Gdx.audio.newSound((Gdx.files.internal("07_human_atk_sword_2.mp3")));
        music = Gdx.audio.newMusic(Gdx.files.internal("Sketchbook 2024-11-07.ogg"));;

        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

    }
    void startGame() {
        // Start the game
        System.out.println("Game started!");
        stage.clear(); // Clear the stage


        Gdx.input.setInputProcessor(movePerson); // Set the input processor to MovePerson
    }


    @Override
    public void render() {
        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        if (Gdx.input.getInputProcessor() == stage) {
            // Draw the stage
            stage.act();
            stage.draw();
//            return;
        } else {
            // Plays sword sound only once per click
            if (movePerson.isAttacking() && !attackSoundPlayed) {
                sound.play(1.0f); // Play sound only once per click
                attackSoundPlayed = true; // Because the soldier has attacked once it won't repeat the sound
            } else if (!movePerson.isAttacking()) {
                attackSoundPlayed = false; // Reset so sound can play on the next attack
            }
            // Call MovePerson's Move() method to update the character's position
            float deltaTime = Gdx.graphics.getDeltaTime();
            movePerson.update(deltaTime);
//            enemy.update(deltaTime);
//            THIS IS A NO-NO~~~~~~moveNPC.update(deltaTime);
//
//            if (movePerson.GetX() <= enemy.GetX() && enemy.attack) {
//                movePerson.damage = true;
//            } else {
//                movePerson.damage = false;
//            }

            batch.begin();
            npc.update(deltaTime);
            background.render(batch);
            soldier.render(batch);
//            orc.render(batch);
            npc.render(batch);
            batch.end();

            world.step(1 / 60f, 6, 2); // Step the physics world
            debugRenderer.render(world, batch.getProjectionMatrix());
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        soldier.dispose();
//        orc.dispose();
        background.dispose();
        world.dispose();
        debugRenderer.dispose();
        music.dispose();
        sound.dispose();
        npc.dispose();
    }
}
