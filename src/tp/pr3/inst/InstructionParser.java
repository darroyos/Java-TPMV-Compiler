package tp.pr3.inst;

import tp.pr3.inst.assignments.SimpleAssignment;
import tp.pr3.inst.assignments.CompoundAssignment;
import tp.pr3.inst.Write;
import tp.pr3.inst.Return;
import tp.pr3.inst.conditionals.While;
import tp.pr3.inst.conditionals.IfThen;
import tp.pr3.elements.LexicalParser;

public class InstructionParser {
	
	private final static Instruction[] instructions =
		{ new SimpleAssignment(), new CompoundAssignment(),
		  new Write(), new Return(), new While(), new IfThen() };
	
	public static Instruction parse(String line, LexicalParser parser) {
		String[] tokens;
		Instruction ins;
		
		// Quitar blancos iniciales y dividir en tokens
		line = line.trim();
		tokens = line.split(" +");
		
		if (tokens.length == 0 || tokens.length > 5) 
			// La máxima longitud de una instrucción es 5 (CompoundAssignment)
			return null;
		
		else {
			/* Hasta que una de las instrucciones sea distinta de null,
			 * recorremos todas las posibles y la devolvemos
			 */
			for (Instruction i : instructions) {
				ins = i.lexParse(tokens, parser);
				if (ins != null) 
					return ins;
			}
			return null;
		}
	}
}