package tp.pr3.elements;

import tp.pr3.mv.SourceProgram;
import tp.pr3.mv.ParsedProgram;
import tp.pr3.inst.Instruction;
import tp.pr3.inst.InstructionParser;
import tp.pr3.exceptions.LexicalAnalysisException;
import tp.pr3.exceptions.ArrayException;

/**
 * Esta clase se encarga del análisis léxico
 */
public class LexicalParser {
	private SourceProgram sProgram;
	private int programCounter;
	
	public LexicalParser() {
		this.sProgram = null;
		this.programCounter = 0;
	}
	
	public void increaseProgramCounter() {
		this.programCounter++;
	}
	
	/**
	 * @param sProgram El código fuente que se quiere cargar en el analizador léxico
	 */
	public void initialize(SourceProgram sProgram) {
		this.sProgram = sProgram;
		this.programCounter = 0;
	}
	
	/**
	 * @param pProgram El programa analizado léxicamente
	 * @param stopKey Punto de finalización del análisis
	 * @throws LexicalAnalysisException
	 */
	public void lexicalParser(ParsedProgram pProgram, String stopKey) throws LexicalAnalysisException {
		boolean stop = false;
		
		while (this.programCounter < this.sProgram.getSize() && !stop) {
			String line = sProgram.getInstr(this.programCounter);
			
			if (line.equalsIgnoreCase(stopKey))
				stop = true;
			else {
				Instruction instruction = InstructionParser.parse(line, this);
				
				// Incorrecciones léxicas en la instrucción
				if (instruction == null)
					throw new LexicalAnalysisException("Excepcion-analisis: Incorrecion en la instruccion ["
													   + line + "]");
				else {
					// Si la instrucción no es null se añade al programa parseado
					try {
						pProgram.addInst(instruction);
					} catch (ArrayException e) {
						throw new LexicalAnalysisException(e.getMessage() + System.lineSeparator()
														   + "Imposible parsear la instruccion: " + line);
					}
				}
			}
		}
	}

}
