# MIPS-Simulator
Simulator of MIPS at the assembly language level.

The following is a description of the different requirements our team decided on when implementing this project:

1. Labels
 * We decided that Labels need to start on a new line by itself in order to correctly parse the information into the symbol table that is shown in the GUI we used for our project. This way, if a label is used as a memory address to jump to in the line of instruction, it is not mistaken for a new label in our symbol table.

2. Commas
* We decided that there should be no commas in the instruction, as occasionally commas are used in the comments section of the text file and it is easier for the program to split the instruction into separate "words" through spaces (rather than ending up with a trailing comma by accident while splitting).

3. Data and Words
* We chose to have our program only support .word labels as it is the main example that we have used in class and it is the easiest to demonstrate in Java since it is equivalent to an Integer. Additionally, we decided to require that if there is a ".data" section in the given text file, that it must be followed by ".text" at the end of the section - as the code will properly start after the ".data" section has been decoded correctly.
* Always follow ".data" section with ".text". This will allow the application to know where the instructions begin. We've provided 2 test files to serve as examples of how to use our syntax.

4. Comments
* We decided to allow the text files to have comments in them and that our program will parse these comments. This is so the user that chooses to use our program does not have to worry about not being able to comment on their code and become confused when they try to figure out where an error has occurred for them in their file while using our program. 

5. Register Values
* Simulator prints the register and their corresponding values in decimal value. The registers that we support include $s0-$s7, $t0-$t9, $zero, $a0-$a3, $v0-$v1, and $sp. To save space on stack use the regular syntax of MIPS. For example, to save 2 space of the stack: "addi $sp $sp -8", "sw $s1 0($sp)", "sw $s2 4($sp)". To pop items off the stack: "addi $sp $sp 8"

6. Other Decisions
  * The simulator is implemented at the assembly language level.
  * To start the project off, we decided to first implement the base of the MIPS Simulator by following the format of the previous LC3 Simulator that we made in 371. From here, we chose to divide the classes into 3 sections: tests, instructions, and programs (the front end of the simulator). We followed the LC3 Simulator diagram by having separate classes for each instruction, as well as separate test classes for each of these as well. 
  * In our "programs" section, we split the simulator into 4 parts: a BitString class for saving each MIPS Instruction (which is the same LC3 Bitstring class but updated to handle MIPS 32 bit instructions), a Computer class that holds the current simulator memory and register status of the program and executes the instructions from the text file, a Decoder class that reads the text file and decodes the text into instructions, and the Main file that starts our program. 
  * We also decided to implement a GUI based on previous GUI projects we have created to make a simple MIPS Simulator GUI that shows the current memory locations and their status, the status of the registers, a console output of the code at the bottom of the GUI, and a controls option in the top left corner of the GUI that allows the user to load a chosen file to the GUI Simulator, execute said program, or exit the GUI program if desired. Our design decisions for the GUI mostly follows the same setup/style of MARS and LC3 so as to not confuse the user if they have previously used these programs and are used to this layout style. 

7. How To Run Our Simulator:
  * Commandline: java -jar Mips_executable.jar
  * Top left coner, click "Controls"
  * Click "Load File" 
  * Select a file on your computer and click "Open"
  * If file is found, console should state "Ready to be executed"
  * Click "Controls" again and click "Execute"
  * Mips Simulator completed.

8. Testing the instructions
  * We've provided JUnit test cases in the "test" package. Simply run the tests.

