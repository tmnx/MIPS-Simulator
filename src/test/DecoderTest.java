/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import program.Decoder;
import program.instructions.Add;
import program.instructions.Addi;
import program.instructions.Addiu;
import program.instructions.Addu;
import program.instructions.And;
import program.instructions.Andi;
import program.instructions.BranchEQ;
import program.instructions.BranchNE;
import program.instructions.Instruction;
import program.instructions.Jump;
import program.instructions.JumpRegister;
import program.instructions.LoadWord;
import program.instructions.Or;
import program.instructions.Ori;
import program.instructions.SaveWord;

/**
 * Tests for the Decoder class.
 * 
 * @author tmn1014 - Minh Nguyen
 * @version 11 Novemeber 2019
 */
public class DecoderTest {
	
	/**
	 * A Decoder object to help test its class.
	 */
	private Decoder myDecoder;

	/**
	 * Create and set up a Computer and a Decoder for testing.
	 */
	@Before
	public void setUp() {
		myDecoder = new Decoder();
	}
	
	/**
	 * Test the decodeInstruction method for add.
	 */
	@Test
	public void testDecodeInstructionAdd() {
		final Instruction instruction = myDecoder.decodeInstruction("add $t7 $s2 $t3");
		
		assertEquals(true, instruction instanceof Add);
	}
	
	/**
	 * Test the decodeInstruction method for add in immediate mode.
	 */
	@Test
	public void testDecodeInstructionAddi() {
		final Instruction instruction = myDecoder.decodeInstruction("addi $t7 $s2 -9");
		
		assertEquals(true, instruction instanceof Addi);
	}
	
	/**
	 * Test the decodeInstruction method for add unsigned in immediate mode.
	 */
	@Test
	public void testDecodeInstructionAddiu() {
		final Instruction instruction = myDecoder.decodeInstruction("addiu $t7 $s2 3");
		
		assertEquals(true, instruction instanceof Addiu);
	}
	
	/**
	 * Test the decodeInstruction method for add unsigned.
	 */
	@Test
	public void testDecodeInstructionAddu() {
		final Instruction instruction = myDecoder.decodeInstruction("addu $t7 $s2 $t3");
		
		assertEquals(true, instruction instanceof Addu);
	}
	
	/**
	 * Test the decodeInstruction method for and.
	 */
	@Test
	public void testDecodeInstructionAnd() {
		final Instruction instruction = myDecoder.decodeInstruction("and $t7 $s2 $t3");
		
		assertEquals(true, instruction instanceof And);
	}
	
	/**
	 * Test the decodeInstruction method for and in immediate mode.
	 */
	@Test
	public void testDecodeInstructionAndi() {
		final Instruction instruction = myDecoder.decodeInstruction("andi $t7 $s2 9");
		
		assertEquals(true, instruction instanceof Andi);
	}
	
	/**
	 * Test the decodeInstruction method for or.
	 */
	@Test
	public void testDecodeInstructionOr() {
		final Instruction instruction = myDecoder.decodeInstruction("or $t7 $s2 $t3");
		
		assertEquals(true, instruction instanceof Or);
	}
	
	/**
	 * Test the decodeInstruction method for or in immediate mode.
	 */
	@Test
	public void testDecodeInstructionOri() {
		final Instruction instruction = myDecoder.decodeInstruction("ori $t7 $s2 3");
		
		assertEquals(true, instruction instanceof Ori);
	}
	
	/**
	 * Test the decodeInstruction method for branch if equal.
	 */
	@Test
	public void testDecodeInstructionBEQ() {
		final Instruction instruction = myDecoder.decodeInstruction("beq $t7 $s2 label");
		
		assertEquals(true, instruction instanceof BranchEQ);
	}
	
	/**
	 * Test the decodeInstruction method for branch if not equal.
	 */
	@Test
	public void testDecodeInstructionBNE() {
		final Instruction instruction = myDecoder.decodeInstruction("bne $t7 $s2 label");
		
		assertEquals(true, instruction instanceof BranchNE);
	}
	
	/**
	 * Test the decodeInstruction method for jump.
	 */
	@Test
	public void testDecodeInstructionJump() {
		final Instruction instruction = myDecoder.decodeInstruction("j label");
		
		assertEquals(true, instruction instanceof Jump);
	}
	
	/**
	 * Test the decodeInstruction method for jump register.
	 */
	@Test
	public void testDecodeInstructionJumpRegister() {
		final Instruction instruction = myDecoder.decodeInstruction("jr $s2");
		
		assertEquals(true, instruction instanceof JumpRegister);
	}
	
	/**
	 * Test the decodeInstruction method for load word.
	 */
	@Test
	public void testDecodeInstructionLoadWord() {
		final Instruction instruction = myDecoder.decodeInstruction("lw $s2 n");
		
		assertEquals(true, instruction instanceof LoadWord);
	}
	
	/**
	 * Test the decodeInstruction method for load word.
	 */
	@Test
	public void testDecodeInstructionSaveWord() {
		final Instruction instruction = myDecoder.decodeInstruction("sw $s2 0($sp)");
		
		assertEquals(true, instruction instanceof SaveWord);
	}
	
	/**
	 * Test the decodeInstruction method for IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testDecodeInstructionIAE() {
		// not supported instruction; should throw an exception
		myDecoder.decodeInstruction("jal label");
	}
	
	/**
	 * Test the decodeInstruction method for instruction with end line comment.
	 */
	@Test
	public void testDecodeInstructionComment() {
		final Instruction instruction = 
						  myDecoder.decodeInstruction("sw $s2 0($sp) # this is a comment");
		
		assertEquals(true, instruction instanceof SaveWord);
	}
}
