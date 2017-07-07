package tp.pr3.inst;

import tp.pr3.elements.LexicalParser;
import tp.pr3.elements.Compiler;
import tp.pr3.inst.assignments.Term;
import tp.pr3.inst.assignments.Variable;
import tp.pr3.bc.Load;
import tp.pr3.bc.Out;
import tp.pr3.exceptions.ArrayException;

public class Write implements Instruction {
	private String varName;
	
	public Write() {
		this.varName = "";
	}
	
	public Write(String varName) {
		this.varName = varName;
	}
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		/*
		 * TOKENS
		 * 
		 * words[0] => write
		 * words[1] => Variable
		 */
		
		Term var = new Variable("");
		
		if (words.length == 2 && words[0].equalsIgnoreCase("WRITE")) {
			
			var = var.parse(words[1]); // Comprobamos que se trata de una variable
			if (var != null) {
			
				// lexParser aumenta su contador
				lexParser.increaseProgramCounter();
			
				return new Write(var.toString());
			}
			return null;
		}
		else
			return null;
	}

	@Override
	public void compile(Compiler compiler) throws ArrayException {
		compiler.addByteCode(new Load(compiler.indexOf(varName)));
		compiler.addByteCode(new Out());
	}

}
