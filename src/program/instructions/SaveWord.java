/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Save Word instruction and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class SaveWord extends IFormat {
	
	private String myAddress;
	
	public SaveWord(final String theOpcode, final String theRt,
					final String theAddress) {
		super(theOpcode, theRt, null, 0);
		myAddress = theAddress;
	}

	@Override
	public void execute(Computer theComp) {
		
		// get register value to be saved
		long value = theComp.getMyRegisters().get(getRt());
		
		// compute where to save in stack (e.g. 	x($sp))
		String[] tokens = myAddress.split("\\(");
		int num = Integer.parseInt(tokens[0]);			// x
		num = num / 4;
		
		// get stack pointer current value
		long index = theComp.getMyRegisters().get("$sp");
		// determine where to store ($sp + num)
		index = index + num + 1;
		
		if (index > -1 && index < theComp.getMyDataMem().length) {
			// store value into index
			theComp.getMyDataMem()[(int) index] = value; // M[index] = rt
		} else {
			throw new IllegalArgumentException("Cannot store in address less than 0"
												+ " or greater than 199.");
		}
		
		
		
		// increment PC
		theComp.setPC(theComp.getPC() + 1);
	}
	
	/**
	 * Get the address to store in.
	 * 
	 * @return the address to store in.
	 */
	public String getAddress() {
		return myAddress;
	}

}
