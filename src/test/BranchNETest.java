/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import program.Computer;
import program.instructions.BranchNE;


/**
 * Tests for the BranchNE class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class BranchNETest {

	/**
	 * An Computer object to help test the instruction.
	 */
	Computer myComputer;

	/**
	 * Create and set up a Computer for testing.
	 */
	@Before
	public void setUp() {
		myComputer = new Computer();
		myComputer.setLabel("branch", 4);
		myComputer.setLabel("outbound1", -1);
		myComputer.setLabel("outbound2", 101);
	}

	/**
	 * Test the constructor of the class.
	 */
	@Test
	public void testBranchEQ() {
		final BranchNE instruction = new BranchNE("bne", "$zero", "$t1", "branch");
		
		assertEquals("bne", instruction.getOpcode());	// opcode
		assertEquals("$zero", instruction.getRt());		// rt
		assertEquals("$t1", instruction.getRs());		// rs
		assertEquals("branch", instruction.getLabel()); // label
	}
	
	/**
	 * Test the execution method of BranchNE for when both registers equal zero.
	 * The branch should not been taken.
	 */
	@Test
	public void testExecuteEqualZero() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$zero", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t1", 0L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 1 (next instruction)
		assertEquals(1, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for when both registers equal a positive value.
	 * The branch should not be taken.
	 */
	@Test
	public void testExecuteEqualPositive() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into registers
		myComputer.getMyRegisters().put("$t0", 3543L);
		myComputer.getMyRegisters().put("$t1", 3543L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 1 (next instruction)
		assertEquals(1, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for when both registers equal a negative value.
	 * The branch should not be taken.
	 */
	@Test
	public void testExecuteEqualNegative() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into registers
		myComputer.getMyRegisters().put("$t0", -3543L);
		myComputer.getMyRegisters().put("$t1", -3543L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 1 (next instruction)
		assertEquals(1, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for when not equal.
	 * The branch should be taken.
	 */
	@Test
	public void testExecuteNotEqualZero() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$zero", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t1", 3543L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 4
		assertEquals(4, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for when not equal.
	 * The branch should be taken.
	 */
	@Test
	public void testExecuteNotEqualPositive() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t0", 353L);
		myComputer.getMyRegisters().put("$t1", 3543L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 4
		assertEquals(4, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for when not equal.
	 * The branch should be taken.
	 */
	@Test
	public void testExecuteNotEqualNegative() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t0", -353L);
		myComputer.getMyRegisters().put("$t1", -3543L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 4
		assertEquals(4, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for when not equal.
	 * The branch should be taken.
	 */
	@Test
	public void testExecuteNotEqualMix() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "branch");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t0", 353L);
		myComputer.getMyRegisters().put("$t1", -3543L);
		
		// execute branch instruction
		instruction.execute(myComputer);
		
		// PC now should be 4
		assertEquals(4, myComputer.getPC());
	}
	
	/**
	 * Test the execution method of BranchNE for IllegalArgumentException.
	 * Should throw IllegalArugmentException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecuteIAE1() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "outbound1");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t0", 9L);
		myComputer.getMyRegisters().put("$t1", 0L);
		
		// execute branch instruction
		instruction.execute(myComputer);
	}
	
	/**
	 * Test the execution method of BranchNE for IllegalArgumentException.
	 * Should throw IllegalArugmentException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecuteIAE2() {
		// create instruction for testing
		final BranchNE instruction = new BranchNE("bne", "$t0", "$t1", "outbound2");
		
		// Check beginning PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		// Put values into register
		myComputer.getMyRegisters().put("$t0", 9L);
		myComputer.getMyRegisters().put("$t1", 0L);
		
		// execute branch instruction
		instruction.execute(myComputer);
	}
}
