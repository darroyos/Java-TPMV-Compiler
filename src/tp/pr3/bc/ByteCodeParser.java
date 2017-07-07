package tp.pr3.bc;

import tp.pr3.bc.arithmetics.*;
import tp.pr3.bc.jumps.*;
import tp.pr3.bc.jumps.conditionaljumps.*;

/**
 * En este caso es la
 * clase encargada de parsear un string que contiene un posible bytecode. Concretamente
 * dispone de un método public static ByteCode parse(String line) que devuelve
 * el bytecode almacenado en s o bien null, si s no representa ninguna instrucción.
 *
 */
public class ByteCodeParser {
	
	private final static ByteCode[] bytecodes =
		{ new Add(), new Sub(), new Mul(), new Div(),
		  new Push(0), new Store(0), new Load(0),
		  new Halt(), new Out(), new Goto(0),
		  new IfEq(0), new IfNeq(0),
		  new IfLe(0), new IfLeq(0) };

	public static ByteCode parse(String line) {
		String[] tokens;
		ByteCode bytecode;
		
		// Quitar blancos y dividir en tokens
		line = line.trim();
		tokens = line.split(" +");
		
		if (tokens.length == 0 || tokens.length > 2) 
			return null;
		
		else {
			/* Hasta que uno de los ByteCodes sea distinto de null,
			 * recorremos todos los posibles y lo devolvemos
			 */
			
			for (ByteCode bc : bytecodes) {
				bytecode = bc.parse(tokens);
				if (bytecode != null)
					return bytecode;
			}
			return null;
		}
	}
}
