package com.resume.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("./src");

        testFile(directory);
    }

    public static void testFile(File dir){
        File[] files = dir.listFiles();
        if (dir.isDirectory()){
            for (File file : files) {
                testFile(file);
            }
        } else {
            System.out.println(dir.getName());
        }
    }
}
