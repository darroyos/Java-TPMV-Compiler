package tp.pr3.bc.arithmetics;

import tp.pr3.bc.ByteCode;
import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;
import tp.pr3.exceptions.DivByZeroException;

public abstract class Arithmetics implements ByteCode {
	
	protected abstract void operates(int c, int sc, CPU cpu) throws StackException, DivByZeroException;
	protected abstract ByteCode parseOperation(String s);
	
	@Override
	public void execute(CPU cpu) throws StackException, DivByZeroException {
		int c, sc;
		
		c = cpu.pop();
		sc = cpu.pop();
		
		this.operates(c, sc, cpu);
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 1) {
			return this.parseOperation(s[0]);
		}
		else
			return null;
	}
	
}
