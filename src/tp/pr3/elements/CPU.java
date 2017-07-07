package tp.pr3.elements;

import tp.pr3.bc.ByteCode;
import tp.pr3.mv.ByteCodeProgram;
import tp.pr3.exceptions.ArrayException;
import tp.pr3.exceptions.StackException;
import tp.pr3.exceptions.ExecutionErrorException;

public class CPU {
	private ByteCodeProgram bcProgram;
	private Memory memory;
	private OperandStack stack;
	private boolean powerOff;
	private int programCounter;
	
	public CPU() {
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.bcProgram = new ByteCodeProgram();
		this.powerOff = false;
		this.programCounter = 0;		
	}
	
	/**
	 * @param bc ByteCode que se quiere insertar
	 * @param pos Posición donde se quiere añadir
	 */
	public void addByteCode(ByteCode bc, int pos) throws ArrayException {
		this.bcProgram.addByteCode(bc, pos);
	}
	
	/**
	 * @return Si está vacía la pila de operandos
	 */
	public boolean emptyStack() {
		return this.stack.emptyStack();
	}
	
	/**
	 * @return Si se ha alcanzado el límite de elementos de la pila
	 */
	public boolean stackOverflow() {
		return this.stack.overflow();
	}
	
	/**
	 * Apaga la CPU
	 */
	public void finish() {
		this.powerOff = true;
	}
	
	/**
	 * Inicializa la memoria y la pila de operandos
	 */
	public void initialize() {
		this.memory.empty();
		this.stack.toString(); // Vacía la pila
	}
	
	/**
	 * @param n Instrucción a la que se desea saltar
	 */
	public void jump(int n) {
		if (n >= this.bcProgram.size())
			this.programCounter = this.bcProgram.size();
		else if (n >= 0)
			this.programCounter = n;
	}
	
	/**
	 * Aumenta el contador del programa en 1
	 */
	public void next() {		
		this.programCounter++;
	}
	
	/**
	 * Imprime el último valor de la pila
	 */
	public void out() throws StackException {
		int temp;

		temp = this.stack.pop();
		System.out.println("consola: " + temp);
	}
	
	/**
	 * @return Entero que existe en la cima de la pila
	 */
	public int pop() throws StackException {
		return this.stack.pop();
	}
	
	/**
	 * @param n Entero que se quiere insertar en la pila de operandos
	 */
	public void push(int n) throws StackException {
		this.stack.push(n);
	}
	
	/**
	 * @param pos Posición de memoria a la que se quiere acceder
	 * @return El valor que hay en la posición de memoria indicada
	 */
	public Integer read(int pos) {
		return this.memory.read(pos);
	}
	
	/**
	 * Inicializa el ByteCodeProgram y la CPU
	 */
	public void reset() {
		this.bcProgram.reset();
		this.initialize(); // Vacía memoria y pila
		this.programCounter = 0;
		this.powerOff = false;
	}
	
	/**
	 * Ejecuta instrucciones del programa hasta que se de una de las siguientes condiciones: 
	 * (1) se alcance Halt, (2) el contador c es menor que 0 o mayor o igual que el número de instrucciones, 
	 * (3) se ha realizado una operación errónea (como dividir por 0 o cargar 
	 * el contenido de una posición de memeoria no usada).
	 */
	public void run() throws ArrayException, ExecutionErrorException {
		ByteCode bc;

		while (this.isAcorrectExecution()) {

			bc = this.bcProgram.getInst(this.programCounter);
			
			this.next(); // Apuntamos a la siguiente instrucción a ejecutar 
			
			// Intentamos ejecutar el ByteCode
			
			try {
				bc.execute(this);
			} catch (ExecutionErrorException e) {
				throw new ExecutionErrorException(
						"[error en linea #" + Integer.toString(this.programCounter - 1) + "] => "
						+ "Excepcion-bytecode "+ bc.toString()
						+ ": " + e.getMessage());
			}
		}
		
		System.out.println("El estado de la maquina tras ejecutar el programa es:" + 
															System.lineSeparator());
		// Mostramos el estado de la CPU
		System.out.println(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cpu;
		
		cpu = "Estado de la CPU:" 
				+ System.lineSeparator() +
				"\t" + this.memory.toString() + 
				System.lineSeparator() +
				"\t" + this.stack + System.lineSeparator();
		
		return cpu;
	}
	
	/**
	 * @param pos Posición de memoria en la que se quiere escribir
	 * @param value Valor que se quiere escribir en la memoria
	 */
	public void write(int pos, int value) {
		this.memory.write(pos, value);
	}
	
	/**
	 * @return Si la CPU está apagada
	 */
	private boolean isPoweredOff() {
		return this.powerOff;
	}
	
	/**
	 * @return Si es posible seguir ejecutando el ByteCodeProgram
	 */
	private boolean isAcorrectExecution() {
		return !this.isPoweredOff() && this.programCounter >= 0 
				&& this.programCounter < this.bcProgram.size();
	}

}
