package tp.pr3.bc.jumps;

import tp.pr3.bc.ByteCode;
import tp.pr3.elements.CPU;

public class Goto extends Jump {
	
	public Goto(int n) {
		super(n);
	}
	
	@Override
	public void execute(CPU cpu) {
		cpu.jump(this.instruction);
	}
	
	@Override
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("GOTO"))
			return new Goto(n);
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "GOTO " + this.instruction;
	}

}
