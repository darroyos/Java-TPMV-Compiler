package tp.pr3.bc.jumps.conditionaljumps;

import tp.pr3.bc.ByteCode;
import tp.pr3.bc.jumps.ConditionalJump;
import tp.pr3.elements.CPU;

public class IfLeq extends ConditionalJump {
	
	public IfLeq(int n) {
		super(n);
	}
	
	@Override
	protected boolean compares(int c, int sc, CPU cpu) {
		if (sc <= c)
			return true;
		else {
			cpu.jump(this.instruction);
			return true;
		}
	}
	
	@Override
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("IFLEQ"))
			return new IfLeq(n);
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "IFLEQ " + this.instruction;
	}

}
