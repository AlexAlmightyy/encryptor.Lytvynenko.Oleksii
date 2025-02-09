package com.javarush;

import com.javarush.UI.CLI;
import com.javarush.UI.GUI;

import java.io.IOException;
import java.util.Scanner;

import static com.javarush.Util.*;

class Runner {

    void run(String[] args) {
        if (args.length > 0) {
            CaesarCipher caesarCipher = new CaesarCipher();
            FileService fileService = new FileService();
            int key = Integer.parseInt(args[2]);
            String filePath = args[1];
            switch (args[0]) {
                case "ENCRYPT":
                    makeEncryption(caesarCipher, fileService, filePath, key);
                    break;
                case "DECRYPT":
                    makeDecryption(caesarCipher, fileService, filePath, key);
                    break;
                case "BRUTEFORCE":
                    makeDecryptionWithBruteForce(caesarCipher, fileService, filePath);
                    break;
                default:
                    System.err.println("Wrong arguments");
            }
        } else {
            System.out.println("Choose interface:");
            System.out.println("|   GUI   | Console |");
            System.out.println("---------------------");
            System.out.println("|    1    |    2    |\n");
            Scanner scanner = new Scanner(System.in);
            int userInterface = Integer.parseInt(scanner.nextLine());
            switch (userInterface) {
                case 1:
                    new GUI();
                    break;
                case 2:
                    CLI cli = new CLI();
                    cli.start();
                    break;
                default:
                    System.out.println("Invalid number, please choose 1 or 2");
            }
        }
    }



}
