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
import program.instructions.Addi;
import program.instructions.SaveWord;

/**
 * Tests for the SaveWord class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class SaveWordTest {

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
	}

	/**
	 * Test the constructor of the class.
	 */
	@Test
	public void testSaveWord() {
		
		final SaveWord instruction = new SaveWord("sw", "$v0", "0($sp)");
		
		assertEquals("sw", instruction.getOpcode());		// opcode
		assertEquals("$v0", instruction.getRt());			// rt
		assertEquals("0($sp)", instruction.getAddress());	// address
	}
	
	/**
	 * Test the execution method of the save word.
	 */
	@Test
	public void testExecution1() {
		// save space on stack
		myComputer.getMyRegisters().put("$sp", myComputer.getMyRegisters().get("$sp") - 1);
		
		// save value of $s0 on stack 0($sp)
		final SaveWord instruction = new SaveWord("sw", "$s0", "0($sp)");
		
		// check data memory initial value at address 199 (should be 0)
		assertEquals(0L, myComputer.getMyDataMem()[199]);
		
		// put value into register
		myComputer.getMyRegisters().put("$s0", -329L);
		instruction.execute(myComputer); // execute instruction
		
		// data memory[199] should be -329
		assertEquals(-329L, myComputer.getMyDataMem()[199]);
	}
	
	/**
	 * Test the execution method of the save word.
	 */
	@Test
	public void testExecution2() {
		// save space on stack
		Addi addi = new Addi("addi", "$sp", "$sp", -8);
		addi.execute(myComputer);
		long sp = myComputer.getMyRegisters().get("$sp");
		assertEquals(197L, sp);
		
		// save value of $s0 on stack 4($sp) & 0($sp)
		final SaveWord instruction1 = new SaveWord("sw", "$s0", "4($sp)");
		final SaveWord instruction2 = new SaveWord("sw", "$s0", "0($sp)");

		// check data memory initial value at address 198 && 199 (should be 0)
		assertEquals(0L, myComputer.getMyDataMem()[198]);
		assertEquals(0L, myComputer.getMyDataMem()[199]);
		
		// put value into register
		myComputer.getMyRegisters().put("$s0", -329L);
		instruction1.execute(myComputer); 	// execute instructions
		instruction2.execute(myComputer);
		
		// data memory[198] && [199] should be -329
		assertEquals(-329L, myComputer.getMyDataMem()[198]);
		assertEquals(-329L, myComputer.getMyDataMem()[199]);
	}
	
	/**
	 * Test the execution method of the save word.
	 * Should throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecutionIAE1() {
		
		// save value of $s0 on stack 0($sp)
		final SaveWord instruction = new SaveWord("sw", "$s0", "0($sp)");
		
		// put value into register
		myComputer.getMyRegisters().put("$s0", -329L);
		instruction.execute(myComputer); // execute instruction
	}
}
