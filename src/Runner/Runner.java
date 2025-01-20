package Runner;

import Cipher.CaesarCipher;
import FileService.FileService;
import UI.CLI;
import UI.GUI;

import java.util.Scanner;

public class Runner{

    public void run(String[] args){
        if(args.length > 0){

            CaesarCipher caesarCipher = new CaesarCipher();
            FileService fileService = new FileService();


            switch (args[0]){
                case "ENCRYPT":
                    fileService.writeEncryptedFile(args[1], caesarCipher.encrypt(fileService.readFile(args[1]), Integer.parseInt(args[2])));
                    break;
                case "DECRYPT":
                    fileService.writeDecryptedFile(args[1], caesarCipher.decrypt(fileService.readFile(args[1]), Integer.parseInt(args[2])));
                    break;
                case "BRUTEFORCE":
                    String decryptedText = caesarCipher.decrypt(fileService.readFile(args[1]), caesarCipher.getKeyWithBruteforce(fileService.readFile(args[1])));
                    int key = caesarCipher.getKeyWithBruteforce(fileService.readFile(args[1]));
                    fileService.writeBruteForcedFile(args[1], decryptedText, key);
                    break;
                default:
                    System.err.println("Wrong arguments");
            }
        }else {
            System.out.println("Choose interface:");
            System.out.println("|   GUI   | Console |");
            System.out.println("---------------------");
            System.out.println("|    1    |    2    |\n");
            Scanner scanner = new Scanner(System.in);
            int userInterface = Integer.parseInt(scanner.nextLine());

            switch (userInterface){
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
