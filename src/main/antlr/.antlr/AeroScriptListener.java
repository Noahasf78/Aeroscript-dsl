// Generated from c:/Users/noaha/OneDrive/Dokumenter/IN2031/oblig3/aeroscript/aeroscript/src/main/antlr/AeroScript.g4 by ANTLR 4.13.1

package no.uio.aeroscript.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AeroScriptParser}.
 */
public interface AeroScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(AeroScriptParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(AeroScriptParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#execution}.
	 * @param ctx the parse tree
	 */
	void enterExecution(AeroScriptParser.ExecutionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#execution}.
	 * @param ctx the parse tree
	 */
	void exitExecution(AeroScriptParser.ExecutionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AeroScriptParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AeroScriptParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#reaction}.
	 * @param ctx the parse tree
	 */
	void enterReaction(AeroScriptParser.ReactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#reaction}.
	 * @param ctx the parse tree
	 */
	void exitReaction(AeroScriptParser.ReactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#event}.
	 * @param ctx the parse tree
	 */
	void enterEvent(AeroScriptParser.EventContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#event}.
	 * @param ctx the parse tree
	 */
	void exitEvent(AeroScriptParser.EventContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(AeroScriptParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(AeroScriptParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#acAscend}.
	 * @param ctx the parse tree
	 */
	void enterAcAscend(AeroScriptParser.AcAscendContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#acAscend}.
	 * @param ctx the parse tree
	 */
	void exitAcAscend(AeroScriptParser.AcAscendContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#acMove}.
	 * @param ctx the parse tree
	 */
	void enterAcMove(AeroScriptParser.AcMoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#acMove}.
	 * @param ctx the parse tree
	 */
	void exitAcMove(AeroScriptParser.AcMoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#acTurn}.
	 * @param ctx the parse tree
	 */
	void enterAcTurn(AeroScriptParser.AcTurnContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#acTurn}.
	 * @param ctx the parse tree
	 */
	void exitAcTurn(AeroScriptParser.AcTurnContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#acDock}.
	 * @param ctx the parse tree
	 */
	void enterAcDock(AeroScriptParser.AcDockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#acDock}.
	 * @param ctx the parse tree
	 */
	void exitAcDock(AeroScriptParser.AcDockContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#acDescend}.
	 * @param ctx the parse tree
	 */
	void enterAcDescend(AeroScriptParser.AcDescendContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#acDescend}.
	 * @param ctx the parse tree
	 */
	void exitAcDescend(AeroScriptParser.AcDescendContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(AeroScriptParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(AeroScriptParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#point}.
	 * @param ctx the parse tree
	 */
	void enterPoint(AeroScriptParser.PointContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#point}.
	 * @param ctx the parse tree
	 */
	void exitPoint(AeroScriptParser.PointContext ctx);
	/**
	 * Enter a parse tree produced by {@link AeroScriptParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(AeroScriptParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AeroScriptParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(AeroScriptParser.RangeContext ctx);
}