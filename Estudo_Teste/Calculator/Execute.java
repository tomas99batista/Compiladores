import java.util.HashMap;
import java.util.Map;

public class Execute extends CalculatorBaseVisitor<Double> {

   @Override
   public Double visitPrint(CalculatorParser.PrintContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null) {
         System.out.println("> " + res);
      }
      return res;
   }

   @Override
   public Double visitAssign(CalculatorParser.AssignContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null) {
         String identifier = ctx.IDENTIFIER().getText();
         symMap.put(identifier, res);
      }
      return res;
   }

   @Override public Double visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      Double res = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null){
         switch(ctx.op.getText()){
            case "*":
               res = e1 * e2;
               break;
            case "/":
               if (e2 == 0){
                  System.err.println("ERROR: Divide by 0.");
               } else {
                  res = e1 / e2;
               }
               break;               
            case "mod":
               res = e1 % e2;
               break;
         }
      }
      return res;
   }

   @Override
   public Double visitExprPlusMinus(CalculatorParser.ExprPlusMinusContext ctx) {
      Double res = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         switch (ctx.op.getText()) {
            case "+":
               res = e1 + e2;
               break;
            case "-":
               res = e1 - e2;
               break;
         }
      }
      return res;
   }

   @Override
   public Double visitExprParentisis(CalculatorParser.ExprParentisisContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Double visitExprIdentifier(CalculatorParser.ExprIdentifierContext ctx) {
      Double res = null;
      String identifier = ctx.IDENTIFIER().getText();
      if (!symMap.containsKey(identifier)) {
         System.err.println("Variable not defined: " + identifier);
      } else {
         res = symMap.get(identifier);
      }
      return res;
   }

   @Override
   public Double visitExprNum(CalculatorParser.ExprNumContext ctx) {
      Double res = Double.parseDouble(ctx.NUM().getText());
      return res;
   }

   private Map<String, Double> symMap = new HashMap<>();
}