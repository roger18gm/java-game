package io.github.roger18gm.java2dgame;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.InputProcessor;

import java.util.Set;

public class Enemy {
    private Character character;  // Now accepts a character via constructor
    private float x, y;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private final int SPEED = 60;
    private static final String WALKING_PATH = "characters\\Characters(100x100)\\Orc\\Orc\\Orc-Walk.png";
    private static final String IDLE_PATH = "characters\\Characters(100x100)\\Orc\\Orc\\Orc-Idle.png";
    private MovePerson movePerson;

    public Enemy(Character character) {
        this.character = character;
        this.x = character.GetX();
        this.y = character.GetY();
    }

    public void SetX(float x){
        this.x = x;
    }


    // Called when a key is pressed
    public float Nearby() {


//        if (movePerson.GetX() <= x + 15)
//        {
//            SetX(x + 20);
//        }

        return x+2;
    }

    public float GetX(){
        return x;
    }
    public float GetY(){
        return y;
    }



    // Update class that moves the character in Render in Main
    public void update(float deltaTime) {
        // new addition
//        Vector2 velocity = new Vector2(0,0);
//
        SetX(x + 2);

//        if (movingLeft) {
//            character.SetX(character.GetX() - 1); // Move Left
////            velocity.x = -SPEED;
////            x = velocity.x;
//            character.setFacingLeft(true);           // Set character to face left
//        }
//        if (movingRight) {
//            character.SetX(character.GetX() + 1); // Move Right
////            velocity.x = SPEED;
////            x = velocity.x;
//            character.setFacingLeft(false);          // Set character to face right
//        }
//        if (movingUp) {
//            character.SetY(character.GetY() + 1); // Move Up
////            velocity.y = SPEED;
////            y = velocity.y;
//        }
//        if (movingDown) {
//            character.SetY(character.GetY() - 1); // Move Down
////            velocity.y = -SPEED;
////            y = velocity.y;
//        }
////        character.getBody().setLinearVelocity(velocity);
//
//        // Change images if moving
//        if (movingDown || movingUp || movingLeft || movingRight){
//            character.SetFilePath(WALKING_PATH, 8);
//        }
//        else
//        {
//            character.SetFilePath(IDLE_PATH, 6);
//        }
        character.update(deltaTime);
    }
}
