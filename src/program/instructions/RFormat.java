/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

/**
 * RFormat is an abstract class that holds similar fields and methods
 * for all Register Format instructions.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */

public abstract class RFormat extends Instruction {
	
	/** The destination register number. */
	private String myRd;
	
	/** The source register number. */
	private String myRt;
	
	/**
	 * Protected constructor to limit visibility to only instruction types 
	 * and to set fields.
	 * 
	 * @param theOpcode is the opcode.
	 * @param theRd is the destination register.
	 * @param theRs is the source register.
	 * @param theRt is the source register.
	 */
	protected RFormat(final String theOpcode, final String theRd,
			   		final String theRs, final String theRt)	{
		super(theOpcode, theRs);
		myRd = theRd;
		myRt = theRt;
	}
	
	/**
	 * Get the destination register number.
	 * 
	 * @return the destination register number.
	 */
	public String getRd() {
		return myRd;
	}
	
	
	/**
	 * Get the source register number.
	 * 
	 * @return the source register number.
	 */
	public String getRt() {
		return myRt;
	}

}
