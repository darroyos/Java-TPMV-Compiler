package tp.pr3.bc.jumps;

import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;

public abstract class ConditionalJump extends Jump {
	
	public ConditionalJump(int n) {
		super(n);
	}
	
	public void setN(int n) {
		this.instruction = n;
	}
	
	@Override
	public void execute(CPU cpu) throws StackException {
		int c, sc;
		
		c = cpu.pop();
		sc = cpu.pop();
		this.compares(c, sc, cpu);
	}
	
	protected abstract boolean compares(int c, int sc, CPU cpu);

}
