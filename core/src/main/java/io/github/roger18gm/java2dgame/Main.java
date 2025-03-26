package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import items.BaseItem;
import items.Inventory;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.security.Key;


/** {@link ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Character soldier;
    private MovePerson movePerson;  // Add a MovePerson instance
    private Background background;

    private Inventory inventory;
    public static boolean isPaused = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        soldier = new Character("C:\\Users\\austi\\IdeaProjects\\java-game\\assets\\characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Walk.png", 100, 50, 6);
        background = new Background();

        inventory = new Inventory();
        movePerson = new MovePerson(soldier);

        // Create MovePerson instance and pass the soldierWalking character to it
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(inventory.getStage());
        multiplexer.addProcessor(movePerson);
        Gdx.input.setInputProcessor(multiplexer);
//        InputProcessor stage = null;
//        Gdx.input.setInputProcessor(stage);
//        movePerson = new MovePerson(soldier);
//        Gdx.input.setInputProcessor(movePerson);
        isPaused = false;
    }

    @Override
    public void render() {

        float deltaTime = Gdx.graphics.getDeltaTime();
//        float delta = Gdx.graphics.getDeltaTime(); // Time between frames

        // Call MovePerson's Move() method to update the character's position

        if (!isPaused) {
            movePerson.update(deltaTime);
            background.update(deltaTime);
        }


        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        batch.begin();
        background.render(batch);
        // Render the current character depending on the state (idle or walking)
        soldier.render(batch);
        batch.end();

        inventory.render(deltaTime);


    }

    public static boolean isGamePaused() {;
        return isPaused;
    }

//    public static void setGamePaused (boolean paused) {
//        isPaused = paused;
//    }

    @Override
    public void dispose() {
        batch.dispose();
        soldier.dispose();
        background.dispose();
        inventory.dispose();
    }
}
