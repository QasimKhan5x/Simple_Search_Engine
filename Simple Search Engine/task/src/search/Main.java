package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> getPeople(Scanner scanner) {
        System.out.println("Enter the number of people:");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter all people:");
        List<String> people = new ArrayList<>();
        while (n--> 0) {
            people.add(scanner.nextLine());
        }
        System.out.println();
        return people;
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
        Scanner scanner = new Scanner(System.in);
        var people = getPeople(scanner);
        int option = -1;
        while (option != 0) {
            option = displayMenu(scanner);
            switch (option) {
                case 0: System.out.println("Bye!"); break;
                case 1: {
                    System.out.println("Enter a name or email to search all suitable people.");
                    getInfo(scanner.nextLine(), people);
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

    private static void getInfo(String target, List<String> people) {
        boolean found = false;
        for (var person : people) {
            if (person.toLowerCase().contains(target.trim().toLowerCase())) {
                if (!found)
                    System.out.println("Found people:");
                found = true;
                System.out.println(person);
            }
        }
        if (!found) {
            System.out.println("No matching people found.\n");
        } else {
            System.out.println();
        }
    }
}
