package io.github.roger18gm.java2dgame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Enemy {
    private Texture characterSheet;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private int frameCols;
    private static final int FRAME_ROWS = 1;
    private String filePath;
    private boolean facingLeft = false;  // To track if the character is facing left
    private SteeringAgent enemyAgent;
    private Body playerAgent;
    private SteeringBehavior<Vector2> seekBehavior;
    private SteeringAcceleration<Vector2> steeringOutput;
    private Body body;

    public Enemy(World world, String filepath, int x, int y, Body playerAgent){
        this.frameCols = frameCols;
        this.filePath = filepath;
        characterSheet = new Texture(Gdx.files.absolute(filepath));
        createAnimationFrames();

        this.enemyAgent = new SteeringAgent(body);
        this.playerAgent = playerAgent;
        this.steeringOutput = new SteeringAcceleration<>(new Vector2());
        this.seekBehavior = new Seek<>(enemyAgent, playerAgent);

        // Create Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(15, 15); // Adjust size as needed
//        shape.setAsBox(10, 15); // Adjust size as needed

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void update(float deltaTime) {
        seekBehavior.calculateSteering(steeringOutput);
        applySteering(deltaTime);
    }

    private void applySteering(float deltaTime) {
        Vector2 force = steeringOutput.linear.scl(deltaTime);
        body.applyForceToCenter(force, true);
//        enemyAgent.getLinearVelocity().add(force);
//        enemyAgent.getLinearVelocity().limit(enemyAgent.getMaxLinearSpeed());
//        enemyAgent.getPosition().add(enemyAgent.getLinearVelocity().scl(deltaTime));
    }

    public void setSeekBehavior(SteeringBehavior<Vector2> behavior) {
        this.seekBehavior = behavior;
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

    // Render the current frame of the animation
    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true); // Get current frame in animation

        // Flip the animation if facing left
        if (facingLeft) {
            currentFrame.flip(true, false);  // Flip horizontally
        }

        batch.draw(currentFrame, body.getPosition().x - currentFrame.getRegionWidth() - 10 , body.getPosition().y - currentFrame.getRegionHeight() - 10, 220, 220); // Draw the current frame of the character
//        batch.draw(currentFrame, body.getPosition().x - currentFrame.getRegionWidth() , body.getPosition().y - currentFrame.getRegionHeight() , 200, 200); // Draw the current frame of the character
//        batch.draw(currentFrame, x, y, 200, 200); // Draw the current frame of the character

        // Reset flip to avoid affecting other frames
        if (facingLeft) {
            currentFrame.flip(true, false);  // Flip back to normal
        }
    }

    // Dispose the character sheet (texture) to free resources
    public void dispose() {
        characterSheet.dispose();
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

    // Set the direction the character is facing
    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public Body getBody() {
        return body;
    }
}
