import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class program {

    public static void main(String[] args) throws IOException {
                
        // Get Lexer
        ExprLexer lexer = new ExprLexer(CharStreams.fromStream(System.in));
        
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Pass tokens to parser
        ExprParser parser = new ExprParser(tokens);
        
		//write your code here
        ParseTree parseTree = parser.prog();
        BuildProgramVisitor progVisitor = new BuildProgramVisitor();
        Program prog = progVisitor.visit(parseTree);
        
        // null deletion
        for(Iterator<AstNodes> i=prog.nodes.iterator(); i.hasNext();) {
        	AstNodes node = i.next();
        	if(node == null) {
        		i.remove();
        	}
        }
        
        AstCall astCall = new AstCall(prog.nodes);
        astCall.CallTree();
        
        // Evaluate
        Evaluate evaluate = new Evaluate(prog.nodes);
        
        for(String result : evaluate.evaluateTree()) {
        	System.out.println(result);
        }
    }
}

