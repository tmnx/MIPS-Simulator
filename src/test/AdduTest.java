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
import program.instructions.Addu;

/**
 * Tests for the Addu class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class AdduTest {

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
	 * Test constructor for Add unsigned instruction.
	 */
	@Test
	public void testAdd() {
		Addu instruction = new Addu("addu", "$s5", "$s7", "$t1");
		
		// test fields
		assertEquals("addu", instruction.getOpcode());	// opcode
		assertEquals("$s5", instruction.getRd());		// rd
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$t1", instruction.getRt());		// rt
	}

	/**
	 * Test execution method for Add unsigned instruction with same register.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 Addu $t1
		Addu instruction = new Addu("addu", "$t0", "$t1", "$t1");
		
		// put some value in register
		myComputer.getMyRegisters().put("$t1", 9543L);
		
		// 9543 Addu 9543 = 19086
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(19086L, result);
	}
	
	/**
	 * Test execution method for Add unsigned instruction with different registers.
	 */
	@Test
	public void testExecution2() {
		// $t0 = $t1 Addu $t1
		Addu instruction = new Addu("addu", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 55L);
		myComputer.getMyRegisters().put("$t2", 322L);
		
		// 55 Addu 322 = 377
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(377L, result);
	}
	
	/**
	 * Test execution method for Add unsigned instruction with negative value.
	 */
	@Test
	public void testExecutionNegative() {
		// $t0 = $t1 Addu $t1
		Addu instruction = new Addu("addu", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 384L);
		myComputer.getMyRegisters().put("$t2", -845L);	// treat as unsigned
		
		// 384 Addu -845 = 4294966835
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(4294966835L, result);
	}
	
	/**¡
	 * Test execution method for Add unsigned instruction with invalid negative value.
	 * Should throw an exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExecutionIAE() {
		// $t0 = $t1 Addu $t1
		Addu instruction = new Addu("addu", "$t0", "$t1", "$t2");
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);	// treat as unsigned
		myComputer.getMyRegisters().put("$t2", -845L);	// treat as unsigned
		
		// -384 Addu -845 = 8589933363
		instruction.execute(myComputer);
	}

}
