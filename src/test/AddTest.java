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
import program.instructions.Add;

/**
 * Tests for the Add class.
 * 
 * @author tmn1014 - Minh Nguyen
 */
public class AddTest {
	
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
	 * Test constructor for Add instruction.
	 */
	@Test
	public void testAdd() {
		Add instruction = new Add("add", "$s5", "$s7", "$t1");
		
		// test fields
		assertEquals("add", instruction.getOpcode());	// opcode
		assertEquals("$s5", instruction.getRd());		// rd
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$t1", instruction.getRt());		// rt
	}

	/**
	 * Test execution method for Add instruction with same register.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 add $t1
		Add instruction = new Add("add", "$t0", "$t1", "$t1");
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 9L);
		
		// 9 add 9 = 18
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(18L, result);
	}
	
	/**
	 * Test execution method for Add instruction with different registers.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 add $t1
		Add instruction = new Add("add", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 55L);
		myComputer.getMyRegisters().put("$t2", 322L);
		
		// 55 add 322 = 377
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(377L, result);
	}
	
	/**
	 * Test execution method for Add instruction with one negative value.
	 */
	@Test
	public void testExecutionNegative1() {
		// $t0 = $t1 add $t1
		Add instruction = new Add("add", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 384L);
		myComputer.getMyRegisters().put("$t2", -845L);
		
		// 384 add -845 = -461
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-461L, result);
	}
	
	/**
	 * Test execution method for Add instruction with 2 negative values.
	 */
	@Test
	public void testExecutionNegative2() {
		// $t0 = $t1 add $t1
		Add instruction = new Add("add", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);
		myComputer.getMyRegisters().put("$t2", -845L);
		
		// -384 add -845 = -1229
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-1229L, result);
	}

}
