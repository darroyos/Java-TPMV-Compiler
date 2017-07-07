package tp.pr3.cm;

import tp.pr3.mv.Engine;
import tp.pr3.exceptions.*;
import java.io.FileNotFoundException;

/**
 * Representa los distintos comandos que puede utilizar un usuario.
 */
public interface Command {

	void execute(Engine engine) throws FileNotFoundException,
									   LexicalAnalysisException,
									   ArrayException,
									   BadFormatByteCodeException,
									   StackException,
									   DivByZeroException,
									   ExecutionErrorException;
														
	Command parse(String[] s);
	
	String textHelp();
}
