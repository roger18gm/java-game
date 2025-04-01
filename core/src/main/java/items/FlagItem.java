package items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class FlagItem extends BaseItem {

    public FlagItem(float x, float y, float width, float height, Texture texture) {
        super(false, 0, true, "Flag", "Get it to the circle!", x, y, width, height, texture);
    }

    // Render the health item on the screen
    public void render(SpriteBatch batch) {
        if (canPickup()) {
            batch.draw(getTexture(), getPickupBox().x, getPickupBox().y, getPickupBox().width, getPickupBox().height);
        }
    }

    // Check collision with player
    public boolean isColliding(Rectangle playerBox) {
        System.out.println("Flag PickupBox: " + getPickupBox());
        System.out.println("Player box: " + playerBox);
        return getPickupBox().overlaps(playerBox);
    }

    public static FlagItem createFlag(Texture flagTexture) {
        float x = 200; // Fixed position or specific location
        float y = 300;

        FlagItem flag = new FlagItem();
        flag.setName("Flag");
        flag.setDescription("Get it to the circle!");
        flag.setPickupBox(x, y, 30, 30); // Set collision box dimensions
        flag.setTexture(flagTexture); // Set flag texture
        return flag;
    }

    // Dispose of resources
    public void dispose() {
        if (getTexture() != null) {
            getTexture().dispose();
        }
    }
}
