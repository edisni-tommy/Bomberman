package Entities.BuffItem;

import java.util.ArrayList;
import java.util.List;

public class BuffItemList {
    private static final List<BuffItem> buffItemList = new ArrayList<>();

    public static void add(BuffItem buffItem) {
        buffItemList.add(buffItem);
    }

    public static void remove(BuffItem buffItem) {
        buffItemList.remove(buffItem);
    }
}
