/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Jump instruction and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class Jump extends Instruction {
	
	/** The instruction address(label) to jump to. */
	private String myLabel;
	
	/**
	 * Construct a Jump instruction.
	 * 
	 * @param theAddress
	 */
	public Jump(final String theOpcode, final String theAddress) {
		super(theOpcode, null);
		myLabel = theAddress;
	}
	
	/**
	 * Get the instruction address label.
	 * 
	 * @return the instruction address label.
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * Execute the jump instruction.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(final Computer theComp) throws IllegalArgumentException {
		
		// get label address to jump to
		int address = theComp.getLabelTable().get(myLabel);
		
		// jump if address if valid
		if (address > -1 && address < theComp.getInstructionMem().length) {
			theComp.setPC(address);
		} else {
			throw new IllegalArgumentException("Cannot jump to address less than 0 "
												 + "or greater than 99");
		}
	}

}
