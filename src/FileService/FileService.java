package FileService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public String readFile(String filePath){
        try{
            return Files.readString(Path.of(filePath));
        } catch (NoSuchFileException e) {
            System.err.println("File not found :");
            System.err.println(e.getMessage());
            return "";
        } catch (IOException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
        return "";
    }

    private void writeFile(String filePath,String fileContent, String status){
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        String fileExtencion = "";
        int extencionIndex = fileName.lastIndexOf('.');
        if(extencionIndex > 0){
            fileExtencion = fileName.substring(extencionIndex);
            fileName = fileName.substring(0, extencionIndex);
        }
        String newFileName = fileName + status + fileExtencion;
        Path newFilePath = path.resolveSibling(newFileName);
        try{
            Files.writeString(newFilePath, fileContent);
        } catch (IOException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeEncryptedFile(String filePath, String fileContent){
        writeFile(filePath, fileContent, "[Encrypted]");
    }

    public void writeDecryptedFile(String filePath, String fileContent){
        writeFile(filePath, fileContent, "[Decrypted]");
    }

    public void writeBruteForcedFile(String filePath, String fileContent, int key){
        writeFile(filePath, fileContent, "[Brute, key - " + key +"]");
    }
}
