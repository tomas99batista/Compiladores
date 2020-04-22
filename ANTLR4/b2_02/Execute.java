public class Execute extends SuffixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(SuffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(SuffixCalculatorParser.StatContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }

   @Override public Double visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      Double expr1 = visit(ctx.expr(0));
      Double expr2 = visit(ctx.expr(1));
      String operator = ctx.op.getText();
      Double res = null;
      switch(operator){
         case "*":
            res = expr1 * expr2;
            break;
         case "/":
            res = expr1 / expr2;
            break;
         case "+":
            res = expr1 + expr2;
            break;
         case "-":
            res = expr1 - expr2;
            break;
      }
      System.out.println("Result: " + res);
      return null;
   }
}
