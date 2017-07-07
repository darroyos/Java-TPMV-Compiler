package tp.pr3.cm;

import tp.pr3.mv.Engine;
import tp.pr3.exceptions.BadFormatByteCodeException;
import tp.pr3.exceptions.ArrayException;

public class Replace implements Command {
	private int instruction;
	
	public Replace() {
		this.instruction = -1;
	}
	
	public Replace(int n) {
		this.instruction = n;
	}
	
	@Override
	public void execute(Engine engine) throws BadFormatByteCodeException, ArrayException {
		engine.executeReplace(this.instruction);
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("REPLACEBC")) {
			try {
				return new Replace(Integer.parseInt(s[1]));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		else
			return null;
	}
	
	@Override
	public String textHelp() {
		return this.toString() + ": Reemplaza la instruccion N por la solicitada al usuario" + System.lineSeparator();
	}
	
	@Override
	public String toString() {
		String str = "REPLACE ";
		if (this.instruction != -1) 
			str += this.instruction;
		else
			str += "N";
		
		return str;
	}

}
