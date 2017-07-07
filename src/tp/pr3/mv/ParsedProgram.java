package tp.pr3.mv;

import tp.pr3.inst.Instruction;
import tp.pr3.exceptions.ArrayException;

public class ParsedProgram {
	Instruction[] pProgram;
	private final static int size = 5000;
	private int numInstructions;
	
	public ParsedProgram() {
		this.pProgram = new Instruction[size];
		this.numInstructions = 0;
	}
	
	/**
	 * @param ins La instrucción que se añadirá al programa analizado
	 * @throws ArrayException
	 */
	public void addInst(Instruction ins) throws ArrayException {
		if (!this.isFull()) {
			this.pProgram[this.numInstructions] = ins;
			this.numInstructions++;
		}
		else
			throw new ArrayException("Excepcion: Se ha superado la capacidad del programa");
	}
	
	/**
	 * @param k Posición de la instrucción
	 * @return
	 */
	public Instruction getInstruction(int k) {
		return this.pProgram[k];
	}
	
	/**
	 * @return Tamaño del programa analizado
	 */
	public int getSize() {
		return this.numInstructions;
	}
	
	/**
	 * Inicializa el programa analizado
	 */
	public void reset() {
		this.numInstructions = 0;
	}
	
	private boolean isFull() {
		if (this.numInstructions == size)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		String program = "";
		
		for (int i = 0; i < this.numInstructions; i++)
			program += this.pProgram[i].toString() + System.lineSeparator();
		
		return program;
	}

}
