import java.util.ArrayList;
import java.util.List;

/*

Build Ast using the method in ExprBaseVisitor.java 
you should override the methods.

*/


public class BuildAstVisitor  extends ExprBaseVisitor<AstNodes>{

	private List<String> vars;	// Stores all the variables from 'Declaration' cases
	private List<Double> vals;	// Stores all the values from 'Declaration' cases
	
	public BuildAstVisitor() {
		vars = new ArrayList<>();
		vals = new ArrayList<>();
	}
	
	@Override
	public AstNodes visitInfixExpr(ExprParser.InfixExprContext ctx) {
		AstNodes left = visit(ctx.getChild(0));
		String op = ctx.getChild(1).getText();
		AstNodes right = visit(ctx.getChild(2));
		
		return new Infix(left, op, right);
	}

	@Override
	public AstNodes visitParensExpr(ExprParser.ParensExprContext ctx) {
		AstNodes inside = visit(ctx.getChild(1));
		
		return new Parens(inside);
	}

	@Override
	public AstNodes visitIdExpr(ExprParser.IdExprContext ctx) {
		String id = ctx.getChild(0).getText();
		
		return new Variable(id);
	}

	@Override
	public AstNodes visitDecl(ExprParser.DeclContext ctx) {
		String id = ctx.getChild(0).getText();
		String valueText = ctx.getChild(2).getText();
		Double value = Double.parseDouble(valueText);
		
		if (vars.contains(id)){
			vals.set(vars.indexOf(id), value);
		}
		else {
			vars.add(id);
			vals.add(value);
		}
		
		return new Declaration(id, value);
	}
	
	@Override
	public AstNodes visitNumberExpr(ExprParser.NumberExprContext ctx) {
		String numberString = ctx.getChild(0).getText();
		return new Number(Double.parseDouble(numberString));
	}

	@Override
	public AstNodes visitSqrtExpr(ExprParser.SqrtExprContext ctx) {
		String numString = ctx.getChild(2).getText();
		return new SqrtNode(Double.parseDouble(numString));
	}

	@Override
	public AstNodes visitMathExpr(ExprParser.MathExprContext ctx) {
		String num1String = ctx.getChild(2).getText();
		String num2String = ctx.getChild(4).getText();
		String mathFunc = ctx.getChild(0).getText();
		
		return new MathNode(Double.parseDouble(num1String), Double.parseDouble(num2String), mathFunc);
	}
	
}

class BuildProgramVisitor extends ExprBaseVisitor<Program>{
	
	@Override
	public Program visitProg(ExprParser.ProgContext ctx) {
		Program prog = new Program();
		
		BuildAstVisitor astVisitor = new BuildAstVisitor();
		for(int i=0; i<ctx.getChildCount(); i++) {
			// check last	child => do not visit
			if(i == ctx.getChildCount()-1) {
				
			}
			else {
				prog.addNode(astVisitor.visit(ctx.getChild(i)));
			}
		}
		
		return prog;
	}
}
