package org.example.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Executor {
    public static void main(String[] args) {

    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
    }

    private static void execute(String source){
//        Scanner scanner = new Scanner(source);
    }
}
