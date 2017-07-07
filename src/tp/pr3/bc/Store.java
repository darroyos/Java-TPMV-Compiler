package tp.pr3.bc;

import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;

public class Store implements ByteCode {
	private int param;
	
	public Store(int param) {
		this.param = param;
	}

	@Override
	public void execute(CPU cpu) throws StackException {
		int temp;
		
		temp = cpu.pop();
		cpu.write(param, temp);
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("STORE")) {
			try {
				return new Store(Integer.parseInt(s[1]));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "STORE " + this.param;
	}

}
