package tp.pr3.inst.conditionals;

import tp.pr3.inst.Instruction;
import tp.pr3.mv.ParsedProgram;
import tp.pr3.elements.LexicalParser;
import tp.pr3.elements.Compiler;
import tp.pr3.exceptions.LexicalAnalysisException;
import tp.pr3.exceptions.ArrayException;

public class IfThen implements Instruction {
	private Condition cd;
	private ParsedProgram pP;
	
	public IfThen() {
		this.cd = new Equal();
		this.pP = new ParsedProgram();
	}
	
	public IfThen(Condition cd, ParsedProgram pP) {
		this.cd = cd;
		this.pP = pP;
	}
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		/*
		 * TOKENS primera línea del while
		 * 
		 * words[0] => if
		 * words[1] => Term
		 * words[2] => BooleanOper
		 * words[3] => Term
		 */
		
		if (words.length != 4 || !words[0].equalsIgnoreCase("IF"))
			return null;
		else {
			// Parseo de la condición
			Condition cond = ConditionParser.parse(words[1], words[2], words[3], lexParser);
			if (cond == null)
				return null;
			else {
				ParsedProgram wb = new ParsedProgram();
				
				try {
				lexParser.lexicalParser(wb, "ENDIF");
				} catch (LexicalAnalysisException e) {
					// Error en el análisis léxico del cuerpo del IfThen
					return null;
				}
				
				// lexParser aumenta su contador
				lexParser.increaseProgramCounter();
				
				return new IfThen(cond, wb);
			}
		}	
	}
	
	/*
	 * Para instrucciones IfThen condition body la compilación consiste en:
	 * 		(1) añadir los bytecodes correspondientes a la compilación de condition que se obtienen con el
	 * 			método de la clase abstracta Condition void compile (Compiler compiler) throws ...
	 * 		(2) añadir los bytecodes correspondientes a la compilación de body
	 * 		(3) la compilación de la condición da valor al atributo protegido ConditionalJump cj de la clase Condition
	 * 		(4) dar valor al atributo del salto cj dependiendo de lo que haya ocupado la compilación de body
	 */

	@Override	
	public void compile(Compiler compiler) throws ArrayException {
		// Compilamos la condición del IfThen
		this.cd.compile(compiler);
		// Compilamos el cuerpo del IfThen
		compiler.compile(this.pP);
		// Establecemos el salto ahora que lo sabemos, corresponde con el tamaño del programa
		this.cd.cj.setN(compiler.getSizeBcProgram());
	}

}
