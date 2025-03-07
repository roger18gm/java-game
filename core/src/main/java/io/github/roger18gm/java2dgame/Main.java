package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture characterSheet;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;

    private static final int FRAME_COLS = 9;
    private static final int FRAME_ROWS = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        characterSheet = new Texture(Gdx.files.absolute("/Users/lilysorensen/Documents/semester5/cse310/java-game/assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Attack03.png"));

        TextureRegion[][] tempFrames = TextureRegion.split(characterSheet,
            characterSheet.getWidth() / FRAME_COLS,
            characterSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++){
            for (int j = 0; j < FRAME_COLS; j++){
                animationFrames[index++] = tempFrames[i][j];
            }
        }

        walkAnimation = new Animation<>(0.1f, animationFrames);
        stateTime = 0f;
    }

    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        float newWidth = 200;
        float newHeight = 200;

//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        batch.begin();
        batch.draw(currentFrame, -50, -50, newWidth, newHeight);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        characterSheet.dispose();
    }
}

