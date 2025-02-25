package com.javarush.UI;

import com.javarush.CaesarCipher;
import com.javarush.FileService;

import java.util.Scanner;

import static com.javarush.CipherUtility.*;

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
                makeEncryption(caesarCipher, fileService, filePath, key);
                System.out.println("Successfully!");
                break;
            case 2:
                System.out.println("Enter a key :");
                int key2 = Integer.parseInt(scanner.nextLine());
                makeDecryption(caesarCipher, fileService, filePath, key2);
                System.out.println("Successfully!");
                break;
            case 3:
                makeDecryptionWithBruteForce(caesarCipher, fileService, filePath);
                System.out.println("Successfully!");
                break;
            default:
                System.out.println("Enter correct number of action :");

        }
    }
}
