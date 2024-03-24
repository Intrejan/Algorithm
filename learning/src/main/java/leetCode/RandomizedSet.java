package leetCode;

import java.util.HashMap;

public class RandomizedSet {

    private final HashMap<Integer, Integer> MAP;
    public RandomizedSet() {
        MAP = new HashMap<>();
    }

    public boolean insert(int val) {
        if (MAP.containsKey(val)) {
            return false;
        } else {
            MAP.put(val, val);
            return true;
        }
    }

    public boolean remove(int val) {
        if (MAP.containsKey(val)) {
            MAP.remove(val);
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        int size = MAP.size();
        int random = (int) (Math.random() * size);
        return (int) MAP.values().toArray()[random];
    }

}
