package tp.pr3.mv;

import tp.pr3.exceptions.ArrayException;

public class SourceProgram {
	String[] sProgram;
	private final static int size = 5000;
	private int numInstructions;
	
	public SourceProgram() {
		this.sProgram = new String[size];
		this.numInstructions = 0;
	}
	
	/**
	 * @param ins La instruccion que se añade al código fuente
	 * @throws ArrayException
	 */
	public void addInst(String ins) throws ArrayException {
		if (!this.isFull()) {
			this.sProgram[this.numInstructions] = ins;
			this.numInstructions++;
		}
		else
			throw new ArrayException("Excepcion: Se ha superado la capacidad del codigo fuente");
	}
	
	/**
	 * @param k Posición de la instrucción
	 * @return La instrucción
	 */
	public String getInstr(int k) {
		return this.sProgram[k];
	}
	
	/**
	 * @return Tamaño del código fuente
	 */
	public int getSize() {
		return this.numInstructions;
	}
	
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
		int numIndents = 0;
		
		String source = "Programa fuente almacenado:" + System.lineSeparator();
		
		// Salimos de un bloque indentado
		for (int i = 0; i < this.numInstructions; i++) {
			if (this.sProgram[i].contains("endwhile") || this.sProgram[i].contains("ENDWHILE")
					|| this.sProgram[i].contains("endif") || this.sProgram[i].contains("ENDIF"))
				numIndents--;
			
			// Línea de la instrucción
			source += i + ": ";
			
			// Aplicamos la indentación
			for (int j = 0; j < numIndents; j++)
				source += "    ";
			
			// Añadimos la instrucción
			source += this.sProgram[i] + System.lineSeparator();
			
			// Entramos en un bloque que tiene indentación
			if (this.sProgram[i].contains("while ") || this.sProgram[i].contains("WHILE ")
					|| this.sProgram[i].contains("if ") || this.sProgram[i].contains("IF "))
				numIndents++;
		}
			
		return source;
	}

}
