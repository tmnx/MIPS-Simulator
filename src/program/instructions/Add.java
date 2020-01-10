/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Add instruction in register mode and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class Add extends RFormat{
	
	public Add(final String theOpcode, final String theRd,
			   final String theRs, final String theRt) {
		super(theOpcode, theRd, theRs, theRt);
	}
	
	/**
	 * Execute the add instruction in register mode.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(final Computer theComp) {
		// get register values
		long rsValue = theComp.getMyRegisters().get(getRs());
		long rtValue = theComp.getMyRegisters().get(getRt());
				
		// evaluate add instruction
		long result = rsValue + rtValue;
		
		// write result back to destination register
		theComp.setRegister(getRd(), result);
		
		// incrementPC
		theComp.setPC(theComp.getPC() + 1);
	}

}
