package tp.pr3.bc;

import tp.pr3.elements.CPU;
import tp.pr3.exceptions.DivByZeroException;
import tp.pr3.exceptions.StackException;

/**
 * Representa las distintas instrucciones bytecode que puede manejar nuestra
 * máquina virtual.
 */
public interface ByteCode {

	/**
	 * @param cpu La CPU con la que se van a ejecutar los bytecodes
	 * @throws DivByZeroException
	 * @throws StackException
	 */
	void execute(CPU cpu) throws DivByZeroException, StackException;
	
	/**
	 * @param s
	 * @return El ByteCode parseado
	 */
	ByteCode parse(String[] s);
}
