package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
    private Sound sound;
    private Music music;
//    private NPC npc;

    // The default is that the soldier hasn't attacked yet
    private boolean attackSoundPlayed = false;

    @Override
    public void create() {

        // UI components
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
//        skin = new Skin(Gdx.files.internal("ui/pixthulhuui/pixthulhu-ui.json"));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        TextButton playButton = new TextButton("Play", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
//        FileHandle solderWalkFile = Gdx.files.internal("assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png");
        soldier = new Character(world, "characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png", 100, 50, 6);
        background = new Background(world);

        // Create MovePerson instance and pass the soldierWalking character to it
        movePerson = new MovePerson(soldier);
//        Gdx.input.setInputProcessor(movePerson);

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

        // Default background color
        ScreenUtils.clear(0.08f, 0.07f, 0.23f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        if (Gdx.input.getInputProcessor() == stage) {
            // Draw the stage
            stage.act();
            stage.draw();
//            return;
        }
        else{
            // Plays sword sound only once per click
            if (movePerson.isAttacking() && !attackSoundPlayed) {
                sound.play(1.0f); // Play sound only once per click
                attackSoundPlayed = true; // Because the soldier has attacked once it won't repeat the sound
            } else if (!movePerson.isAttacking()) {
                attackSoundPlayed = false; // Reset so sound can play on the next attack
            }

            float deltaTime = Gdx.graphics.getDeltaTime();
            // Call MovePerson's Move() method to update the character's position
            movePerson.update(deltaTime);
            batch.begin();
            background.render(batch);
            // Render the current character depending on the state (idle or walking)
            soldier.render(batch);
            batch.end();
            world.step(1/60f, 6, 2); // Step the physics world
            debugRenderer.render(world, batch.getProjectionMatrix());
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        soldier.dispose();
        background.dispose();
        world.dispose();
        debugRenderer.dispose();
        music.dispose();
        sound.dispose();
    }
}
