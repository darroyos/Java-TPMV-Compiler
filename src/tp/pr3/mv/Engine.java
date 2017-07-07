package tp.pr3.mv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tp.pr3.bc.ByteCode;
import tp.pr3.bc.ByteCodeParser;
import tp.pr3.cm.Command;
import tp.pr3.cm.CommandParser;
import tp.pr3.elements.CPU;
import tp.pr3.elements.LexicalParser;
import tp.pr3.elements.Compiler;
import tp.pr3.exceptions.*;

public class Engine {
	private static Scanner sc = new Scanner(System.in);
	private SourceProgram sProgram;
	private ParsedProgram parsedProgram;
	private ByteCodeProgram bcProgram;
	private LexicalParser lexParser;
	private Compiler compiler;
	private CPU cpu;
	private boolean end;
	
	public Engine() {
		this.sProgram = new SourceProgram();
		this.parsedProgram = new ParsedProgram();
		this.bcProgram = new ByteCodeProgram();
		this.lexParser = new LexicalParser();
		this.compiler = new Compiler();
		this.cpu = new CPU();
		this.end = false;
	}
	
	/**
	 * Pide al usuario la introducción de comandos
	 * hasta el cierre de la Máquina Virtual
	 */
	public void start() {
		String command;
		Command commandParsed;
		
		do {
			
			System.out.print("> ");
			command = sc.nextLine();
			commandParsed = CommandParser.parse(command);
			
			if(commandParsed != null) {
				System.out.println("Comienza la ejecucion de " + commandParsed);
				
				try {
				// PARSEADOR HA RECONOCIDO EL COMANDO
				commandParsed.execute(this);
				} catch (ExecutionErrorException e) {
					// EL COMANDO NO SE HA PODIDO EJECUTAR
					System.out.println(e.getMessage() + System.lineSeparator());
				} catch (BadFormatByteCodeException e2) {
					System.out.println(e2.getMessage() + System.lineSeparator());
				} catch (ArrayException e3) {
					System.out.println(e3.getMessage() + System.lineSeparator());
				} catch (LexicalAnalysisException e4) {
					System.out.println(e4.getMessage() + System.lineSeparator());
				} catch (FileNotFoundException e5) {
					System.out.println("Excepcion: Fichero no encontrado...");
				}
			}
			else {
				// PARSEADOR NO HA RECONOCIDO EL COMANDO
				System.out.println("Error: Comando incorrecto" + 
									System.lineSeparator());
			}

			
		} while (!this.end);
		
		System.out.println("Fin de la ejecucion..." + System.lineSeparator());
		// Cerramos el Scanner
		sc.close();	
	}
	
	/**
	 * Analiza léxicamente el código fuente y genera el programa bytecode correspondiente
	 * 
	 * @throws LexicalAnalysisException
	 * @throws ArrayException
	 */
	public void executeCompile() throws LexicalAnalysisException, ArrayException {
			this.lexicalAnalysis();
			this.generateByteCode();
	}
	
	/**
	 * Analiza léxicamente el código fuente
	 * 
	 * @throws LexicalAnalysisException
	 */
	private void lexicalAnalysis() throws LexicalAnalysisException {
		this.lexParser.initialize(this.sProgram);
		this.lexParser.lexicalParser(this.parsedProgram, "END");
		
		System.out.println(this.sProgram.toString());
	}
	
	/**
	 * Genera el programa bytecode
	 * 
	 * @throws ArrayException
	 */
	private void generateByteCode() throws ArrayException {
		this.compiler.initialize(this.bcProgram);
		this.compiler.compile(this.parsedProgram);
		
		System.out.println(this.bcProgram.toString());
	}
	
	/**
	 * Ejecuta el código del programa que haya almacenado en la CPU
	 */
	public void executeRun() throws ExecutionErrorException, ArrayException {
		this.cpu.reset();
		
		for (int i = 0; i < this.bcProgram.size(); i++)
			this.cpu.addByteCode(this.bcProgram.getInst(i), i);
		
		this.cpu.run();
		System.out.println(this.sProgram);
		System.out.println(this.bcProgram);
	}
	
	/**
	 * Muestra información acerca de los comandos disponibles en la Máquina Virtual
	 */
	public void executeHelp() {
		CommandParser.showHelp();
		System.out.print(System.lineSeparator());
	}
	
	/**
	 * @param n Instrucción que el usuario desea reemplazar
	 */
	public void executeReplace(int n) throws BadFormatByteCodeException, ArrayException {
		String newByteCode;
		ByteCode byteCodeParsed;
		
		System.out.print("Nuevo bytecode: ");
		newByteCode = sc.nextLine(); // Leemos la nueva instrucción
	
		byteCodeParsed = ByteCodeParser.parse(newByteCode);
		
		if (byteCodeParsed == null)
			throw new BadFormatByteCodeException("Excepcion-formato bytecode: " + newByteCode);
		else {
			this.bcProgram.addByteCode(byteCodeParsed, n);
			System.out.println(this.sProgram);
			System.out.println(this.bcProgram);
		}
	}
	
	/**
	 * Resetea el Engine
	 */
	public void executeReset() {
		this.sProgram.reset();
		this.parsedProgram.reset();
		this.bcProgram.reset();
		this.cpu.reset();
	}
	
	/**
	 * Finaliza la ejecución de la Máquina Virtual
	 */
	public void executeQuit() {
		this.end = true;
	}
	
	/**
	 * Carga desde un fichero el código fuente
	 * 
	 * @param fichName nombre del fichero
	 * @throws FileNotFoundException
	 * @throws ArrayException
	 */
	public void load(String fichName) throws FileNotFoundException, ArrayException {
		String ins = "";
		
		// Reseteamos el Engine para proceder a la carga de un archivo
		this.executeReset();

		// Creamos un Scanner para leer el código fuente
		Scanner fileLoader = new Scanner(new File(fichName));
		
		try { // Intentamos añadir una instrucción
			
			do {
				ins = fileLoader.nextLine();
				this.sProgram.addInst(ins);
				
			} while (!ins.equalsIgnoreCase("END")); // Leemos hasta el final del programa
			
		} finally { // Haya excepciones o no cerramos el Scanner
			fileLoader.close();
		}
		
		// Mostramos el programa fuente almacenado
		System.out.println(this.sProgram.toString());
	}
	
}
