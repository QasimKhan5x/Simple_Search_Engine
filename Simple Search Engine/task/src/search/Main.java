package search;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        String target = scanner.nextLine();

        int index = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Not found");
        } else {
            System.out.println(index + 1);
        }

    }
}
