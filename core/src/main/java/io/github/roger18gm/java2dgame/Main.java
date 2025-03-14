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

<<<<<<< Updated upstream
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture characterSheet;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;
=======
import javax.swing.text.AttributeSet;

//import java.lang.classfile.attribute.CharacterRangeTableAttribute;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private Character soldierWalking;
    private Character soldierIdle;
    private Character soldierHurt;
    private Character soldierDeath;
    private Character soldierAttack;
    private Character orcWalking;
    private Character orcIdle;
    private Character orcHurt;
    private Character orcDeath;
    private Character orcAttack;


>>>>>>> Stashed changes

    private static final int FRAME_COLS = 9;
    private static final int FRAME_ROWS = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
<<<<<<< Updated upstream
        characterSheet = new Texture(Gdx.files.absolute("/Users/lilysorensen/Documents/semester5/cse310/java-game/assets/characters/Characters(100x100)/Soldier/Soldier/Soldier-Attack03.png"));

        TextureRegion[][] tempFrames = TextureRegion.split(characterSheet,
            characterSheet.getWidth() / FRAME_COLS,
            characterSheet.getHeight() / FRAME_ROWS);
=======

        // Background
        background = new Background();

        // Each form of soldier and orc
        soldierWalking = new Character("characters/Characters(100x100)/Soldier/Soldier/Soldier-Walk.png", 50, 50, 8);
        soldierIdle = new Character("characters/Characters(100x100)/Soldier/Soldier/Soldier-Idle.png", 100, 50, 6);
        soldierHurt = new Character("characters/Characters(100x100)/Soldier/Soldier/Soldier-Hurt.png", 150, 50, 4);
        soldierDeath = new Character("characters/Characters(100x100)/Soldier/Soldier/Soldier-Death.png", 200, 50, 4);
        soldierAttack = new Character("characters/Characters(100x100)/Soldier/Soldier/Soldier-Attack02.png", 250, 50, 6);
        orcWalking = new Character("characters/Characters(100x100)/Orc/Orc/Orc-Walk.png", 300, 50, 8);
        orcIdle = new Character("characters/Characters(100x100)/Orc/Orc/Orc-Idle.png", 350, 50, 6);
        orcHurt = new Character("characters/Characters(100x100)/Orc/Orc/Orc-Hurt.png", 400, 50, 4);
        orcDeath = new Character("characters/Characters(100x100)/Orc/Orc/Orc-Death.png", 450, 50, 4);
        orcAttack = new Character("characters/Characters(100x100)/Orc/Orc/Orc-Attack02.png", 500, 50, 6);
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
=======
        float deltaTime = Gdx.graphics.getDeltaTime();

        soldierWalking.update(deltaTime);
        soldierIdle.update(deltaTime);
        soldierHurt.update(deltaTime);
        soldierDeath.update(deltaTime);
        soldierAttack.update(deltaTime);
        orcWalking.update(deltaTime);
        orcIdle.update(deltaTime);
        orcHurt.update(deltaTime);
        orcDeath.update(deltaTime);
        orcAttack.update(deltaTime);

//       ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
>>>>>>> Stashed changes

        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> (1.0, 0.75, 0.80)

        batch.begin();
<<<<<<< Updated upstream
        batch.draw(currentFrame, -50, -50, newWidth, newHeight);
=======
        background.render(batch);
        soldierWalking.render(batch);
        soldierIdle.render(batch);
        soldierHurt.render(batch);
        soldierDeath.render(batch);
        soldierAttack.render(batch);
        orcWalking.render(batch);
        orcIdle.render(batch);
        orcHurt.render(batch);
        orcDeath.render(batch);
        orcAttack.render(batch);
>>>>>>> Stashed changes
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
<<<<<<< Updated upstream
        characterSheet.dispose();
=======
        background.dispose();
        soldierWalking.dispose();
        soldierIdle.dispose();
        soldierHurt.dispose();
        soldierDeath.dispose();
        soldierAttack.dispose();
        orcWalking.dispose();
        orcIdle.dispose();
        orcHurt.dispose();
        orcDeath.dispose();
        orcAttack.dispose();


>>>>>>> Stashed changes
    }
}

