package tp.pr3.inst.assignments;

import tp.pr3.bc.ByteCode;
import tp.pr3.elements.Compiler;

public interface Term {
	
	ByteCode compile(Compiler compile);
	
	Term parse(String term);

}
