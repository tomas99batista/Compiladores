import java.util.*;

public class Execute extends StrLangBaseVisitor<String> {

   @Override public String visitPrint(StrLangParser.PrintContext ctx) {
      String res = visit(ctx.expr());
      if (res != null){
         System.out.println("> " + res);
      }
      return res;
   }

   @Override public String visitAssignment(StrLangParser.AssignmentContext ctx) {
      String res = visit(ctx.expr());
      if (res != null){
         String identifier = ctx.Identifier().getText();
         symbol_table.put(identifier, res);
      }
      return res;
   }

   @Override public String visitExprInput(StrLangParser.ExprInputContext ctx) {
      String res = null;
      String prompt = visit(ctx.expr());
      if (prompt != null){
         System.out.print(prompt);
         //Scan me now
         res = scan.nextLine();
      }
      return res;
   }

   @Override public String visitExprConcat(StrLangParser.ExprConcatContext ctx) {
      String res = null;
      String s1 = visit(ctx.expr(0));
      String s2 = visit(ctx.expr(1));
      if (s1 != null && s2 != null){
         res = s1 + s2;
      }
      return res;
   }

   @Override public String visitExprSubstitute(StrLangParser.ExprSubstituteContext ctx) {
      String res = null;
      String str = visit(ctx.expr(0));
      String substring = visit(ctx.expr(1));
      String new_substring = visit(ctx.expr(2));
      if (str != null && substring != null && new_substring!=null){
         res = str.replaceAll(substring, new_substring);
      }
      return res;
   }

   @Override public String visitExprString(StrLangParser.ExprStringContext ctx) {
      String res = ctx.String().getText();
      res = res.substring(1, res.length() - 1);
      return res;
   }

   @Override public String visitExprIdentifier(StrLangParser.ExprIdentifierContext ctx) {
      String res = null;
      String identifier = ctx.Identifier().getText();
      if(!symbol_table.containsKey(identifier)){
         System.err.println("ERRO! " + identifier + " - variável não definida");
      } else {
         res = symbol_table.get(identifier);
      }
      return res;
   }

   private Map<String, String> symbol_table = new HashMap<>();

   private Scanner scan = new Scanner(System.in);
}
