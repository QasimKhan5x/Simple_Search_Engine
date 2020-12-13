package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SearchingStrategy {
    public void search(String query);

    public static List<Integer> flatten(List<List<Integer>> list) {
        List<Integer> flattened = new ArrayList<>();
        list.forEach(flattened::addAll);
        return flattened;
    }
}
