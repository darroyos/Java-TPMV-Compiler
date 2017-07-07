package tp.pr3.elements;

import tp.pr3.exceptions.StackException;

public class OperandStack {
	private int[] stack;
	private static final int SIZE = 100; // Tamaño de la pila
	private int stackPointer;
	
	public OperandStack() {
		this.stack = new int[SIZE];
		this.stackPointer = 0;
	}
	
	/**
	 * @param value Valor que se quiere apilar
	 */
	public void push(int value) throws StackException {
		if(this.overflow())
			throw new StackException("Limite de pila alcanzado");
		else {
			this.stack[this.stackPointer] = value;
			this.stackPointer++;
		}
	}
	
	/**
	 * @return Valor que se ha desapilado
	 */
	public int pop() throws StackException {
		int value;
		
		if (this.emptyStack())
			throw new StackException("Tamaño de pila insuficiente");
		else {
			value = this.stack[this.stackPointer - 1];
			this.stackPointer--;
			return value;
		}
	}
	
	/**
	 * @return Si se ha producido "overflow"
	 */
	public boolean overflow() {
		return this.stackPointer == SIZE;
	}
	
	/**
	 * @return Si la pila está vacía
	 */
	public boolean emptyStack() {
		return this.stackPointer == 0;
	}
	
	/**
	 * Vacía la pila de operandos, a parte retornar su estado
	 */
	@Override
	public String toString() {
		String stackStatus;
		
		stackStatus = "Pila: ";
		if(this.emptyStack())
			stackStatus += "<vacia>";
		
		else {
			for (int i = 0; i < this.stackPointer; i++)
				stackStatus += this.stack[i] + "  ";
			this.stackPointer = 0;
		}
		
		return stackStatus;
	}

}
