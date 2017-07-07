package tp.pr3.bc.arithmetics;

import tp.pr3.bc.ByteCode;
import tp.pr3.elements.CPU;
import tp.pr3.exceptions.StackException;
import tp.pr3.exceptions.DivByZeroException;

public class Div extends Arithmetics {
	
	@Override
	protected void operates(int c, int sc, CPU cpu) throws StackException, DivByZeroException {
		if (c == 0) // División por 0 prohibida
			throw new DivByZeroException("No es posible dividir " + sc + " / " + c);
		else		
			cpu.push(sc / c);
	}
	
	@Override
	protected ByteCode parseOperation(String s) {
		if (s.equalsIgnoreCase("DIV"))
			return new Div();
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "DIV";
	}

}
