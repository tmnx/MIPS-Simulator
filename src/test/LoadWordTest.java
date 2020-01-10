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
import program.instructions.LoadWord;

/**
 * Tests for the LoadWord class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class LoadWordTest {

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
		
		// Put labels into label table
		myComputer.setLabel("n", 0);		// n at address 0
		myComputer.setLabel("k", 42);		// k at address 42
		myComputer.setLabel("num", 100);	// num at address 100
		myComputer.setLabel("out1", 200);	// out of bound
		myComputer.setLabel("out2", -1);	// out of bound
		
		// Put data into data memory
		myComputer.getMyDataMem()[0] = 293L;
		myComputer.getMyDataMem()[42] = -394321L;
		myComputer.getMyDataMem()[100] = 2938298L;
	}
	
	/**
	 * Test the constructor of the class.
	 */
	@Test
	public void testLoadWord() {
		// load value of n into $a0
		LoadWord instruction = new LoadWord("lw", "$a0", "n");
		
		assertEquals("lw", instruction.getOpcode());	// opcode
		assertEquals("$a0", instruction.getRt());		// rt
		assertEquals("n", instruction.getLabel());		// label
	}
	
	/**
	 * Test the execution method of load word for loading n.
	 */
	@Test
	public void testExecution1() {
		// load value of n into $a0
		LoadWord instruction = new LoadWord("lw", "$a0", "n");
		
		// check $a0 initial value (should be 0)
		long value = myComputer.getMyRegisters().get("$a0");
		assertEquals(0L, value);
		
		// execute
		instruction.execute(myComputer);
		
		// $a0 should now be 293
		value = myComputer.getMyRegisters().get("$a0");
		assertEquals(293L, value);
	}
	
	/**
	 * Test the execution method of load word for loading k and num.
	 */
	@Test
	public void testExecution2() {
		// load value of k into $a0
		LoadWord instructionK = new LoadWord("lw", "$a0", "k");
		
		// check $a0 initial value (should be 0)
		long value = myComputer.getMyRegisters().get("$a0");
		assertEquals(0L, value);
		
		// execute
		instructionK.execute(myComputer);
		
		// $a0 should now be -394321
		value = myComputer.getMyRegisters().get("$a0");
		assertEquals(-394321L, value);
		
		// load value of num into $a0
		LoadWord instructionNum = new LoadWord("lw", "$a0", "num");
		
		// execute
		instructionNum.execute(myComputer);
		
		// $a0 should now be 2938298
		value = myComputer.getMyRegisters().get("$a0");
		assertEquals(2938298L, value);
	}
	
	/**
	 * Test the execution method of the load word.
	 * Should throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecutionIAE1() {
		LoadWord instruction = new LoadWord("lw", "$a0", "out1");
		
		instruction.execute(myComputer); // execute instruction
	}
	
	/**
	 * Test the execution method of the load word.
	 * Should throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecutionIAE2() {
		LoadWord instruction = new LoadWord("lw", "$a0", "out2");
		
		instruction.execute(myComputer); // execute instruction
	}

}
