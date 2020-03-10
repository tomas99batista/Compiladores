import java.util.Iterator;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Execute extends HelloBaseVisitor<String> {

   @Override public String visitGreetings(HelloParser.GreetingsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitA(HelloParser.AContext ctx) {
      System.out.println("Ola \"" + visit(ctx.name()) + "\"");
      return null;
   }

   @Override public String visitB(HelloParser.BContext ctx) {
      System.out.println("Adeus \"" + visit(ctx.name()) + "\"");
      return null;
   }
   @Override public String visitName(HelloParser.NameContext ctx) {
      Iterator<TerminalNode> iterator = ctx.ID().iterator();
      String result = "";
      while (iterator.hasNext())
      {
         result += iterator.next().getText() + " ";
      }
      return result.trim();
   }
}
