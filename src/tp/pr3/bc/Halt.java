package tp.pr3.bc;

import tp.pr3.elements.CPU;

public class Halt implements ByteCode {
	
	@Override
	public void execute(CPU cpu) {
		cpu.finish();
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("HALT"))
			return new Halt();
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "HALT";
	}

}
