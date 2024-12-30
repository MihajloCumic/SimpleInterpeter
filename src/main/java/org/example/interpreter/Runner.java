package org.example.interpreter;

import org.example.interpreter.ast.ASTNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        if(args.length != 1) throw new RuntimeException("Usage: java Runner <file-name>");
        try {
            String source = getSource(args[0]);

            Scanner scanner = new Scanner(source);
            List<Token> tokens = scanner.scanTokens();

            Parser parser = new Parser(tokens);

            ASTNode root = parser.parse();
            root.accept(new Interpreter());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSource(String pathStr) throws IOException {
        Path path = Paths.get(pathStr);
        if(Files.exists(path)){
            return Files.readString(path);
        }else {
            throw new RuntimeException("File does not exist.");
        }
    }
}
