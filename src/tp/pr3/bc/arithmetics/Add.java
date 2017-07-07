package tp.pr3.bc.arithmetics;

import tp.pr3.bc.ByteCode;
import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;

public class Add extends Arithmetics {
	
	@Override
	protected void operates(int c, int sc, CPU cpu) throws StackException {
		cpu.push(sc + c);
	}
	
	@Override
	protected ByteCode parseOperation(String s) {
		if (s.equalsIgnoreCase("ADD"))
			return new Add();
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "ADD";
	}

}
