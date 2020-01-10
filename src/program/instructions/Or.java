/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Or instruction in register mode and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */
public class Or extends RFormat{
	
	/**
	 * Create an or instruction.
	 * 
	 * @param theOpcode
	 * @param theRd
	 * @param theRs
	 * @param theRt
	 */
	public Or(final String theOpcode, final String theRd,
			  final String theRs, final String theRt) {
		super(theOpcode, theRd, theRs, theRt);
	}
	
	/**
	 * Execute the or instruction in register mode.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		// get register values
		long rsValue = theComp.getMyRegisters().get(getRs());
		long rtValue = theComp.getMyRegisters().get(getRt());
				
		// evaluate or instruction
		long result = rsValue | rtValue;
		
		// write result back to destination register
		theComp.setRegister(getRd(), result);
		
		// increment PC
		theComp.setPC(theComp.getPC() + 1);
	}

}
