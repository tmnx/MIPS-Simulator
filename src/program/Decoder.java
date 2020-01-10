/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package program;

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
 * Decoder class decodes String of instruction and create Instruction objects.
 * 
 * @author tmn1014
 * @version 03 November 2019
 */
public final class Decoder {
	
	/**
	 * Decode the string and create the appropriate Instruction object.
	 * 
	 * @throws IllegalArgumentException if value for immediate is out of bound.
	 */
	public Instruction decodeInstruction(final String theString) throws IllegalArgumentException {
		
		String newString = theString;
		
		if (newString.contains("#")) {					// if string contains comment
			int end = newString.indexOf("#");			// find start of comment
			newString = newString.substring(0, end);	// get string w/o comment
		}
		newString = newString.stripLeading();		// get string w/o leading white spaces
		newString = newString.stripTrailing();		// get string w/o trailing white spaces
		
		Instruction instruction = null;
		
		// Consider all white spaces as a delimiter
		final String[] tokens = newString.split("\\s+");
		
		
		// Decode and create appropriate instruction
		switch(tokens[0]) {
			case "add" :
				instruction = new Add(tokens[0], tokens[1], tokens[2], tokens[3]);
				break;
			case "addu" :
				instruction = new Addu(tokens[0], tokens[1], tokens[2], tokens[3]);
				break;
			case "and" :
				instruction = new And(tokens[0], tokens[1], tokens[2], tokens[3]);
				break;
			case "or" :
				instruction = new Or(tokens[0], tokens[1], tokens[2], tokens[3]);
				break;
			case "j" :
				instruction = new Jump(tokens[0], tokens[1]);
				break;
			case "jr" :
				instruction = new JumpRegister(tokens[0], tokens[1]);
				break;
			case "addi" :
					instruction = new Addi(tokens[0], tokens[1], tokens[2], 
									   Integer.parseInt(tokens[3]));
				break;
			case "addiu" :
				instruction = new Addiu(tokens[0], tokens[1], tokens[2], 
						   				Integer.parseInt(tokens[3]));
				break;
			case "andi" :
				instruction = new Andi(tokens[0], tokens[1], tokens[2], 
		   							   Integer.parseInt(tokens[3]));
				break;
			case "ori" :
				instruction = new Ori(tokens[0], tokens[1], tokens[2], 
						   			  Integer.parseInt(tokens[3]));
				break;
			case "lw" :
				instruction = new LoadWord(tokens[0], tokens[1], tokens[2]);
				break;
			case "sw" :
				instruction = new SaveWord(tokens[0], tokens[1], tokens[2]);
				break;
			case "beq" :
				instruction = new BranchEQ(tokens[0], tokens[1], tokens[2], tokens[3]);
				break;
			case "bne" :
				instruction = new BranchNE(tokens[0], tokens[1], tokens[2], tokens[3]);
				break;
			default :
				throw new IllegalArgumentException("Instruction " + tokens[0] + 
												   " not supported.");
		}
		return instruction;

	}
}
