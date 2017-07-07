package tp.pr3.inst.assignments;

import tp.pr3.bc.ByteCode;
import tp.pr3.bc.Push;
import tp.pr3.elements.Compiler;

public class Number implements Term {
	private int num;
	
	public Number(int n) {
		this.num = n;
	}

	@Override
	public ByteCode compile(Compiler compile) {
		return new Push(this.num);
	}
	
	@Override
	public Term parse(String term) {
		int num;
		
		// ¡¡¡ NO ESTOY SEGURO DE QUE SEA CORRECTO!
		try {
			num = Integer.parseInt(term);
			// ¡es un entero!
			return new Number(num);
		} catch (NumberFormatException e) {
			// ¡no es un entero!
			return null;
		}
		
	}

	@Override
	public String toString() {
		return Integer.toString(this.num);
	}
}
