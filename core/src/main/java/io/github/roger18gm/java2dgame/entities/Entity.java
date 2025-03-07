package io.github.roger18gm.java2dgame.entities;

public abstract class Entity {
    protected int x, y, speed;

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    // Getters and Setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    // To be implemented by subclasses
    public abstract void update();
}
