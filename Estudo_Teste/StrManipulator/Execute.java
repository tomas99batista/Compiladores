import java.util.HashMap;
import java.util.Map;

import java.util.*;

public class Execute extends StrManipBaseVisitor<String> {

   @Override public String visitPrint(StrManipParser.PrintContext ctx) {
      String res = visit(ctx.expr());
      if (res != null){
         System.out.println("> " + res);
      }
      return res;
   }

   @Override public String visitAssign(StrManipParser.AssignContext ctx) {
      String res = visit(ctx.expr());
      if (res != null){
         String identifier = ctx.Identifier().getText();
         symbol_table.put(identifier, res);
      }
      return res;
   }

   @Override public String visitExprConcat(StrManipParser.ExprConcatContext ctx) {
      String res = null;
      String e1 = visit(ctx.expr(0));
      String e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null){
         res = e1 + e2;
      }
      return res;
   }

   @Override public String visitExprString(StrManipParser.ExprStringContext ctx) {
      String res = null;
      String string = ctx.String().getText();
      res = string.substring(1, string.length() - 1);
      return res;
   }

   @Override public String visitExprInput(StrManipParser.ExprInputContext ctx) {
      String res = null;
      String text = visit(ctx.expr());
      if (text != null){
         System.out.print(text);
         res = scanner.nextLine();
      }
      return res;
   }

   @Override public String visitExprSubstitute(StrManipParser.ExprSubstituteContext ctx) {
      String res = null;
      String string = visit(ctx.expr(0));
      String pattern = visit(ctx.expr(1));
      String replacer = visit(ctx.expr(2));
      if(string != null && pattern != null && replacer != null){
         res = string.replaceAll(pattern, replacer);
      }
      return res;
   }

   @Override public String visitExprID(StrManipParser.ExprIDContext ctx) {
      String res = null;
      String identifier = ctx.Identifier().getText();
      if(!symbol_table.containsKey(identifier)){
         System.err.println("ERROR: Variable not defined: " + identifier);
      } else {
         res = symbol_table.get(identifier);
      }
      return res;
   }

   private Map<String, String> symbol_table = new HashMap<>();
   private Scanner scanner = new Scanner(System.in);
}
