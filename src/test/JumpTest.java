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
import program.instructions.Jump;

/**
 * Tests for the Jump class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class JumpTest {

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
		
		myComputer.setLabel("here", 9);
		myComputer.setLabel("main", 0);
		myComputer.setLabel("exit", 30);
		myComputer.setLabel("out_bound100", 100);
		myComputer.setLabel("out_Negative", -1);
	}

	/**
	 * Test the constructor of the class.
	 */
	@Test
	public void testJump() {
		Jump instruction = new Jump("j", "here");
		
		assertEquals("j", instruction.getOpcode());
		assertEquals("here", instruction.getLabel());
	}
	
	/**
	 * Test the execute method of the class.
	 */
	@Test
	public void testExecute() {
		Jump instruction1 = new Jump("j", "here");
		Jump instruction2 = new Jump("j", "main"); 
		Jump instruction3 = new Jump("j", "exit");
		
		// check initial PC value
		assertEquals(0, myComputer.getPC());
		
		// execute
		instruction1.execute(myComputer);
		// check PC
		assertEquals(9, myComputer.getPC());
		
		// execute
		instruction2.execute(myComputer);
		// check PC
		assertEquals(0, myComputer.getPC());
		
		// execute
		instruction3.execute(myComputer);
		// check PC
		assertEquals(30, myComputer.getPC());
	}
	
	/**
	 * Test execute method for IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExecuteIAE() {
		Jump instruction = new Jump("j", "out_bound100");
		instruction.execute(myComputer);
	}
	
	/**
	 * Test execute method for IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExecuteIAENegative() {
		Jump instruction = new Jump("j", "out_Negative");
		instruction.execute(myComputer);
	}

}
