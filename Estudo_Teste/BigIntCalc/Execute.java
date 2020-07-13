import java.math.BigInteger;
import java.util.*;

public class Execute extends BigIntCalcBaseVisitor<BigInteger> {

   @Override public BigInteger visitPrint(BigIntCalcParser.PrintContext ctx) {
      BigInteger res = visit(ctx.expr());
      System.out.println("> " + res);
      return res;
   }

   @Override public BigInteger visitAssign(BigIntCalcParser.AssignContext ctx) {
      BigInteger res = null;
      BigInteger number = visit(ctx.expr());
      if(res != null){
         switch (expr.signal.getText()) {
            case "+":
               res = number;
               break;
            case "-":
               res = number.negate();
               break;
         }
         String identifier = ctx.Identifier().getText();
         symbMap.put(identifier, res);
      } 

      return res;
   }

   @Override public BigInteger visitExprSignal(BigIntCalcParser.ExprSignalContext ctx) {
      BigInteger res = visit(ctx.expr());
      if(res != null){
         String identifier = ctx.Identifier().getText();
         symbMap.put(identifier, res);
      } 
      return res;
   }

   @Override public BigInteger visitExprMultDivMod(BigIntCalcParser.ExprMultDivModContext ctx) {
      BigInteger res = null;
      BigInteger e1 = visit(ctx.expr(0));
      BigInteger e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null){
         switch (ctx.op.getText()) {
            case "*":
               res = e1.multiply(e2);
               break;
            case "div":
               if(e1 == new BigInteger("0")){
                  System.err.println("ERROR: Divide by 0");
               } else{
                  res = e1.divide(e2);
               }
               break;
            case "mod":
               res = e1.remainder(e2);
               break;
         }
      }
      return res;
   }

   @Override public BigInteger visitExprPlusMinus(BigIntCalcParser.ExprPlusMinusContext ctx) {
      BigInteger res = null;
      BigInteger e1 = visit(ctx.expr(0));
      BigInteger e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null){
         switch (ctx.op.getText()) {
            case "+":
               res = e1.add(e2);
               break;
            
            case "-":
               res = e1.subtract(e2);
               break;
         }
      }
      return res;
   }

   @Override public BigInteger visitExprParentisis(BigIntCalcParser.ExprParentisisContext ctx) {
      BigInteger res = visit(ctx.expr());
      return res;
   }

   @Override public BigInteger visitExprIdentifier(BigIntCalcParser.ExprIdentifierContext ctx) {
      BigInteger res = null;
      String identifier = ctx.Identifier().getText();
      if(!symbMap.containsKey(identifier)){
         System.err.println("ERROR: Variable not defined: " + identifier);
      } else {
         res = symbMap.get(identifier);
      }
      return res;
   }

   @Override public BigInteger visitExprNum(BigIntCalcParser.ExprNumContext ctx) {
      BigInteger res = new BigInteger(ctx.Num().getText());
      return res;
   }

   private Map<String, BigInteger> symbMap = new HashMap<>();
}
