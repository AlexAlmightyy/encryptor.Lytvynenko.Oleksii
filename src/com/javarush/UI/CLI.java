package com.javarush.UI;

import com.javarush.CaesarCipher;
import com.javarush.FileService;

import java.io.IOException;
import java.util.Scanner;

public class CLI {

    public void start(){
        System.out.println("[E]NCRYPT | [D]ECRYPT | [B]RUTEFORCE");
        System.out.println("------------------------------------");
        System.out.println("|    1    |      2    |      3     |");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action :");
        int chosenAction = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a path to the file :");
        String filePath = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();
        FileService fileService = new FileService();

        switch (chosenAction){
            case 1:
                System.out.println("Enter a key :");
                int key = scanner.nextInt();
                try {
                    String encryptedText = caesarCipher.encrypt(fileService.readFile(filePath), key);
                    fileService.writeEncryptedFile(filePath, encryptedText);
                } catch (IOException e) {
                    System.err.println("Something went wrong ! " + e.getMessage());
                }
                System.out.println("Successfully!");
                break;
            case 2:
                System.out.println("Enter a key :");
                int key2 = Integer.parseInt(scanner.nextLine());
                try {
                    String decryptedText = caesarCipher.decrypt(fileService.readFile(filePath), key2);
                    fileService.writeDecryptedFile(filePath, decryptedText);
                } catch (IOException e) {
                    System.err.println("Something went wrong ! " + e.getMessage());
                }
                System.out.println("Successfully!");
                break;
            case 3:
                try {
                    int key3 = caesarCipher.getKeyWithBruteforce(fileService.readFile(filePath));
                    String bruteText = caesarCipher.decrypt(fileService.readFile(filePath), key3);
                    fileService.writeBruteForcedFile(filePath, bruteText, key3);
                } catch (IOException e) {
                    System.err.println("Something went wrong ! " + e.getMessage());
                }
                System.out.println("Successfully!");
                break;
            default:
                System.out.println("Enter correct number of action :");

        }
    }
}
