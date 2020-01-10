/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import program.instructions.Instruction;

/**
 * Computer class comprises of memory, registers,
 * can execute the instructions based on PC and IR
 * 
 * @author tmn1014
 * @version 03 November 2019
 */
public final class Computer {
	
	/** Total of memory space for data memory. */
	private final static int MAX_DATA_MEM = 200;
	
	/** Total of memory space for instruction memory. */
	private final static int MAX_INSTR_MEM = 100;
	
	/** The property change for executing an instruction. */
	public final static String PROPERTY_INSTRUCTION = "An instruction has been executed.";
	
	/** The property change for executing an instruction. */
	public final static String PROPERTY_READ = "A file has been read.";
	
	/** The property change for executing an instruction. */
	public final static String PROPERTY_EXECUTED = "Program has been executed.";
		
	/** Map of registers. */
	private Map<String, Long> myRegisters;
	
	/** Array of instruction memory. */
	private Instruction[] myInstructionMem;
	
	/** Data memory. */
	private long[] myDataMem;
	
	/** A label table to keep track of label address. 
	 *  Key = a label
	 *  Value = a memory location.
	 */
	private Map<String, Integer> myLabelTable;
	
	/** The current Program Counter value. */
	private int myPC;
	
	private Decoder myDecoder;
	
	/** Manager for the Property Change Listeners. */
    public PropertyChangeSupport myPropertyChangeSupport;
	
	/**
	 * Constructor for Computer class.
	 * Initializes all fields with necessary values.
	 */
	public Computer() {
		myInstructionMem = new Instruction[MAX_INSTR_MEM];
		myDataMem = new long[MAX_DATA_MEM];
		myLabelTable = new HashMap<>();
		myDecoder = new Decoder();
		myPropertyChangeSupport = new PropertyChangeSupport(this);
		mapRegisters();					// Map register with correct 'key'
		setUpDataMemory();
		setPC(0);
	}
	
	/**
	 * Execute all instructions in the instruction memory.
	 */
	public void startExecution() {
		
		// restart PC
		myPC = 0;

		// execute instruction as long as PC is valid and it's not a null instruction.
		execution:
		while (myPC < MAX_INSTR_MEM && myInstructionMem[myPC] != null) {
			try {
				myInstructionMem[myPC].execute(this);	// execute current instruction
			} catch (IllegalArgumentException e) {
				System.out.println("An exception has occurred -- " + e + " ");
				e.printStackTrace();
				break execution;
			}
		}
		myPropertyChangeSupport.firePropertyChange(PROPERTY_EXECUTED, 
												   null, 
												   "---The program has been executed---");

	}
	
	/**
	 * Read file and put the instructions into the Computer instruction memory.
	 * 
	 * @param theFile is the file to be read
	 * @throws FileNotFoundException if file is not found
	 * @throws IllegalArgumentException for invalid input from file
	 */
	public void readFile(final File theFile) throws FileNotFoundException, 
													IllegalArgumentException {
		
		// restart the computer
		myInstructionMem = new Instruction[MAX_INSTR_MEM];
		myDataMem = new long[MAX_DATA_MEM];
		myLabelTable = new HashMap<>();
		mapRegisters();					// Map register with correct 'key'
		setUpDataMemory();
		setPC(0);
		
		final Scanner input = new Scanner(theFile);
		int dataMem_location = 0;
		Scanner word;
				
		while (input.hasNextLine()) {
			String line = input.nextLine();	// current line
			line = line.toLowerCase();
			
			// check if current line is a comment, if so skip
			// if not a comment, not blank, not ".data" continue...
			if (!line.startsWith("#") && !line.isBlank()) {
				
				// check for .data section
				// if it's a data section, read data until .text section
				if (line.contains(".data")) {
					line = input.nextLine();
					line = line.toLowerCase();
					while (!line.contains(".text")) {
						word = new Scanner(line);	// current word
						String label = word.next();		// get label name
						// get label without colon
						int index = label.indexOf(":");
						label = label.substring(0, index);
						word.next();					// skip .word
						int value = word.nextInt();		// get value
						
						if (dataMem_location < 200) {
							// add label to Label Table
							setLabel(label, dataMem_location);
							// set value at that data memory location
							getMyDataMem()[dataMem_location] = value;
							dataMem_location++;
							if (input.hasNextLine()) {
								line = input.nextLine();
							}
						}
					}
				}
				// line does not contain data so this mean it's .text section
				else {
					word = new Scanner(line);
					String label = word.next();
					if (label.contains(":")) {	// check for label
						
						// get label without colon
						int index = label.indexOf(":");
						label = label.substring(0, index);
						
						setLabel(label, getPC());
					} else {
						// decode current instruction
						try {
							Instruction instruction = myDecoder.decodeInstruction(line);
							getInstructionMem()[getPC()] = instruction;
							setPC(getPC() + 1);
						} catch(IllegalArgumentException e) {
							System.out.println("Error in syntax / values");
						}

					}
					
				}
				
			}
			
		}
		myPropertyChangeSupport.firePropertyChange(PROPERTY_READ, null, theFile);
		input.close();
	}

