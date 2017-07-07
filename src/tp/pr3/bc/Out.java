package tp.pr3.bc;

import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;

public class Out implements ByteCode {
	
	@Override
	public void execute(CPU cpu) throws StackException {
		cpu.out();
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("OUT"))
			return new Out();
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "OUT";
	}

}
