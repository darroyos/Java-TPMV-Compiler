package tp.pr3.elements;

import tp.pr3.mv.ByteCodeProgram;
import tp.pr3.mv.ParsedProgram;
import tp.pr3.exceptions.ArrayException;
import tp.pr3.inst.Instruction;
import tp.pr3.bc.ByteCode;

/**
 * Esta clase es la encargada de generar el programa bytecode
 */
public class Compiler {
	private ByteCodeProgram bcProgram;
	private String[] varTable;
	private final static int size = 6000;
	private int numVariables;
	
	public Compiler() {
		this.bcProgram = null;
		this.varTable = new String[size];
		this.numVariables = 0;
	}
	
	/**
	 * Genera el correspondiente código bytecode al código fuente
	 * 
	 * @param pProgram El programa que se va a parsear
	 * @throws ArrayException
	 */
	public void compile(ParsedProgram pProgram) throws ArrayException {
		int i = 0;
		
		try {
			while (i < pProgram.getSize()) {
				Instruction instr = pProgram.getInstruction(i);
				instr.compile(this);
				i++;
			}
		} catch (ArrayException e) {
			throw new ArrayException("Excepcion: se alcanzo el tamaño maximo del programa");
		}
	}
	
	/**
	 * Si no se encuentra la variable en el índice la añade en la última posición libre
	 * 
	 * @param varName Nombre de la variable que se quiere buscar en el índice
	 * @return Posición de la variable en el índice
	 */
	public int indexOf(String varName) {
		boolean located = false;
		int i = 0, index = -1;
		
		while (i < this.numVariables && !located) {
			if (this.varTable[i].equalsIgnoreCase(varName)) {
				index = i;
				located = true;
			}
			if (!located)
				i++;
		}
		
		if (!located)
			index = this.addVariable(varName);
		
		return index;
	}
	
	/**
	 * @param bc El bytecode que se quiere añadir al código del programa
	 * @throws ArrayException
	 */
	public void addByteCode(ByteCode bc) throws ArrayException {
		this.bcProgram.addByteCode(bc, this.bcProgram.size());
	}
	
	/**
	 * @param varName La variable que se quiere añadir al índice
	 * @return Posición de la variable en el índice
	 */
	public int addVariable(String varName) {
		int index;
		
		this.varTable[this.numVariables] = varName;
		index = this.numVariables;
		this.numVariables++;
		
		return index;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.bcProgram.toString();
	}
	
	/**
	 * @return Devuelve el tamaño del código del programa
	 */
	public int getSizeBcProgram() {
		return this.bcProgram.size();
	}
	
	/**
	 * Carga el código del programa e inicializa la tabla de variables
	 * 
	 * @param bcProgram El código del programa que se quiere cargar en el compilador
	 */
	public void initialize(ByteCodeProgram bcProgram) {
		this.bcProgram = bcProgram;
		this.numVariables = 0;
	}

}
