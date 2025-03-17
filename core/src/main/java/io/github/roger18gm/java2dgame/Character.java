package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;

import java.awt.*;


public class Character {
    private Texture characterSheet;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private float x, y;
    private int frameCols;
    private static final int FRAME_ROWS = 1;



    public Character(String filepath, float x, float y, int frameCols) {
        this.x = x;
        this.y = y;
        this.frameCols = frameCols;
        characterSheet = new Texture(Gdx.files.absolute(filepath));

        TextureRegion[][] tempFrames = TextureRegion.split(characterSheet,
            characterSheet.getWidth() / frameCols,
            characterSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] animationFrames = new TextureRegion[frameCols * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < frameCols; j++) {
                animationFrames[index++] = tempFrames[i][j];
            }
        }

        animation = new Animation<>(0.1f, animationFrames);
        stateTime = 0f;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    public void render(SpriteBatch batch){
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y, 200, 200);
    }

    public void dispose(){
        characterSheet.dispose();
    }
}
