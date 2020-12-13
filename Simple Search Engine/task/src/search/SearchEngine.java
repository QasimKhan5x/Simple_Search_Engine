package search;

import java.util.List;
import java.util.Map;

public class SearchEngine {

    SearchingStrategy strategy;

    SearchEngine(SearchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void search(String query) {
        strategy.search(query);
    }
}
