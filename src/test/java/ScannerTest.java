import org.example.interpreter.Scanner;
import org.example.interpreter.Token;

public class ScannerTest {
    public static void main(String[] args) {
        String line1 = "// this is a comment\n";
        String line2 = "{} //\n";
        String line3 = "=";
        Scanner scanner = new Scanner(line1 + line2 + line3);
        scanner.scanTokens();
        for(Token t: scanner.getTokens()){
            System.out.println(t.toString());
        }
    }
}
