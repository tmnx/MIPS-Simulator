/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program.instructions;

/**
 * IFormat is an abstract class that holds similar fields and methods
 * for all Immediate Format instructions.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */
public abstract class IFormat extends Instruction {
	
	/** The source register number. */
	private String myRt;
	
	/** The immediate value. */
	private int myImmediate;
	
	/**
	 * Protected constructor to limit visibility to only instruction types 
	 * and to set fields.
	 * 
	 * @param theOpcode is the opcode.
	 * @param theRt is the source register.
	 * @param theRs is the source register.
	 * @param theImmediate is the immediate value.
	 */
	protected IFormat(final String theOpcode, final String theRt, 
					  final String theRs, final int theImmediate)	{
		super(theOpcode, theRs);
		myRt = theRt;
		myImmediate = theImmediate;
	}

	/**
	 * Get the source register number.
	 * 
	 * @return the source register number.
	 */
	public String getRt() {
		return myRt;
	}
	
	/**
	 * Get the immediate value.
	 * 
	 * @return the immediate value.
	 */
	public int getImmediate() {
		return myImmediate;
	}
	

}
