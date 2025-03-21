package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Inventory {
    private Stage stage;
    private Window inventoryWindow;
    private boolean isVisible; // Tracks visibility

    public Inventory() {
        stage = new Stage(new ScreenViewport());

        // Load a skin for UI elements
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create an inventory window
        inventoryWindow = new Window("Inventory", skin);
        inventoryWindow.setSize(300, 400);
        inventoryWindow.setPosition(50, 50);

        // Add a table to organize the items
        Table table = new Table();
        for (int i = 1; i <= 10; i++) {
            Label label = new Label("Item " + i, skin);
            table.add(label).pad(5);
            table.row();
        }
        inventoryWindow.add(table);

        // Initially hidden
        inventoryWindow.setVisible(false);
        isVisible = false;

        // Add the inventory window to the stage
        stage.addActor(inventoryWindow);

        // Enable input for the stage
        Gdx.input.setInputProcessor(stage);

        public Stage getStage() {
            return stage;
        }
    }

    public void toggleVisibility() {
        isVisible = !isVisible;
        inventoryWindow.setVisible(isVisible);
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            toggleVisibility();
        }
    }

    public void render(float delta) {
        handleInput(); // Check for keypress
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}

