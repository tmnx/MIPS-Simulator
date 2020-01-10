/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The And instruction in register mode and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class And extends RFormat {
	
	public And(final String theOpcode, final String theRd,
			   final String theRs, final String theRt) {
		super(theOpcode, theRd, theRs, theRt);
	}
	
	/**
	 * Execute the and instruction.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		// get register values
		long rsValue = theComp.getMyRegisters().get(getRs());
		long rtValue = theComp.getMyRegisters().get(getRt());
		
		// evaluate and instruction
		long result = rsValue & rtValue;
		
		// write result back to destination register
		theComp.setRegister(getRd(), result);
		
		// incrementPC
		theComp.setPC(theComp.getPC() + 1);
		
	}

}
