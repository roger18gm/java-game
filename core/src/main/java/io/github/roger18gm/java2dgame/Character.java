package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import io.github.roger18gm.java2dgame.entities.Player;
import io.github.roger18gm.java2dgame.movement.PlayerMovement;

import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;

public class Character {
    private Texture characterSheet;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private int x, y;
    private int frameCols;
    private static final int FRAME_ROWS = 1;
    private String filePath;


    // Constructor
    public Character(String filepath, int x, int y, int frameCols) {
        this.x = x;
        this.y = y;
        this.frameCols = frameCols;
        this.filePath = filepath;
        characterSheet = new Texture(Gdx.files.absolute(filepath));

        // Create animation frames
        createAnimationFrames();
    }

    // Creates the animation frames based on the texture and number of columns
    private void createAnimationFrames() {
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

        animation = new Animation<>(0.1f, animationFrames); // Create the animation with frame time of 0.1f
        stateTime = 0f; // Reset state time for animation
    }

    // Update the state time (should be called every frame)
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    // Render the current frame of the animation
    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true); // Get current frame in animation
        batch.draw(currentFrame, x, y, 200, 200); // Draw the current frame of the character
    }

    // Dispose the character sheet (texture) to free resources
    public void dispose() {
        characterSheet.dispose();
    }

    // Getter and Setter for position
    public int GetX() {
        return x;
    }

    public void SetX(int x) {
        this.x = x;
    }

    public int GetY() {
        return y;
    }

    public void SetY(int y) {
        this.y = y;
    }

    // Getter and Setter for the file path (also reloads texture and animation frames)
    public String GetFilePath() {
        return this.filePath;
    }

    public void SetFilePath(String filePath, int frameCols) {
        if (!this.filePath.equals(filePath)) {
            this.filePath = filePath;
            this.frameCols = frameCols;

            // Dispose of the old texture if it exists
            if (characterSheet != null) {
                characterSheet.dispose();
            }

            // Load the new texture
            characterSheet = new Texture(Gdx.files.absolute(filePath));

            // Recreate the animation frames with the new texture
            createAnimationFrames();

        }
    }
}

