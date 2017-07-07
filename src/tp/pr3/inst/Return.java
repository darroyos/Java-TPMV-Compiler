package tp.pr3.inst;

import tp.pr3.elements.LexicalParser;
import tp.pr3.elements.Compiler;
import tp.pr3.bc.Halt;
import tp.pr3.exceptions.ArrayException;

public class Return implements Instruction {
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		/*
		 * TOKENS
		 * 
		 * words[0] => RETURN
		 */
		
		if (words.length == 1 && words[0].equalsIgnoreCase("RETURN")) {
			// lexParser aumenta su contador
			lexParser.increaseProgramCounter();
			
			return new Return();
		}
		else
			return null;
	}
	
	@Override
	public void compile(Compiler compiler) throws ArrayException {
		compiler.addByteCode(new Halt());
	}

}
