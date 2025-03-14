package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
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
public class Main extends Game {
    private SpriteBatch batch;
    public Character soldierIdle;
    Texture soldierIdleTexture;
    Sprite soldierIdleSprite;
    Texture leftSoldierIdleTexture;
    Sprite leftSoldierIdleSprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        FileHandle soldierImageFile = Gdx.files.internal("Tiny RPG Character Asset Pack v1.03 -Free Soldier&Orc\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Idle.png");
        soldierIdleTexture = new Texture(soldierImageFile);
        leftSoldierIdleTexture = soldierIdleTexture;

        soldierIdleSprite = new Sprite(soldierIdleTexture);
    }

    private void input() {
        float speed = 30f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            soldierIdleSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            soldierIdleSprite.translateX(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            soldierIdleSprite.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
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

    }

    @Override
    public void dispose() {
        batch.dispose();
        soldierIdle.dispose();

    }
}

