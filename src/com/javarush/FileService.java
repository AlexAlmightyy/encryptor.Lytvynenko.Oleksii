package com.javarush;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    private void writeFile(String filePath, String fileContent, String status) throws IOException {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        String fileExtencion = "";
        int extencionIndex = fileName.lastIndexOf('.');
        if(extencionIndex > 0) {
            fileExtencion = fileName.substring(extencionIndex);
            fileName = fileName.substring(0, extencionIndex);
        }
        String newFileName = fileName + status + fileExtencion;
        Path newFilePath = path.resolveSibling(newFileName);
        Files.writeString(newFilePath, fileContent);
    }

    public void writeEncryptedFile(String filePath, String fileContent) throws IOException {
        writeFile(filePath, fileContent, "[Encrypted]");
    }

    public void writeDecryptedFile(String filePath, String fileContent) throws IOException {
        writeFile(filePath, fileContent, "[Decrypted]");
    }

    public void writeBruteForcedFile(String filePath, String fileContent, int key) throws IOException {
        writeFile(filePath, fileContent, "[Brute, key - " + key +"]");
    }
}
