package tp.pr3.inst.assignments;

import tp.pr3.elements.Compiler;
import tp.pr3.elements.LexicalParser;
import tp.pr3.inst.Instruction;
import tp.pr3.bc.arithmetics.*;
import tp.pr3.bc.*;
import tp.pr3.exceptions.ArrayException;

public class CompoundAssignment implements Instruction {
	private String varName;
	private String operator;
	private Term t1;
	private Term t2;
	
	public CompoundAssignment() {
		this.varName = "";
		this.operator = "";
		this.t1 = new Number(-1);
		this.t2 = new Number(-1);
	}
	
	public CompoundAssignment(String varName, String operator, Term t1, Term t2) {
		this.varName = varName;
		this.operator = operator;
		this.t1 = t1;
		this.t2 = t2;
	}
	
	/*
	 * Para asignaciones compuestas varName = term1 operator term2 la compilación consiste en:
	 *		(1) añadir el bytecode correspondiente a la compilación de term1
	 *		(2) idem term2
	 *		(3) añadir new Add() o new Sub() o dependiendo de lo que sea operator
	 *		(4) añadir Store i, donde i es el lugar de la memoria donde se guarda varName
	 */

	@Override
	public void compile(Compiler compiler) throws ArrayException {		
		 compiler.addByteCode(t1.compile(compiler));
		 compiler.addByteCode(t2.compile(compiler));
		 
		 switch (this.operator) {
		 case "+": compiler.addByteCode(new Add()); break;
		 case "-": compiler.addByteCode(new Sub()); break;
		 case "/": compiler.addByteCode(new Div()); break;
		 case "*": compiler.addByteCode(new Mul()); break;
		 }
		 
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
		 * words[3] => ArithmeticOper
		 * words[4] => Term 
		 */
		
		// Parseamos la variable para comprobar que devuelve algo distinto de null
		var = var.parse(words[0]);
		
		// Incorrecciones detectadas
		 if (words.length != 5 || var == null || !words[1].equals("="))
			 return null;
		 else {
			 // Parseo del primer operando
			 Term term1 = TermParser.parse(words[2]);
			 if (term1 == null)
				 return null;
			 // Parseo del operador aritmético
			 else if (isAnArithmeticOper(words[3])) {
				 // Parseo del segundo operando
				 Term term2 = TermParser.parse(words[4]);
				 if (term2 == null)
					 return null;
				 else {
					// lexParser aumenta su contador
					 lexParser.increaseProgramCounter();
					 // La instrucción parseada
					 return new CompoundAssignment(var.toString(), words[3], term1, term2);
				 }
			 }
			 return null;
		 }
	}
	
	private static boolean isAnArithmeticOper(String str) {
		switch (str) {
		case "+": return true;
		case "-": return true;
		case "/": return true;
		case "*": return true;
		default: return false;
		}
	}
	
	@Override
	public String toString() {
		return this.varName + " = " + this.t1.toString() + " " + this.operator + " "
				+ this.t2.toString();
	}

}
