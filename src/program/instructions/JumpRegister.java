/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Jump Register instruction and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class JumpRegister extends Instruction {
	
	public JumpRegister(final String theOpcode, final String theRs) {
		super(theOpcode, theRs);
	}
	
	/**
	 * Execute the jump register instruction.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(final Computer theComp) {
		// get register value
		long address = theComp.getMyRegisters().get(getRs());
		
		// jump if address if valid
		if (address > -1 && address < theComp.getInstructionMem().length) {
			theComp.setPC((int) address);
		} else {
			throw new IllegalArgumentException("Cannot jump to address less than 0 "
											   + "or greater than 99");
		}
	}

}
