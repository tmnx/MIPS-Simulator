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
import program.instructions.Andi;


/**
 * Tests for the Andi class.
 * 
 * @author tmn1014 - Minh Nguyen
 */
public class AndiTest {

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
	public void testAndi() {
		Andi instruction = new Andi("andi", "$s5", "$s7", -432);
		
		// test fields
		assertEquals("andi", instruction.getOpcode());	// opcode
		assertEquals("$s7", instruction.getRs());		// rs
		assertEquals("$s5", instruction.getRt());		// rt
		assertEquals(-432, instruction.getImmediate()); // immediate
	}

	/**
	 * Test execution method for And instruction with same register.
	 */
	@Test
	public void testExecution1() {
		// $t0 = $t1 And $t1
		Andi instruction = new Andi("andi", "$t0", "$t1", 43);
		
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
		Andi instruction = new Andi("andi", "$t0", "$t1", 322);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 55L);
		
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
		Andi instruction = new Andi("andi", "$t0", "$t1", -845);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", 384L);
		
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
		Andi instruction = new Andi("or", "$t0", "$t1", -845);
		
		// put some values in registers
		myComputer.getMyRegisters().put("$t1", -384L);
		
		// -384 And -845 = -896
		instruction.execute(myComputer);
		long result = myComputer.getMyRegisters().get("$t0");
		
		assertEquals(-896L, result);
	}

}
