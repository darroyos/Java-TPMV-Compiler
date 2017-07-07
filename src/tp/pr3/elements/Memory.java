package tp.pr3.elements;

public class Memory {
	private Integer[] memory;
	private int size = 10;
	
	public Memory() {
		this.memory = new Integer[size];
	}
	
	/**
	 * @param pos Posición de memoria en la que se quiere escribir
	 * @param value Valor que se quiere escribir en la memoria
	 * @return Si ha sido satisfactoria la escritura en la posición de memoria indicada
	 */
	public boolean write(int pos, int value) {
		if(correctPos(pos)) {
			
			// Tenemos una memoria ilimitada que siempre intenta redimensionarse
			if(this.tooBig(pos))
				this.resize(pos);
			
			this.memory[pos] = value;
			return true; // La posición de memoria dada era correcta
		}
		else
			return false;
	}
	
	/**
	 * @param pos Posición de memoria a la que se quiere acceder
	 * @return El valor que hay en la posición de memoria indicada
	 */
	public Integer read(int pos) {
		if((correctPos(pos)) && (this.memory[pos] != null))
			return this.memory[pos];
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String memoryStatus;
		boolean used = false;
		
		memoryStatus = "Memoria: ";
		for (int i = 0; i < this.size; i++) {
			if(this.memory[i] != null) {
				memoryStatus += "[" + i + "]:" + this.memory[i] + " ";
				used = true;
			}
		}
		
		if(!used) memoryStatus += "<vacia>";
		
		return memoryStatus;
	}
	
	/**
	 * @param pos Posición de memoria
	 * @return Si es una dirección muy alta
	 */
	private boolean tooBig(int pos) {
		return pos >= this.size;
	}
	
	/**
	 * @param pos Posición a la que se quiere acceder
	 * @return Si es una posición correcta
	 */
	private static boolean correctPos(int pos) {
		return pos >= 0;
	}
	
	/**
	 * @param pos Dirección de memoria sobre la que se calcula el nuevo tamaño de la memoria
	 */
	private void resize(int pos) {
		int newSize = pos * 2;
		Integer[] biggerMemory = new Integer[newSize];
		
		for (int i = 0; i < this.size; i++)
			biggerMemory[i] = this.memory[i];
		
		this.size = newSize;
		this.memory = biggerMemory;
	}
	
	/**
	 * Inicializa la memoria
	 */
	public void empty() {
		for (int i = 0; i < this.size; i++)
			this.memory[i] = null;
	}
}

