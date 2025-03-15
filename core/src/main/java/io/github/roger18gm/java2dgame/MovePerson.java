package io.github.roger18gm.java2dgame;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.awt.event.KeyEvent;


public class MovePerson  implements InputProcessor {

    private Character character;  // Now accepts a character via constructor
    private int x, y;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private static final String WALKING_PATH = "C:\\Users\\jerem\\OneDrive\\Documents\\Winter2025\\Applied Programming\\java-game\\assets\\characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Walk.png";
    private static final String IDLE_PATH = "C:\\Users\\jerem\\OneDrive\\Documents\\Winter2025\\Applied Programming\\java-game\\assets\\characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Idle.png";


    public MovePerson(Character character) {
        this.character = character;
        this.x = character.GetX();
        this.y = character.GetY();
    }

    // Called when a key is pressed
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            movingLeft = true; // Start moving left
        } else if (keycode == Input.Keys.RIGHT) {
            movingRight = true; // Start moving right
        } else if (keycode == Input.Keys.UP) {
            movingUp = true; // Start moving up
        } else if (keycode == Input.Keys.DOWN) {
            movingDown = true; // Start moving down
        }
        return true;
    }

    // Called when a key is released
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            movingLeft = false; // Stop moving left
        } else if (keycode == Input.Keys.RIGHT) {
            movingRight = false; // Stop moving right
        } else if (keycode == Input.Keys.UP) {
            movingUp = false; // Stop moving up
        } else if (keycode == Input.Keys.DOWN) {
            movingDown = false; // Stop moving down
        }
        return true;
    }

    // Update class that moves the character in Render in Main
    public void update(float deltaTime) {
        if (movingLeft) {
            character.SetX(character.GetX() - 1); // Move Left
            character.setFacingLeft(true);           // Set character to face left
        }
        if (movingRight) {
            character.SetX(character.GetX() + 1); // Move Right
            character.setFacingLeft(false);          // Set character to face right
        }
        if (movingUp) {
            character.SetY(character.GetY() + 1); // Move Up
        }
        if (movingDown) {
            character.SetY(character.GetY() - 1); // Move Down
        }
        // Change images if moving
        if (movingDown || movingUp || movingLeft || movingRight){
            character.SetFilePath(WALKING_PATH, 8);
        }
        else
        {
            character.SetFilePath(IDLE_PATH, 6);
        }
        character.update(deltaTime);
    }



    // Ignore these. They are here to avoid errors.
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}

