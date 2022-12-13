import java.util.List;

/*

print the Ast that we build in BuildAstVisitor.java

*/


public class AstCall {
	List<AstNodes> list;
	
    public AstCall(List<AstNodes> list) {
    	this.list = list;
    }
    
    public void CallTree() {
    	for(AstNodes node : list) {
    		if(node instanceof Declaration) {
    			Declaration declarationNode = (Declaration) node;
    			String id = declarationNode.id;
    			String value = Double.toString(declarationNode.value);
    			System.out.println("ASSIGN");
    			System.out.println("\t" + id);
    			System.out.println("\t" + value);
    		}
    		else {
    			CallNode(node, 0);
    		}
    	}
    }
    
    private void CallNode(AstNodes node, Integer indents) {
    	String indentation = "\t".repeat(indents);
    	
    	if(node instanceof Number) {
			Number numNode = (Number) node;
			String num = Double.toString(numNode.num);
			System.out.println(indentation + num);
		}
    	else if(node instanceof Infix) {
			Infix infixNode = (Infix) node;
			String op = infixNode.op;
			
			switch(op) {
			case "+": System.out.println(indentation + "ADD");
						break;
			case "*": System.out.println(indentation + "MUL");
						break;
			case "/": System.out.println(indentation + "DIV");
						break;
			case "-": System.out.println(indentation + "SUB");
						break;
			}
			
			CallNode(infixNode.left, indents+1);
			CallNode(infixNode.right, indents+1);
		}
    	else if(node instanceof Variable) {
			Variable varNode = (Variable) node;
			String id = varNode.id;
			System.out.println(indentation + id);
		}
		else if(node instanceof Parens) {
			Parens parensNode = (Parens) node;
			CallNode(parensNode.inside, indents);
		}
		else if(node instanceof SqrtNode) {
			SqrtNode sqrtNode = (SqrtNode) node;
			Double num = sqrtNode.num;
			String numString = Double.toString(num);
			
			System.out.println(indentation + "sqrt");
			System.out.println(indentation + "\t" + numString);
		}
		else if(node instanceof MathNode) {
			MathNode mathNode = (MathNode) node;
			String num1String = Double.toString(mathNode.num1);
			String num2String = Double.toString(mathNode.num2);
			String mathFunc = mathNode.mathFunc;
			
			switch(mathFunc) {
			case "pow": System.out.println(indentation + "pow");
						break;
			case "min": System.out.println(indentation + "min");
						break;
			case "max": System.out.println(indentation + "max");
						break;
			}
			
			System.out.println(indentation + "\t" + num1String);
			System.out.println(indentation + "\t" + num2String);
		}
 
    }
    
}