	/**
	 * Get the PC.
	 * 
	 * @return myPC the current PC value.
	 */
	public int getPC() {
		return myPC;
	}
	
	/**
	 * Set the PC value to the new specified PC value.
	 * 
	 * @param thePC is the PC value to be set to.
	 * @throws IllegalArgumentException if PC is out of range.
	 */
	public void setPC(final int thePC) throws IllegalArgumentException {
		if (thePC > -1 && thePC < 100) {
			myPC = thePC;
		} else {
			throw new IllegalArgumentException("PC out of bound!");
		}
	}
	
	/**
	 * Get the label table.
	 * 
	 * @return myLabelTable
	 */
	public Map<String, Integer> getLabelTable() {
		return myLabelTable;
	}
	
	/**
	 * Set label with address of label.
	 * 
	 * @param theLabel
	 * @param theAddress
	 */
	public void setLabel(final String theLabel, final int theAddress) {
		myLabelTable.put(theLabel,theAddress);
	}
	
	/**
	 * Get the current state of the data memory.
	 * 
	 * @return myDataMem is current state of the data memory
	 */
	public long[] getMyDataMem() {
		return myDataMem;
	}

	/**
	 * Return the registers.
	 * 
	 * @return myRegisters
	 */
	public Map<String, Long> getMyRegisters() {
		return myRegisters;
	}

	/**
	 * Set specific register to a value.
	 * 
	 * @param theRegister is the register
	 * @param theValue is the value to set the register to.
	 */
	public void setRegister(final String theRegister, final long theValue) throws IllegalArgumentException {
		if (myRegisters.containsKey(theRegister)) {
			myRegisters.put(theRegister, theValue);
		} else {
			throw new IllegalArgumentException("Register does not exist");
		}
	
	}
	
	/**
	 * Get the set of instructions to be executed.
	 * 
	 * @return myInstructionMem the current set of instructions to be executed.
	 */
	public Instruction[] getInstructionMem() {
		return myInstructionMem;
	}
	
	/**
	 * Create register map.
	 * Initializes all registers to zero.
	 * Set stack pointer register to last data memory address.
	 */
	private void mapRegisters() {
		myRegisters = new HashMap<>();
		
		myRegisters.put("$zero", 0L);	// constant zero
		myRegisters.put("$sp", 199L);	// stack pointer start a bottom of data memory.
		
		for(int i = 0; i < 10; i++) {	// temporaries
			myRegisters.put("$t"+i, 0L);
		}
		
		for(int i = 0; i < 8; i++) {	// saved temporaries
			myRegisters.put("$s"+i, 0L);
		}
		
		for(int i = 0; i < 4; i++) {	// arguments
			myRegisters.put("$a"+i, 0L);
		}
		
		for(int i = 0; i < 2; i++) {	// returns
			myRegisters.put("$v"+i, 0L);
		}
	}
	
	/**
	 * Initialize data memory to zero.
	 */
	private void setUpDataMemory() {
		for (int i = 0; i < MAX_DATA_MEM; i++) {
			myDataMem[i] = 0;
		}
		
	}
	
}
