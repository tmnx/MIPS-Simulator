/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package test;

import static org.junit.Assert.*;

import program.Computer;
import program.instructions.Or;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Or class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class OrTest {
	
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
		Or instruction = new Or("or", "$s5", "$s7", "$t1");
		
		// test fields
		assertEquals("or", instruction.getOpcode());	// opcode
		assertEquals("$s5", instruction.getRd());		// rd
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$t1", instruction.getRt());		// rt
	}
	
	/**
	 * Test execution method for Or instruction with same register.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 or $t1
		Or instruction = new Or("or", "$t0", "$t1", "$t1");
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 9L);
		
		// 9 or 9 = 9
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(9L, result);
	}
	
	/**
	 * Test execution method for Or instruction with different registers.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 or $t1
		Or instruction = new Or("or", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 55L);
		myComputer.getMyRegisters().put("$t2", 322L);
		
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
		Or instruction = new Or("or", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 384L);
		myComputer.getMyRegisters().put("$t2", -845L);
		
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
		Or instruction = new Or("or", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);
		myComputer.getMyRegisters().put("$t2", -845L);
		
		// -384 or -845 = -333
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-333L, result);
	}

}
