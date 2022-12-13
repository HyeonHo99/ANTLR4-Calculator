import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Calculate The Input String
And Return the result

*/


class Evaluate {
	List<AstNodes> list;
	// HashMap for vals of vars
	public Map<String, Double> vals;
	
	public Evaluate(List<AstNodes> list) {
		this.list = list;
		vals = new HashMap<>();
	}
	
	public List<String> evaluateTree(){
		List<String> results = new ArrayList<>();
		for(AstNodes node: list) {
			if (node instanceof Declaration) {
				Declaration declaration = (Declaration) node;
				vals.put(declaration.id, declaration.value);
				results.add("0.0");
			}
			else {
				double result = evaluateNode(node);
				String resultString = Double.toString(result);
				results.add(resultString);
			}
		}
		return results;
	}
    
	private Double evaluateNode(AstNodes node) {
		Double result = 0.0;
		
		if(node instanceof Number) {
			Number numNode = (Number) node;
			result = numNode.num;
		}
		else if(node instanceof Infix) {
			Infix infixNode = (Infix) node;
			double left = evaluateNode(infixNode.left);
			double right = evaluateNode(infixNode.right);
			String op = infixNode.op;
			
			switch(op) {
			case "+": result = left+right;
						break;
			case "*": result = left*right;
						break;
			case "/": result = left/right;
						break;
			case "-": result = left-right;
						break;
			}
		}
		else if(node instanceof Variable) {
			Variable varNode = (Variable) node;
			result = vals.get(varNode.id);
		}
		else if(node instanceof Parens) {
			Parens parensNode = (Parens) node;
			result = evaluateNode(parensNode.inside);
		}
		else if(node instanceof SqrtNode) {
			SqrtNode sqrtNode = (SqrtNode) node;
			Double num = sqrtNode.num;
			result = Math.sqrt(num);
		}
		else if(node instanceof MathNode) {
			MathNode mathNode = (MathNode) node;
			Double num1 = mathNode.num1;
			Double num2 = mathNode.num2;
			String mathFunc = mathNode.mathFunc;
			
			switch(mathFunc) {
			case "pow": result = Math.pow(num1, num2);
						break;
			case "min": result = Math.min(num1, num2);
						break;
			case "max": result = Math.max(num1, num2);
						break;
			}
		}
		
		return result;
	}
}