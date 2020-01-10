/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Branch if Equal instruction and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class BranchEQ extends IFormat {
	
	/** The label to be branched to. */
	private String myLabel;
	
	public BranchEQ(final String theOpcode, final String theRt,
					final String theRs, final String theLabel) {
		super(theOpcode, theRt, theRs, 0);
		myLabel = theLabel;
	}

	/**
	 * Execute the branch if equal instruction.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		// get register values;
		long rsValue = theComp.getMyRegisters().get(getRs());
		long rtValue = theComp.getMyRegisters().get(getRt());
		int address = theComp.getLabelTable().get(myLabel);	// get label address
		
		if (rsValue == rtValue) {									// if equal, branch to label
			if (-1 < address && theComp.getInstructionMem().length > address) {
				theComp.setPC(address);								// set PC address
			} else {
				throw new IllegalArgumentException("Cannot jump to address less than 0 "
												   + "or greater than 99");
			}
		} else {
			// else ignore, don't branch
			theComp.setPC(theComp.getPC() + 1);
		}
	}
	
	/**
	 * Get label.
	 * 
	 * @return the label to be branched to.
	 */
	public String getLabel() {
		return myLabel;
	}

}
