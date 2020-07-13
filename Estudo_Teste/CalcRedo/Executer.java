import java.lang.annotation.Retention;
import java.util.*;

public class Executer extends CalcRedoBaseVisitor<Double> {

   @Override public Double visitPrint(CalcRedoParser.PrintContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null){
         System.out.println("» " + res);
      }
      return res;
   }

   @Override public Double visitAssign(CalcRedoParser.AssignContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null){
         String id = ctx.Id().getText();
         symbol_table.put(id, res);
      }
      return res;
   }

   @Override public Double visitExprMultDivMod(CalcRedoParser.ExprMultDivModContext ctx) {
      Double res = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null){
         switch (ctx.op.getText()) {
            case "x":
               res = e1 * e2;               
               break;
            
            case "/":
               res = e1 / e2;
               break;
            case "%":
               res = e1 % e2;
               break;
         }
      }
      return res;
   }

   @Override public Double visitExprSumMinus(CalcRedoParser.ExprSumMinusContext ctx) {
      Double res = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null){
         switch(ctx.op.getText()){
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

   @Override public Double visitExprParentisis(CalcRedoParser.ExprParentisisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Double visitExprID(CalcRedoParser.ExprIDContext ctx) {
      Double res = null;
      String id = ctx.Id().getText();
      if(!symbol_table.containsKey(id)){
         System.err.println("ERROR: Variable not defined!");
      } else {
         res = symbol_table.get(id);
      }
      return res;
   }

   @Override public Double visitExprNum(CalcRedoParser.ExprNumContext ctx) {
      Double res = Double.parseDouble(ctx.Num().getText());
      return res;
   }

   Map<String, Double> symbol_table = new HashMap<>();
}
