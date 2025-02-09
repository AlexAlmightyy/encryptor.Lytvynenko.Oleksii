package com.javarush;

import java.io.IOException;

public class Util {

    public static void makeDecryptionWithBruteForce(CaesarCipher caesarCipher, FileService fileService, String filePath) {
        try {
            int keyWithBruteforce = caesarCipher.getKeyWithBruteforce(fileService.readFile(filePath));
            String decryptedText = caesarCipher.decrypt(fileService.readFile(filePath), keyWithBruteforce);
            fileService.writeBruteForcedFile(filePath, decryptedText, keyWithBruteforce);
        } catch (IOException e) {
            System.err.println("Something went wrong ! " + e.getMessage());
        }
    }

    public static void makeDecryption(CaesarCipher caesarCipher, FileService fileService, String filePath, int key) {
        try {
            String decrypted = caesarCipher.decrypt(fileService.readFile(filePath), key);
            fileService.writeDecryptedFile(filePath, decrypted);
        } catch (IOException e) {
            System.err.println("Something went wrong ! " + e.getMessage());
        }
    }

    public static void makeEncryption(CaesarCipher caesarCipher, FileService fileService, String filePath, int key) {
        try {
            String encrypted = caesarCipher.encrypt(fileService.readFile(filePath), key);
            fileService.writeEncryptedFile(filePath, encrypted);
        } catch (IOException e) {
            System.err.println("Something went wrong ! " + e.getMessage());
        }
    }
}
