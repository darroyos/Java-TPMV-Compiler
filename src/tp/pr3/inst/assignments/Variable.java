package tp.pr3.inst.assignments;

import tp.pr3.bc.ByteCode;
import tp.pr3.bc.Load;
import tp.pr3.elements.Compiler;

public class Variable implements Term {
	private String varName;
	
	public Variable(String var) {
		this.varName = var;
	}
	
	@Override
	public ByteCode compile(Compiler compile) {
		int i = compile.indexOf(varName);
		
		return new Load(i);
	}
	
	@Override
	public Term parse(String term) {
		if (term.length() != 1)
			return null;
		else{
			char name = term.charAt(0);
			if ('a' <= name && name <= 'z')
				return new Variable(term);
			else
				return null;
		}
	}
	
	@Override
	public String toString() {
		return this.varName;
	}

}
