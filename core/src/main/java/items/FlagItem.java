package items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FlagItem extends BaseItem {
    private boolean isCarried; // Tracks if the flag is being carried by the player

    public FlagItem(float x, float y, float width, float height, Texture texture) {
        super(false, 0, true, "Flag", "Get it to the circle!", x, y, width, height, texture);
        this.isCarried = false; // Initially, the flag is not carried
    }

    // Static factory method to create a FlagItem
    public static FlagItem createFlag(Texture flagTexture) {
        float x = 525; // Example X coordinate
        float y = 200; // Example Y coordinate
        float width = 60;
        float height = 60;
        return new FlagItem(x, y, width, height, flagTexture);
    }

    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), getPickupBox().x, getPickupBox().y, getPickupBox().width, getPickupBox().height);
    }

    public boolean isColliding(Rectangle playerBox) {
        return getPickupBox().overlaps(playerBox);
    }

    public void carry(float playerX, float playerY) {
        setPickupBox(playerX, playerY, getPickupBox().width, getPickupBox().height);
    }

    public void drop() {
        isCarried = false;
    }

    public boolean isCarried() {
        return isCarried;
    }

    public void setCarried(boolean isCarried) {
        this.isCarried = isCarried;
    }

    public void dispose() {
        if (getTexture() != null) {
            getTexture().dispose();
        }
    }
}
