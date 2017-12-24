package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Service {
    public boolean save(String folderName, String fileName, String message) {
        String folder = "/home/tkaczenko/Desktop/" + folderName;
        boolean folderCreated = new File(folder).mkdir();
        if (folderCreated) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder + "/" + fileName + ".txt"))) {
                bw.write(message);
                bw.flush();
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }
}
