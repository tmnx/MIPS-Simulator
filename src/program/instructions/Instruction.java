/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * Instruction is an abstract class that holds similar fields and methods
 * for all instruction formats.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */
public abstract class Instruction {
	
	/** The opcode of the instruction. */
	private String myOpcode;
	
	/** The source register number. */
	private String myRs;
	
	/**
	 * Protected constructor to limit visibility to only instruction types 
	 * and to set fields.
	 * 
	 * @param theOpcode is the opcode.
	 * @param theRs is a register.
	 */
	protected Instruction(final String theOpcode, final String theRs) {
		myOpcode = theOpcode;
		myRs = theRs;
	}
	
	/**
	 * Get the opcode.
	 * 
	 * @return the opcode.
	 */
	public String getOpcode() {
		return myOpcode;
	}
	
	/**
	 * Get the source register number.
	 * 
	 * @return the source register number.
	 */
	public String getRs() {
		return myRs;
	}
	
	/**
	 * Execute the instruction.
	 * 
	 * @param theComp is the computer running the instruction.
	 * @throws IllegalAccessException 
	 */
	public abstract void execute(final Computer theComp) throws IllegalArgumentException;
}
