package tp.pr3.inst.assignments;

import tp.pr3.inst.Instruction;
import tp.pr3.elements.Compiler;
import tp.pr3.elements.LexicalParser;
import tp.pr3.bc.Store;
import tp.pr3.exceptions.ArrayException;

public class SimpleAssignment implements Instruction {
	private String varName;
	private Term rhs;
	
	public SimpleAssignment() {
		this.varName = "";
		this.rhs = new Number(-1);
	}
	
	public SimpleAssignment(String varName, Term rhs) {
		this.varName = varName;
		this.rhs = rhs;
	}
	
	/*
	 * La compilación de una asignación simple consiste en:
	 * 		(1) añadir el bytecode correspondiente a la compilación del lado derecho
	 * 		(2) añadir Store i
	 */
	
	@Override
	public void compile(Compiler compiler) throws ArrayException {
		compiler.addByteCode(this.rhs.compile(compiler));
		compiler.addByteCode(new Store(compiler.indexOf(this.varName)));		
	}
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		Term var = new Variable("");
		
		/*
		 * TOKENS
		 * 
		 * words[0] => Variable
		 * words[1] => "="
		 * words[2] => Term
		 */
		
		// Parseamos la variable para comprobar que devuelve algo distinto de null
		var = var.parse(words[0]);
		
		if (words.length != 3 || var == null || !words[1].equals("="))
			return null;
		else {
			Term rhs = TermParser.parse(words[2]);
			if (rhs == null)
				return null;
			else {
				// lexParser aumenta su contador
				lexParser.increaseProgramCounter();
				// La instrucción parseada
				return new SimpleAssignment(var.toString(), rhs);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.varName + " = " + this.rhs.toString();
	}

}
