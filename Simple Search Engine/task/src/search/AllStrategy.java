package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllStrategy implements SearchingStrategy {
    List<String> data;
    Map<String, List<Integer>> invertedIndex;

    AllStrategy(List<String> data, Map<String, List<Integer>> invertedIndex) {
        this.data = data;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void search(String query) {
        /*
        1. Find indices of people whose information is mentioned in the query
        2. Print those people
         */
        String[] targets = query.trim().toLowerCase().split(" ");
        if (targets.length == 0 || !invertedIndex.containsKey(targets[0])) {
            System.out.println("No matching people found.\n");
            return;
        }
        List<Integer> results = new ArrayList<>(invertedIndex.get(targets[0]));
        for (int i = 1; i < targets.length; i++) {
            if (invertedIndex.containsKey(targets[i])) {
                List<Integer> indices = invertedIndex.get(targets[i]);
                results.retainAll(indices);
            } else {
                System.out.println("No matching people found.\n");
                return;
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
