package items;

public class BaseItem {
    private boolean consume;
    private int hpRestore;
    private int weight;


    public BaseItem(boolean consume, int hpRestore, int weight) {
        this.consume = consume;
        this.hpRestore = hpRestore;
        this.weight = weight;
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
        this.hpRestore = hpRestore;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

