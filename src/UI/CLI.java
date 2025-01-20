package UI;

import Cipher.CaesarCipher;
import FileService.FileService;

import java.util.Scanner;

public class CLI {

    public void start(){
        System.out.println("[E]NCRYPT | [D]ECRYPT | [B]RUTEFORCE");
        System.out.println("------------------------------------");
        System.out.println("|    1    |      2    |      3     |");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action :");
        int choosedAction = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a path to the file :");
        String filePath = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();
        FileService fileService = new FileService();

        switch (choosedAction){
            case 1:
                System.out.println("Enter a key :");
                int key = scanner.nextInt();
                fileService.writeEncryptedFile(filePath, caesarCipher.encrypt(fileService.readFile(filePath), key));
                System.out.println("Successfully!");
                break;
            case 2:
                System.out.println("Enter a key :");
                int key2 = scanner.nextInt();
                fileService.writeDecryptedFile(filePath, caesarCipher.decrypt(fileService.readFile(filePath), key2));
                System.out.println("Successfully!");
                break;
            case 3:
                fileService.writeBruteForcedFile(filePath
                        , caesarCipher.decrypt(fileService.readFile(filePath), caesarCipher.getKeyWithBruteforce(fileService.readFile(filePath)))
                        , caesarCipher.getKeyWithBruteforce(fileService.readFile(filePath)));
                System.out.println("Successfully!");
                break;
            default:
                System.out.println("Enter correct number of action :");

        }
    }
}
