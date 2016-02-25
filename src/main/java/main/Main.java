package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by User on 24.02.2016.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(Math.random());

        Scanner scanner = new Scanner(new File("input.txt"));

        System.out.println(scanner.next());

    }
}
