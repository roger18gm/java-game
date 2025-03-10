package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

//import java.lang.classfile.attribute.CharacterRangeTableAttribute;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    //    private Texture characterSheet;
//    private Animation<TextureRegion> walkAnimation;
//    private float stateTime;
    private Character soldierWalking;
    private Character soldierIdel;
    private Character soldierHurt;
    private Character soldierDeath;


    @Override
    public void create() {
        batch = new SpriteBatch();
        soldierWalking = new Character("/Users/lilysorensen/Documents/semester5/cse310/java-game/assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png", 50, 50, 8);
        soldierIdel = new Character("/Users/lilysorensen/Documents/semester5/cse310/java-game/assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Idle.png", 100, 50, 6);
        soldierHurt = new Character("/Users/lilysorensen/Documents/semester5/cse310/java-game/assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Hurt.png", 150, 50, 4);
        soldierDeath = new Character("/Users/lilysorensen/Documents/semester5/cse310/java-game/assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Death.png", 200, 50, 4);


//        TextureRegion[][] tempFrames = TextureRegion.split(characterSheet,
//            characterSheet.getWidth() / FRAME_COLS,
//            characterSheet.getHeight() / FRAME_ROWS);
//
//        TextureRegion[] animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
//        int index = 0;
//        for (int i = 0; i < FRAME_ROWS; i++){
//            for (int j = 0; j < FRAME_COLS; j++){
//                animationFrames[index++] = tempFrames[i][j];
//            }
//        }
//
//        walkAnimation = new Animation<>(0.1f, animationFrames);
//        stateTime = 0f;
    }

    @Override
    public void render() {
//        stateTime += Gdx.graphics.getDeltaTime();
//        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

//        float newWidth = 200;
//        float newHeight = 200;

        float deltaTime = Gdx.graphics.getDeltaTime();

        soldierWalking.update(deltaTime);
        soldierIdel.update(deltaTime);
        soldierHurt.update(deltaTime);
        soldierDeath.update(deltaTime);

//       ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        batch.begin();
        soldierWalking.render(batch);
        soldierIdel.render(batch);
        soldierHurt.render(batch);
        soldierDeath.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        soldierWalking.dispose();
        soldierIdel.dispose();
        soldierHurt.dispose();
        soldierDeath.dispose();

    }
}

