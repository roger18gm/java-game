package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.security.Key;

//import java.lang.classfile.attribute.CharacterRangeTableAttribute;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private Character soldier;
    private MovePerson movePerson;  // Add a MovePerson instance
    private Background background;

    @Override
    public void create() {
        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
//        FileHandle solderWalkFile = Gdx.files.internal("assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png");
        soldier = new Character(world, "characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Walk.png", 100, 50, 6);
        background = new Background(world);

        // Create MovePerson instance and pass the soldierWalking character to it
        movePerson = new MovePerson(soldier);
        Gdx.input.setInputProcessor(movePerson);
    }

    @Override
    public void render() {

        float deltaTime = Gdx.graphics.getDeltaTime();

        // Call MovePerson's Move() method to update the character's position
        movePerson.update(deltaTime);

        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        batch.begin();
        background.render(batch);
        // Render the current character depending on the state (idle or walking)
        soldier.render(batch);
        batch.end();
        world.step(1/60f, 6, 2); // Step the physics world
        debugRenderer.render(world, batch.getProjectionMatrix());
    }

    @Override
    public void dispose() {
        batch.dispose();
        soldier.dispose();
        background.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
}
