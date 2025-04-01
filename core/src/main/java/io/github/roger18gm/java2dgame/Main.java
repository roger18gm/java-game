package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import items.Inventory;
import items.HealthItem;
import items.HeartSpawner;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Character soldier;
    private MovePerson movePerson; // Character movement instance
    private Background background; // Background rendering
    private Inventory inventory; // Inventory system
    public static boolean isPaused = false;

    private ArrayList<HealthItem> healthItems; // List of hearts on screen
    private Texture heartTexture; // Heart texture
    private Rectangle playerBox; // Player's collision box
    private Texture flagTexture;

    private float spawnTimer; // Timer for heart spawning

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Initialize player character
        soldier = new Character("C:\\Users\\austi\\IdeaProjects\\java-game\\assets\\characters\\Characters(100x100)\\Soldier\\Soldier\\Soldier-Walk.png", 100, 50, 6);

        // Initialize background
        background = new Background();

        // Initialize inventory system
        Skin skin = new Skin(Gdx.files.internal("C:\\Users\\austi\\IdeaProjects\\java-game\\assets\\ui\\uiskin.json"));
        inventory = new Inventory();

        // Initialize player movement logic
        movePerson = new MovePerson(soldier);

        // Initialize health items
        healthItems = new ArrayList<>();
        heartTexture = new Texture("C:\\Users\\austi\\IdeaProjects\\java-game\\core\\src\\main\\java\\items\\heart pixel art 16x16.png"); // Replace with actual heart texture path
        flagTexture = new Texture("C:\\Users\\austi\\IdeaProjects\\java-game\\assets\\tiles\\PNG\\Props\\Flag_A.png");

        // Initial player box
        playerBox = new Rectangle(100, 100, 50, 50); // Example player position and size

        // Spawn some initial hearts
//        for (int i = 0; i < 5; i++) {
//            healthItems.add(HeartSpawner.createRandomHeart(heartTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
//        }

        // Set up input multiplexer for inventory and player movement
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(inventory.getStage());
        multiplexer.addProcessor(movePerson);
        Gdx.input.setInputProcessor(multiplexer);

        isPaused = false; // Game starts unpaused
        spawnTimer = 0;   // Initialize spawn timer
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Update spawn timer and spawn hearts periodically
        spawnTimer += deltaTime;
        if (spawnTimer > 5) { // Spawn a new heart every 5 seconds
            healthItems.add(HeartSpawner.createRandomHeart(heartTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            spawnTimer = 0; // Reset spawn timer
        }

        // Collision detection: Check for player pickup
        Iterator<HealthItem> iterator = healthItems.iterator();
        while (iterator.hasNext()) {
            HealthItem heart = iterator.next();
            if (heart.isColliding(playerBox)) {
                System.out.println("Collision detected with heart at: " +heart.getPickupBox());
                heart.applyEffect(); // Apply health effect
                iterator.remove();   // Remove heart after pickup
            }
        }
        batch.begin();
        for (HealthItem heart : healthItems) {
            heart.render(batch);
        }
        batch.end();

        // Update game elements only when not paused
        if (!isPaused) {
            movePerson.update(deltaTime);
            background.update(deltaTime);

            // Example player movement logic (replace with real movement updates)
            playerBox.setPosition(playerBox.x + 1, playerBox.y); // Temporary movement example
        }

        // Clear the screen
        ScreenUtils.clear(1.0f, 0.75f, 0.80f, 1.0f); // RGB (255, 192, 203) -> pastel pink

        // Render game objects
        batch.begin();
        background.render(batch);
        if (!isPaused) {
            soldier.render(batch); // Render soldier only when not paused
        }

        // Render health items
        for (HealthItem heart : healthItems) {
            heart.render(batch);
        }
        batch.end();

        // Render inventory elements
        inventory.render(deltaTime);
    }

    @Override
    public void dispose() {
        batch.dispose();
        soldier.dispose();
        background.dispose();
        inventory.dispose();
        heartTexture.dispose();

        // Dispose of health item textures
        for (HealthItem heart : healthItems) {
            heart.dispose();
        }
    }
}
