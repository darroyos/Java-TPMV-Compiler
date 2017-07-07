package tp.pr3.inst;

import tp.pr3.elements.Compiler;
import tp.pr3.elements.LexicalParser;
import tp.pr3.exceptions.ArrayException;

public interface Instruction {
	
	/**
	 * Analizador léxico de instrucciones
	 * 
	 * @param words Tokens de la instrucci'on
	 * @param lexParser Analizador léxico
	 * @return Instruction Una instrucción parseada
	 */
	Instruction lexParse(String[] words, LexicalParser lexParser);
	
	/**
	 * Compilador de instrucciones
	 * 
	 * @param compiler El compilador
	 * @throws ArrayException
	 */
	void compile(Compiler compiler) throws ArrayException;
}
