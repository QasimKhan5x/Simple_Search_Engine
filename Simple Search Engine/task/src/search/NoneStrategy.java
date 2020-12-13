package search;

import java.util.*;

public class NoneStrategy implements SearchingStrategy {
    List<String> data;
    Map<String, List<Integer>> invertedIndex;

    NoneStrategy(List<String> data, Map<String, List<Integer>> invertedIndex) {
        this.data = data;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void search(String query) {
        List<Integer> list = new ArrayList<>();
        invertedIndex.values().forEach(list::addAll);
        int max = list
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        String[] targets = query.trim().toLowerCase().split(" ");
        Set<Integer> results = new HashSet<>();
        for (String target : targets) {
            if (invertedIndex.containsKey(target)) {
                results.addAll(invertedIndex.get(target));
            }
        }
        Set<Integer> complement = new HashSet<>();
        for (int i = 0; i <= max; i++) {
            if (!results.contains(i))
                complement.add(i);
        }
        if (complement.isEmpty()) {
            System.out.println("No matching people found.\n");
        } else {
            System.out.println(complement.size() + " persons found:");
            for (int i : complement) {
                System.out.println(data.get(i));
            }
            System.out.println();
        }
    }
}
