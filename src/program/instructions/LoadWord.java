/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Load Word instruction and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class LoadWord extends IFormat {
	
	private String myLabel;
	
	public LoadWord(final String theOpcode, final String theRt,
					final String theLabel) {
		super(theOpcode, theRt, null, 0);
		myLabel = theLabel;
	}

	/**
	 * Execute the load word instruction.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		
		// get label address
		int address = theComp.getLabelTable().get(myLabel);
		
		if (address > - 1 && address < theComp.getMyDataMem().length) {
			// get value stored at that address memory
			long value = theComp.getMyDataMem()[address];
			
			// store value into register
			theComp.setRegister(getRt(), value);
		} else {
			throw new IllegalArgumentException("Cannot store in address less than 0"
												+ " or greater than 199.");
		}
		
		// increment PC
		theComp.setPC(theComp.getPC() + 1);
		
	}

	public String getLabel() {
		return myLabel;
	}

}
