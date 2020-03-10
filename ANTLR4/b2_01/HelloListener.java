// Generated from Hello.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#greetings}.
	 * @param ctx the parse tree
	 */
	void enterGreetings(HelloParser.GreetingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#greetings}.
	 * @param ctx the parse tree
	 */
	void exitGreetings(HelloParser.GreetingsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#a}.
	 * @param ctx the parse tree
	 */
	void enterA(HelloParser.AContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#a}.
	 * @param ctx the parse tree
	 */
	void exitA(HelloParser.AContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#b}.
	 * @param ctx the parse tree
	 */
	void enterB(HelloParser.BContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#b}.
	 * @param ctx the parse tree
	 */
	void exitB(HelloParser.BContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(HelloParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(HelloParser.NameContext ctx);
}