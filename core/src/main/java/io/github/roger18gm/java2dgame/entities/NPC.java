package io.github.roger18gm.java2dgame.entities;

public class NPC extends Entity{
    private String name;

    // Constructor
    public NPC(String name, int x, int y, int speed) {
        super(x, y, speed);
        this.name = name;
    }

    // Implement the abstract method
    @Override
    public void update() {
        // Define how the NPC should behave in the game/ "random" movement
        System.out.println(name + "is moving...");
        x += (Math.random() - 0.5) * speed;
        y += (Math.random() - 0.5) * speed;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName() {
        this.name = name;
    }
}
