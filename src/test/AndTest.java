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
import program.instructions.And;

/**
 * Tests for the And class.
 * 
 * @author tmn1014 - Minh Nguyen
 */
public class AndTest {

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
	 * Test constructor for And instruction.
	 */
	@Test
	public void testAnd() {
		And instruction = new And("and", "$s5", "$s7", "$t1");
		
		// test fields
		assertEquals("and", instruction.getOpcode());	// opcode
		assertEquals("$s5", instruction.getRd());		// rd
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$t1", instruction.getRt());		// rt
	}

	/**
	 * Test execution method for And instruction with same register.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 And $t1
		And instruction = new And("and", "$t0", "$t1", "$t1");
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 43L);
		
		// 43 And 43 = 43
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(43L, result);
	}
	
	/**
	 * Test execution method for And instruction with different registers.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 And $t1
		And instruction = new And("and", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 55L);
		myComputer.getMyRegisters().put("$t2", 322L);
		
		// 55 And 322 = 2
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(2L, result);
	}
	
	/**
	 * Test execution method for And instruction with one negative value.
	 */
	@Test
	public void testExecutionNegative1() {
		// $t0 = $t1 And $t1
		And instruction = new And("and", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 384L);
		myComputer.getMyRegisters().put("$t2", -845L);
		
		// 384 And -845 = 128
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(128L, result);
	}
	
	/**
	 * Test execution method for And instruction with 2 negative values.
	 */
	@Test
	public void testExecutionNegative2() {
		// $t0 = $t1 And $t1
		And instruction = new And("and", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);
		myComputer.getMyRegisters().put("$t2", -845L);
		
		// -384 And -845 = -896
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-896L, result);
	}

}
