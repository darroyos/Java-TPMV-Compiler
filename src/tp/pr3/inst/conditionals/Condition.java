package tp.pr3.inst.conditionals;

import tp.pr3.bc.jumps.ConditionalJump;
import tp.pr3.inst.assignments.TermParser;
import tp.pr3.inst.assignments.Term;
import tp.pr3.inst.assignments.Number;
import tp.pr3.elements.Compiler;
import tp.pr3.elements.LexicalParser;
import tp.pr3.exceptions.ArrayException;

public abstract class Condition {
	protected ConditionalJump cj; // atributo para la compilación
	private Term t1;
	private Term t2;
	
	public Condition() {
		this.cj = null;
		this.t1 = new Number(-1);
		this.t2 = new Number(-1);
	}
	
	public Condition(Term t1, Term t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	public void compile(Compiler compiler) throws ArrayException {
		ConditionalJump cond;
		
		compiler.addByteCode(this.t1.compile(compiler));
		compiler.addByteCode(this.t2.compile(compiler));
		
		// Damos valor al atributo ConditionalJump cj para la compilación
		cond = this.compileOp();
		this.cj = cond;
		// Se añade al ByteCodeProgram, aunque todavía no sabemos el salto
		compiler.addByteCode(cond);
	}
	
	protected abstract ConditionalJump compileOp();
	
	public Condition parse(String t1, String op, String t2, LexicalParser parser) {
		this.t1 = TermParser.parse(t1);
		this.t2 = TermParser.parse(t2);
		
		if (this.t1 == null || this.t2 == null)
			return null;
		else {
			return this.parseOp(this.t1, op, this.t2, parser);
		}
	}
	
	protected abstract Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser);

}
