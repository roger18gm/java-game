package io.github.roger18gm.java2dgame.movement;

import io.github.roger18gm.java2dgame.entities.Entity;

public class PlayerMovement implements Movement {
    public void move(Entity entity) {
        int dx = getInputX(); // Assume a method that reads input
        int dy = getInputY();
        entity.setX(entity.getX() + dx * entity.getSpeed());
        entity.setY(entity.getY() + dy * entity.getSpeed());
    }

    private int getInputX() {
        // Placeholder for input handling
        return 0;
    }

    private int getInputY() {
        return 0;
    }
}
