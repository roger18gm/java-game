package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import java.sql.Struct;

//import java.lang.classfile.attribute.CharacterRangeTableAttribute;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private Character soldier;
    private MovePerson movePerson;  // Add a MovePerson instance
    private Background background;
    private Character orc;
    private Enemy enemy;


    @Override
    public void create() {
        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
//        FileHandle solderWalkFile = Gdx.files.internal("assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png");
        soldier = new Character(world, "characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Walk.png", 100, 50, 6, 3);
        background = new Background(world);
        orc = new Character(world, "characters\\Characters(100x100)\\Orc\\Orc\\Orc-Idle.png", 90, 100, 6, 3);
        // Create MovePerson instance and pass the soldierWalking character to it
        movePerson = new MovePerson(soldier);
        enemy = new Enemy(orc);



        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(movePerson);
        inputMultiplexer.addProcessor(enemy);
        Gdx.input.setInputProcessor(inputMultiplexer);


        Gdx.app.log("Main", "InputProcessors added: MovePerson & Enemy");
    }

    @Override
    public void render() {

        float deltaTime = Gdx.graphics.getDeltaTime();
        movePerson.update(deltaTime);
        enemy.update(deltaTime);

        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)


        // Move_person damage
        if (enemy.attack) {
            float distance = Math.abs(movePerson.GetX() - enemy.GetX());
            if (distance < 56) {
                if (movePerson.faceLeft && !enemy.faceLeft || !movePerson.faceLeft && enemy.faceLeft) {
                    movePerson.damage = true;
                }
            }
        } else {
            movePerson.damage = false;
        }

        // Simplified if statement for enemy damage
        if (movePerson.attack) {
            float distance = Math.abs(enemy.GetX() - movePerson.GetX());
            if (distance < 50) {
                if (enemy.faceLeft && !movePerson.faceLeft || !enemy.faceLeft && movePerson.faceLeft) {
                    enemy.damage = true;
                }
            }
        } else {
            enemy.damage = false;
        }


        batch.begin();

        background.render(batch);
        soldier.render(batch);
        orc.render(batch);
        batch.end();

        world.step(1/60f, 6, 2); // Step the physics world
        debugRenderer.render(world, batch.getProjectionMatrix());
    }

    @Override
    public void dispose() {
        batch.dispose();
        soldier.dispose();
        orc.dispose();
        background.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
}
