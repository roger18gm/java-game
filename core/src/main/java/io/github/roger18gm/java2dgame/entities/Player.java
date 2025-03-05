package io.github.roger18gm.java2dgame.entities;

import io.github.roger18gm.java2dgame.movement.Movement;

public class Player extends Entity {
    private Movement movement;

    public Player(int x, int y, int speed, Movement movement) {
        super(x, y, speed);
        this.movement = movement;
    }

    @Override
    public void update() {
        movement.move(this);
    }
}
