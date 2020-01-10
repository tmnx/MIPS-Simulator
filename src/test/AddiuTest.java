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
import program.instructions.Addiu;

/**
 * Tests for the Addiu class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class AddiuTest {

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
	 * Test constructor for Add immediate unsigned instruction.
	 */
	@Test
	public void testAdd() {
		Addiu instruction = new Addiu("addiu", "$s5", "$s7", 383);
		
		// test fields
		assertEquals("addiu", instruction.getOpcode());	// opcode
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$s5", instruction.getRt());		// rt
		assertEquals(383, instruction.getImmediate());  // immediate
	}

	/**
	 * Test execution method for Add immediate unsigned instruction 
	 * with zero.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 Addiu $t1
		Addiu instruction = new Addiu("addiu", "$t0", "$t1", 0);
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 9543L);
		
		// 9543 Addiu 0 = 9543
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(9543L, result);
	}
	
	/**
	 * Test execution method for Add immediate unsigned instruction 
	 * with positive values.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 Addiu $t1
		Addiu instruction = new Addiu("addiu", "$t0", "$t1", 322);
		
		// put some values in register
		myComputer.getMyRegisters().put("$t1", 55L);
		
		// 55 Addiu 322 = 377
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(377L, result);
	}
	
	/**
	 * Test execution method for Add immediate unsigned instruction 
	 * with one negative value.
	 */
	@Test
	public void testExecutionNegative() {
		// $t0 = $t1 Addiu $t1
		Addiu instruction = new Addiu("addiu", "$t0", "$t1", 384);
		
		// put some values in register
		myComputer.getMyRegisters().put("$t1", -845L);	// treat as unsigned
		
		// 384 Addiu -845 = 4294966835
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(4294966835L, result);
	}
	
	/**¡
	 * Test execution method for Add unsigned instruction with negative invalid value.
	 * Should throw an exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecutionIAE() {
		// $t0 = $t1 Addiu $t1
		Addiu instruction = new Addiu("addiu", "$t0", "$t1", -845);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);	// treat as unsigned
		
		// -384 Addiu -845 = 8589933363
		instruction.execute(myComputer);
	}

}
