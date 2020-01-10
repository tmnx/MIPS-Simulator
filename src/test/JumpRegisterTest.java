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
import program.instructions.JumpRegister;

/**
 * Tests for the JumpRegister class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class JumpRegisterTest {

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
		
		myComputer.setRegister("$s1", 200);		
		myComputer.setRegister("$s2", -1);
		myComputer.setRegister("$s3", 199);
		myComputer.setRegister("$s4", 0);
	}

	/**
	 * Test the constructor of the class
	 */
	@Test
	public void testJumpRegister() {
		JumpRegister instruction = new JumpRegister("jr", "$s1");
		
		assertEquals("jr", instruction.getOpcode());	// opcode
		assertEquals("$s1", instruction.getRs());		// rs
	}
	
	/**
	 * Test the execute method for IllegalArgumentException.
	 * Should throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecuteIAE1() {
		JumpRegister instruction1 = new JumpRegister("jr", "$s1");
		
		// check initial PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		//execute
		instruction1.execute(myComputer);
	}
	
	/**
	 * Test the execute method for IllegalArgumentException.
	 * Should throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecuteIAE2() {
		JumpRegister instruction1 = new JumpRegister("jr", "$s2");
		
		// check initial PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		//execute
		instruction1.execute(myComputer);
	}
	
	/**
	 * Test the execute method.
	 * Should not throw exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecute1() {
		JumpRegister instruction1 = new JumpRegister("jr", "$s3");
		JumpRegister instruction2 = new JumpRegister("jr", "$s4");
		
		// check initial PC value (should be 0)
		assertEquals(0, myComputer.getPC());
		
		//execute
		instruction1.execute(myComputer);
		// check PC
		assertEquals(199, myComputer.getPC());
		// execute
		instruction2.execute(myComputer);
		// check PC
		assertEquals(0, myComputer.getPC());
	}

}
