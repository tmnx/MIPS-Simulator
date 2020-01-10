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

/**
 * Tests for the Addi class.
 * 
 * @author tmn1014 - Minh Nguyen
 */
public class AddiTest {

	/**
	 * An Computer object to help test the instruction.
	 */
	Computer myComputer;

	/**
	 * Create a Computer for testing.
	 */
	@Before
	public void setUp() {
		myComputer = new Computer();
	}
	
	/**
	 * Test constructor for Add instruction in immediate mode.
	 */
	@Test
	public void testAddi() {
		Addi instruction = new Addi("addi", "$s5", "$s7", 728);
		
		// test fields
		assertEquals("addi", instruction.getOpcode());	// opcode
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$s5", instruction.getRt());		// rt
		assertEquals(728, instruction.getImmediate());	// immediate
		
	}

	/**
	 * Test execution method for Add instruction with positive values.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 add $t1
		Addi instruction = new Addi("addi", "$t0", "$t1", 9382);
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 8L);
		
		// 8 add 9382 = 9390
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(9390L, result);
	}
	
	/**
	 * Test execution method for Add instruction with zero.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 add $t1
		Addi instruction = new Addi("addi", "$t0", "$t1", 0);
		
		// put some values in register
		myComputer.getMyRegisters().put("$t1", 55L);
		
		// 55 add 0 = 55
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(55L, result);
	}
	
	/**
	 * Test execution method for Add instruction with negative value.
	 */
	@Test
	public void testExecutionNegative1() {
		// $t0 = $t1 add $t1
		Addi instruction = new Addi("addi", "$t0", "$t1", -1);
		
		// put some values in register
		myComputer.getMyRegisters().put("$t1", 845L);
		
		// 845 add -1 = 844
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(844L, result);
	}
	
	/**
	 * Test execution method for Add instruction with 2 negative values.
	 */
	@Test
	public void testExecutionNegative2() {
		// $t0 = $t1 add $t1
		Addi instruction = new Addi("addi", "$t0", "$t1", -845);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);
		
		// -384 add -845 = -1229
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-1229L, result);
	}
	
	/**
	 * Test execution method for Add instruction for stack pointer.
	 */
	@Test
	public void testExecutionStackPointer() {
		// save 3 spaces on stack
		Addi instruction = new Addi("addi", "$sp", "$sp", -12);
		
		// check stack pointer initial value
		long sp = myComputer.getMyRegisters().get("$sp");
		assertEquals(199L, sp);
		
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$sp");
		
		// sp should be at 196
		assertEquals(196L, result);
	}
	
	/**
	 * Test execution method for Add instruction for stack pointer.
	 */
	@Test
	public void testExecutionStackPointer2() {
		// save 3 spaces on stack
		Addi instruction = new Addi("addi", "$sp", "$sp", -4);
		
		// check stack pointer initial value
		long sp = myComputer.getMyRegisters().get("$sp");
		assertEquals(199L, sp);
		
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$sp");
		
		// sp should be at 198
		assertEquals(198L, result);
	}

}
