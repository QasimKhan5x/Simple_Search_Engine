package search;

import java.io.*;
import java.util.*;

public class Main {

    public static List<String> getPeople(String filepath) {

        List<String> people = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                people.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }
    
    public static Map<String, List<Integer>> getInvertedIndex(List<String> people) {
        Map<String, List<Integer>> invertedIndex = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            String[] items = people.get(i).split(" ");
            for (String item : items) {
                item = item.toLowerCase();
                if (invertedIndex.containsKey(item)) {
                    invertedIndex.get(item).add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    invertedIndex.put(item, list);
                }
            }
        }
        return invertedIndex;
    }

    public static int displayMenu(Scanner scanner) {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println();
        return n;
    }

    public static void main(String[] args) {
        String filepath = args[1];
        List<String> people = getPeople(filepath);
        var invertedIndex = getInvertedIndex(people);
        SearchEngine allEngine = new SearchEngine(new AllStrategy(people, invertedIndex));
        SearchEngine anyEngine = new SearchEngine(new AnyStrategy(people, invertedIndex));
        SearchEngine noneEngine = new SearchEngine(new NoneStrategy(people, invertedIndex));
        Scanner scanner = new Scanner(System.in);

        int option = -1;
        while (option != 0) {
            option = displayMenu(scanner);
            switch (option) {
                case 0: System.out.println("Bye!"); break;
                case 1: {
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    switch (strategy) {
                        case "ALL": allEngine.search(query); break;
                        case "ANY": anyEngine.search(query); break;
                        case "NONE": noneEngine.search(query); break;
                    }
                    break;
                }
                case 2: {
                    System.out.println("=== List of people ===");
                    for (var person : people)
                        System.out.println(person);
                    System.out.println();
                    break;
                }
                default: System.out.println("Incorrect option! Try again.\n");
            }
        }
    }

    private static void getInfo(String target, List<String> people, Map<String, List<Integer>> invertedIndex) {
        target = target.trim().toLowerCase();
        if (invertedIndex.containsKey(target)) {
            System.out.println("Found people:");
            List<Integer> indices = invertedIndex.get(target);

            for (int i : indices) {
                System.out.println(people.get(i));
            }
            System.out.println();
        } else {
            System.out.println("No matching people found.\n");
        }
    }
}
