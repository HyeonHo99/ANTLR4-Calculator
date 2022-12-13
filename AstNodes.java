import java.util.ArrayList;
import java.util.List;

/*

define Ast Nodes

*/

public class AstNodes{
	// (1) Constructor (2) toString
}

class Parens extends AstNodes{
	AstNodes inside;
	public Parens(AstNodes inside) {
		this.inside = inside;
	}
	
	public String toString() {
		return "(" + inside.toString() + ")";
	}
}

class Number extends AstNodes{
	double num;
	public Number(double num){
		this.num = num;
	}
	
	public String toString(){
		return Double.toString(num);
	}
}


class Declaration extends AstNodes{
	public	String id;
	public	double value;
	
	public Declaration(String id, double value){
		this.id = id;
		this.value = value;
	}
}

class Variable extends AstNodes{
	String id;
	
	public Variable(String id){
		this.id = id;
	}
	
	
	public String toString(){
		return id;
	}
}

class Infix extends AstNodes{
	AstNodes left;
	AstNodes right;
	String op;
	
	public Infix(AstNodes left, String op, AstNodes right){
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	
	public String toString(){
		return left.toString() + op + right.toString();
	}
}

class SqrtNode extends AstNodes{
	Double num;
	String mathFunc;
	
	public SqrtNode(Double num) {
		this.num = num;
	}
	
	public String toString() {
		String numString = Double.toString(num);
		return mathFunc + '(' + numString + ')';
	}
}

class MathNode extends AstNodes{
	Double num1;
	Double num2;
	String mathFunc;
	
	public MathNode(Double num1, Double num2, String mathFunc) {
		this.num1 = num1;
		this.num2 = num2;
		this.mathFunc = mathFunc;
	}
	
	public String toString() {
		String num1String = Double.toString(num1);
		String num2String = Double.toString(num2);
		
		return mathFunc + "(" + num1String + "," + num2String + ")";
	}
}

// Root of AST tree
class Program{
	public List<AstNodes> nodes;
	
	public Program() {
		this.nodes = new ArrayList<>();
	}
	
	public void addNode(AstNodes node) {
		nodes.add(node);
	}
}
