package Runner;

import Cipher.CaesarCipher;
import FileService.FileService;

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
            }
        }else {
            //TODO CLI and GUI
        }
    }

}
