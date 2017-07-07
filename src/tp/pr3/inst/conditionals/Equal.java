package tp.pr3.inst.conditionals;

import tp.pr3.inst.assignments.Term;
import tp.pr3.elements.LexicalParser;
import tp.pr3.bc.jumps.ConditionalJump;
import tp.pr3.bc.jumps.conditionaljumps.IfEq;

public class Equal extends Condition {
	
	public Equal() {
		super();
	}
	
	public Equal(Term t1, Term t2) {
		super(t1, t2);
	}

	@Override
	protected ConditionalJump compileOp() {
		return new IfEq(0);
	}
	
	@Override
	protected Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser) {
		if (op.equals("=")) {
			lexParser.increaseProgramCounter();
			return new Equal(t1, t2);
		}
		else
			return null;
	}
	
}
