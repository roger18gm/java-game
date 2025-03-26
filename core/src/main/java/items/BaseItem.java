package items;

import java.awt.*;
import com.badlogic.gdx.math.Rectangle;

public class BaseItem {
    private boolean consume;
    private int hpRestore;
    private boolean canPickup;
    private String name;
    private String description;
    private Rectangle pickupBox;

    // Full constructor with validation
    public BaseItem(boolean consume, int hpRestore, boolean canPickup, String name, String description, float x, float y, float width, float height) {
        this.consume = consume;
        this.hpRestore = Math.max(hpRestore, 0); // Prevent negative HP restore
        this.canPickup = canPickup;
        this.name = name;
        this.description = description;
        this.pickupBox = new Rectangle(x, y, width, height);
    }

    // Default constructor for placeholders
    public BaseItem() {
        this.consume = false;
        this.hpRestore = 0;
        this.canPickup = true;
        this.name = "Default Item";
        this.description = "Default Description";
    }

    public boolean isConsumable() {
        return consume;
    }

    public void setConsumable(boolean consume) {
        this.consume = consume;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    public void setHpRestore(int hpRestore) {
        if (hpRestore >= 0) {
            this.hpRestore = hpRestore;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCanPickup() {
        return canPickup;
    }

    public void setCanPickup(boolean canPickup) {
        this.canPickup = canPickup;
    }

    @Override
    public String toString() {
        return "BaseItem{name='" + name + "', description='" + description +
            "', consumable=" + consume + ", hpRestore=" + hpRestore +
            ", canPickup=" + canPickup + "}";
    }
}


//package items;
//
//public class BaseItem {
//    private boolean consume;
//    private int hpRestore;
//    private boolean canPickup;
//    private String name;
//    private String description;
//
//
//    public BaseItem(boolean consume, int hpRestore, boolean canPickup, String name, String description) {
//        this.consume = consume;
//        this.hpRestore = Math.max(hpRestore, 0);
//        this.canPickup = canPickup;
//        this.name = name;
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "BaseItem{name='" + name + "', description='" + description +
//            "', consumable=" + consume + ", hpRestore=" + hpRestore +
//            ", canPickup=" + canPickup + "}";
//    }
//
//
//    public boolean isConsumable() {
//        return consume;
//    }
//
//    public void setConsumable(boolean consume) {
//        this.consume = consume;
//    }
//
//    public int getHpRestore() {
//        return hpRestore;
//    }
//
//    public void setHpRestore(int hpRestore) {
//        if (hpRestore >= 0) { // Prevent negative HP restore values
//            this.hpRestore = hpRestore;
//        }
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public boolean isCanPickup() {
//        return canPickup;
//    }
//
//    public void setCanPickup(boolean canPickup) {
//        this.canPickup = canPickup;
//    }
//
//}
//
