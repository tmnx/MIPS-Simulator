/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.Computer;

/**
 * The Add instruction in immediate mode and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class Addi extends IFormat {
	
	public Addi(final String theOpcode, final String theRt,
				final String theRs, final int theImmediate) {
		super(theOpcode, theRt, theRs, theImmediate);
	}

	/**
	 * Execute the add instruction in immediate mode.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		// get register values
		long rsValue = theComp.getMyRegisters().get(getRs());
		long immediateValue = this.getImmediate();
				
		// evaluate add instruction
		long result = rsValue + immediateValue;
		
		// if save space on stack
		if (getRs().equalsIgnoreCase("$sp") && getRs().equalsIgnoreCase("$sp")) {
			result = rsValue + (immediateValue / 4);
		}
		
		// write result back to destination register
		theComp.setRegister(getRt(), result);
				
		// incrementPC
		theComp.setPC(theComp.getPC() + 1);
				
	}

}
