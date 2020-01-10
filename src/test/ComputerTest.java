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
import program.instructions.BranchNE;
import program.instructions.Instruction;
import program.instructions.Jump;
import program.instructions.LoadWord;
import program.instructions.SaveWord;

/**
 * Tests for the Computer class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class ComputerTest {
	
	/**
	 * Computer object the test the class.
	 */
	private Computer myComputer;
	
	/**
	 * Set up the computer for testing.
	 */
	@Before
	public void setUp() {
		myComputer = new Computer();
		
		// put labels in label memory
		myComputer.setLabel("n", 1);
		myComputer.setLabel("jump", 2);
		
		// put data in data memory
		myComputer.getMyDataMem()[1] = 3;
		
		// put instructions into instruction memory
		Instruction lw = new LoadWord("lw", "$s1", "n");
		Instruction addi = new Addi("addi", "$s0", "$zero", 5);
		Instruction addi2 = new Addi("addi", "$s0", "$s0", -1);
		Instruction bne = new BranchNE("bne", "$s0", "$s1", "jump");
		Instruction saveStack = new Addi("addi", "$sp", "$sp", -4);
		Instruction saveWord = new SaveWord("sw", "$s0", "0($sp)");
		Instruction popStack = new Addi("addi", "$sp", "$sp", 4);
		
		myComputer.getInstructionMem()[0] = lw;
		myComputer.getInstructionMem()[1] = addi;
		myComputer.getInstructionMem()[2] = addi2;
		myComputer.getInstructionMem()[3] = bne;
		myComputer.getInstructionMem()[4] = saveStack;
		myComputer.getInstructionMem()[5] = saveWord;
		myComputer.getInstructionMem()[6] = popStack;
	}
	
	/**
	 * Test the computer constructor.
	 */
	@Test
	public void testComputer() {
		assertEquals(0, myComputer.getPC());		// program starts at PC = 0
		assertEquals(100, myComputer.getInstructionMem().length);
		assertEquals(200, myComputer.getMyDataMem().length);
		
		// check stack pointer
		long sp = myComputer.getMyRegisters().get("$sp");
		assertEquals(199L, sp);
		
		// check data memory
		assertEquals(0, myComputer.getMyDataMem()[199]);
	}
	
	/**
	 * Test the execution method of the Computer class.
	 */
	@Test
	public void testStartExecution() {
		
		myComputer.startExecution();			// execute program
		
		assertEquals(7, myComputer.getPC());	// should end when PC = 7 (null instruction)
		
		// check value of $s0 after execution; should be 3
		long s0 = myComputer.getMyRegisters().get("$s0");
		assertEquals(3L, s0);
		// $s0 value should be saved in memory
		assertEquals(3L, myComputer.getMyDataMem()[199]);
		// $s1 value should remain the same as the beginning (3)
		long s1 = myComputer.getMyRegisters().get("$s1");
		assertEquals(3L, s1);
	}
	
	/**
	 * Test the execution method of the Computer class.
	 * Test for IllegalArgumentException branch.
	 */
	@Test
	public void testStartExecutionIAE(){
		myComputer.setLabel("exception", 100);
		myComputer.getInstructionMem()[7] = new Jump("j", "exception");
		
		myComputer.startExecution();			// execute program
		
		assertEquals(7, myComputer.getPC());	// should end when PC = 7 (null instruction)
		
		// check value of $s0 after execution; should be 3
		long s0 = myComputer.getMyRegisters().get("$s0");
		assertEquals(3L, s0);
		// $s0 value should be saved in memory
		assertEquals(3L, myComputer.getMyDataMem()[199]);
		// $s1 value should remain the same as the beginning (3)
		long s1 = myComputer.getMyRegisters().get("$s1");
		assertEquals(3L, s1);
	}

	/**
	 * Test the set PC method.
	 * Should not throw an exception.
	 */
	@Test
	public void testSetPC() {
		assertEquals(0, myComputer.getPC());	// check initial PC value
		myComputer.setPC(29); 					// set PC to 29
		assertEquals(29, myComputer.getPC());	// check current PC
	}
	
	/**
	 * Test the set PC method.
	 * Should throw an exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSetPCIAE1() {
		assertEquals(0, myComputer.getPC());	// check initial PC value
		myComputer.setPC(-1); 					// set PC to 29
	}
	
	/**
	 * Test the set PC method.
	 * Should throw an exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSetPCIAE2() {
		assertEquals(0, myComputer.getPC());	// check initial PC value
		myComputer.setPC(100); 					// set PC to 29
	}
	
	/**
	 * Test the set label method.
	 */
	@Test
	public void testSetLabel() {
		myComputer.setLabel("label", 7);
		int value = myComputer.getLabelTable().get("label");
		assertEquals(7, value);
	}

	/**
	 * Test the set register.
	 */
	@Test
	public void testSetRegister() {
		// check initial register value
		long value = myComputer.getMyRegisters().get("$t0");
		assertEquals(0L, value);
		
		// check register value now
		myComputer.setRegister("$t0", 1000L);
		value = myComputer.getMyRegisters().get("$t0");
		assertEquals(1000L, value);
	}
	
	/**
	 * Test the set register.
	 * Should throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSetRegisterIAE() {
		myComputer.setRegister("$ra", 1000L);
	}

}
