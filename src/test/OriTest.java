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
import program.instructions.Ori;

/**
 * Tests for the Ori class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class OriTest {

	/**
	 * An Computer object to help test the instruction.
	 */
	Computer myComputer;

	/**
	 * Create a Computer for testing.
	 * 
	 */
	@Before
	public void setUp() {
		myComputer = new Computer();
	}
	
	/**
	 * Test constructor for Or instruction.
	 */
	@Test
	public void testOr() {
		Ori instruction = new Ori("ori", "$s5", "$s7", -382);
		
		// test fields
		assertEquals("ori", instruction.getOpcode());	// opcode
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$s5", instruction.getRt());		// rt
		assertEquals(-382, instruction.getImmediate()); // immediate
	}
	
	/**
	 * Test execution method for Or instruction with zero.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 or $t1
		Ori instruction = new Ori("ori", "$t0", "$t1", 0);
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 9L);
		
		// 9 or 0 = 9
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(9L, result);
	}
	
	/**
	 * Test execution method for Or instruction with positive values.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 or $t1
		Ori instruction = new Ori("ori", "$t0", "$t1", 322);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 55L);
		
		// 55 or 322 = 375
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(375L, result);
	}
	
	/**
	 * Test execution method for Or instruction with one negative value.
	 */
	@Test
	public void testExecutionNegative1() {
		// $t0 = $t1 or $t1
		Ori instruction = new Ori("ori", "$t0", "$t1", -845);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 384L);
		
		// 384 or -845 = -589
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-589L, result);
	}
	
	/**
	 * Test execution method for Or instruction with 2 negative values.
	 */
	@Test
	public void testExecutionNegative2() {
		// $t0 = $t1 or $t1
		Ori instruction = new Ori("ori", "$t0", "$t1", -845);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);
		
		// -384 or -845 = -333
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-333L, result);
	}

}
