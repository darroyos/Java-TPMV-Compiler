package tp.pr3.bc;

import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;

public class Load implements ByteCode {
	private int param;
	
	public Load(int param) {
		this.param = param;
	}

	@Override
	public void execute(CPU cpu) throws StackException {
		Integer val = cpu.read(param);
		if (val == null)
			throw new StackException("La posición de memoria no ha sido anteriormente escrita");
		else
			cpu.push(val);
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD")) {
			try {
				return new Load(Integer.parseInt(s[1]));
			} catch (NumberFormatException e) {
				return null;
			}
		}
			
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "LOAD " + this.param;
	}

}
