package tp.pr3.mv;

import tp.pr3.bc.ByteCode;
import tp.pr3.exceptions.ArrayException;

public class ByteCodeProgram {
	private ByteCode[] program;
	private static final int size = 10000;
	private int numInstructions;
	
	public ByteCodeProgram() {
		this.program = new ByteCode[size];
		this.numInstructions = 0;
	}
	
	/**
	 * @param byteCode
	 * @param pos Posición en el ByteCodeProgram donde se quiere añadir la instrucción
	 * @return Si el proceso ha sido satisfactorio
	 */
	public boolean addByteCode(ByteCode byteCode, int pos) throws ArrayException {
		boolean ok = true;
		
		if (this.isFull()) {
			ok = false;
			throw new ArrayException("Excepcion: Se ha superado el tamaño maximo del codigo maquina");
		}
		// Reemplazamos la instrucción
		else if (pos >= 0 && pos < this.numInstructions) {
			this.program[pos] = byteCode;
			// if (pos + 1 > this.numInstructions)
				// this.numInstructions++;
		}
		/*
		 * Si pos no es un número mayor o igual que 0 y 
		 * menor que el número de instrucciones, se añade en la siguiente posición libre.
		 */
		else {
			this.program[this.numInstructions] = byteCode;
			this.numInstructions++;
		}

		return ok;
	}
	
	public void reset() {
		this.numInstructions = 0;
	}
	
	/**
	 * @param pos Instrucción que se quiere ejecutar
	 * @return ByteCode La instrucción solicitada
	 */
	public ByteCode getInst(int pos) throws ArrayException {
		if (this.exists(pos))
			return this.program[pos];
		else
			throw new ArrayException("Excepcion: No existe la instruccion solicitada");
	}
	
	/**
	 * @return Si está lleno el ByteCodeProgram
	 */
	private boolean isFull() {
		if(this.numInstructions == size)
			return true;
		else
			return false;
	}
	
	/**
	 * @return El número de instrucciones
	 */
	public int size() {
		return this.numInstructions;
	}
	
	/**
	 * @param pos Instrucción
	 * @return Si existe la instrucción indicada
	 */
	private boolean exists(int pos) {
		return (this.numInstructions > 0) && (pos >= 0) && (pos <= (this.numInstructions - 1));
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String program = "Programa bytecode almacenado:" + System.lineSeparator();
		
		for (int i = 0; i < this.numInstructions; i++)
			program += i + ": " + this.program[i].toString() + System.lineSeparator();
		
		return program;
	}
	
}

