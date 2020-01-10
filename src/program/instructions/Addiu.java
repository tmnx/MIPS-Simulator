/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

import program.BitString;
import program.Computer;

/**
 * The Add unsigned instruction in immediate mode and its execution.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public class Addiu extends IFormat {
	
	public Addiu(final String theOpcode, final String theRt,
				 final String theRs, final int theImmediate) {
		super(theOpcode, theRt, theRs, theImmediate);
	}

	/**
	 * Execute the add unsigned instruction in immediate mode.
	 * 
	 * @param theComp is the Computer that holds the memory and the register values.
	 */
	@Override
	public void execute(Computer theComp) {
		// get register values
		long rsValue = theComp.getMyRegisters().get(getRs());
		long immediateValue = this.getImmediate();
		
		// if negative, treat as unsigned:
		if (rsValue < 0) {	// get unsigned value if value is currently signed
			rsValue = getUnsigned(rsValue);
		}
		
		if (immediateValue < 0) {	// get unsigned value if value is currently signed
			immediateValue = getUnsigned(immediateValue);
		}
		
		if (immediateValue > 4294967295L) {
			throw new IllegalArgumentException("Unsigned value larger than 4294967295");
		}
				
		// evaluate add instruction
		long result = rsValue + immediateValue;
		
		if (result > 4294967295L) {
			throw new IllegalArgumentException("Unsigned value larger than 4294967295");
		}
		
		// write result back to destination register
		theComp.getMyRegisters().put(getRt(), result);
		
		// incrementPC
		theComp.setPC(theComp.getPC() + 1);
	}
	
	/**
	 * 
	 * @param rsValue
	 * @return
	 */
	private long getUnsigned(final long rsValue) {
		BitString bits = new BitString();	// create a BitString object
		bits.setValue2sComp(rsValue);		// set 2's complement bits for the number
		
		return bits.getValue();				// return the unsigned value of the bits
	}
}
