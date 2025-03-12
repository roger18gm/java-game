package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import io.github.roger18gm.java2dgame.entities.Player;
import io.github.roger18gm.java2dgame.movement.PlayerMovement;

//import java.lang.classfile.attribute.CharacterRangeTableAttribute;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    public Character soldierIdle;
    Texture soldierIdleTexture;
    Sprite soldierIdleSprite;
    Texture leftSoldierIdleTexture;
    Sprite leftSoldierIdleSprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        soldierIdleTexture = new Texture("C:\\Users\\Roger\\Desktop\\javagame\\assets\\Tiny RPG Character Asset Pack v1.03 -Free Soldier&Orc\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Idle.png");
        leftSoldierIdleTexture = soldierIdleTexture;

        soldierIdleSprite = new Sprite(soldierIdleTexture);
    }

    private void input() {
        float speed = 15f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            soldierIdleSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            soldierIdleSprite.translateX(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            soldierIdleSprite.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            soldierIdleSprite.translateY(-speed * delta);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(1, 0.75f, 0.80f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        soldierIdleSprite.draw(batch);
        batch.end();
    }
    @Override
    public void render() {

        input();
        draw();

//        float deltaTime = Gdx.graphics.getDeltaTime();
//
//        soldierIdle.update(deltaTime);
//
//
//        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)
//
//        batch.begin();
//        soldierIdle.render(batch);
//        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        soldierWalking.dispose();
        soldierIdle.dispose();
//        soldierHurt.dispose();
//        soldierDeath.dispose();

    }
}

