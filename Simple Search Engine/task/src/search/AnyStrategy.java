package search;

import java.util.*;

public class AnyStrategy implements SearchingStrategy {
    List<String> data;
    Map<String, List<Integer>> invertedIndex;

    AnyStrategy(List<String> data, Map<String, List<Integer>> invertedIndex) {
        this.data = data;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void search(String query) {
        String[] targets = query.trim().toLowerCase().split(" ");
        if (targets.length == 0) {
            System.out.println("No matching people found.\n");
            return;
        }
        Set<Integer> results = new HashSet<>();
        for (String target : targets) {
            if (invertedIndex.containsKey(target)) {
                results.addAll(invertedIndex.get(target));
            }
        }
        if (results.isEmpty()) {
            System.out.println("No matching people found.\n");
        } else {
            System.out.println(results.size() + " persons found:");
            for (int i : results) {
                System.out.println(data.get(i));
            }
            System.out.println();
        }
    }
}
