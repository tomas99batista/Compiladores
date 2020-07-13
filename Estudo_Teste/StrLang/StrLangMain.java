import java.io.*;
import java.util.Scanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class StrLangMain {
   public static void main(String[] args) {
      for (String path : args) {
         try {
            Scanner sc = new Scanner(new File(path));
            String lineText = null;
            int numLine = 1;
            if (sc.hasNextLine())
               lineText = sc.nextLine();
            StrLangParser parser = new StrLangParser(null);
            // replace error listener:
            // parser.removeErrorListeners(); // remove ConsoleErrorListener
            // parser.addErrorListener(new ErrorHandlingListener());
            Execute visitor0 = new Execute();
            while (lineText != null) {
               // create a CharStream that reads from standard input:
               CharStream input = CharStreams.fromString(lineText + "\n");
               // create a lexer that feeds off of input CharStream:
               StrLangLexer lexer = new StrLangLexer(input);
               lexer.setLine(numLine);
               lexer.setCharPositionInLine(0);
               // create a buffer of tokens pulled from the lexer:
               CommonTokenStream tokens = new CommonTokenStream(lexer);
               // create a parser that feeds off the tokens buffer:
               parser.setInputStream(tokens);
               // begin parsing at main rule:
               ParseTree tree = parser.main();
               if (parser.getNumberOfSyntaxErrors() == 0) {
                  // print LISP-style tree:
                  // System.out.println(tree.toStringTree(parser));
                  visitor0.visit(tree);
               }
               if (sc.hasNextLine())
                  lineText = sc.nextLine();
               else
                  lineText = null;
               numLine++;
            }
         } catch (RecognitionException e) {
            e.printStackTrace();
            System.exit(1);
         } catch (FileNotFoundException e){
            System.err.println("FILE não encontrado: " + e);
         }
      }

   }
}
