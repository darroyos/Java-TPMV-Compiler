package tp.pr3.bc;

import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;

public class Push implements ByteCode {
	private int param;
	
	public Push(int param) {
		this.param = param;
	}

	@Override
	public void execute(CPU cpu) throws StackException {
		cpu.push(param);
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) {
			try {
				return new Push(Integer.parseInt(s[1]));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "PUSH " + this.param;
	}

}
