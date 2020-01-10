/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Or instruction in immediate mode and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class Ori extends IFormat {
	
	public Ori(final String theOpcode, final String theRt,
			   final String theRs, final int theImmediate) {
		super(theOpcode, theRt, theRs, theImmediate);
	}

	/**
	 * Execute the or instruction in immediate mode.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		// get register values
		long rsValue = theComp.getMyRegisters().get(getRs());
		long immediateValue = this.getImmediate();
				
		// evaluate or instruction
		long result = rsValue | immediateValue;
		
		// write result back to destination register
		theComp.setRegister(getRt(), result);
		
		// increment PC
		theComp.setPC(theComp.getPC() + 1);
	}

}
