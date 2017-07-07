# Java-TPMV-Compiler
It is a very simple imperative language compiler into bytecode. This virtual machine is also capable of executing the generated code using its simulated CPU, Stack and Memory.

## Source program grammar
* **SourceProgram** ::= Instruction1 . . . Instruction
* **Instruction** ::= SimpleAssignment | CompoundAssignment | While | If | Write | Return
* **SimpleAssignment** ::= Variable = Term
* **CompoundAssignment** ::= Variable = Term ArithmeticOper Term
* **While** ::= while BooleanCond SourceProgram endwhile
* **If** ::= if BooleanCond SourceProgram endif
* **Write** ::= write Variable
* **Term** ::= Variable | Number
* **ArithmeticOper** ::= + | - | / | *
* **Variable** ::= a | . . . | z
* **Number** ::= any integer number
* **BooleanCond** ::= Term BooleanOper Term
* **BooleanOper** ::= = | != | <= | <

## Virtual Machine available commands
* **HELP:** it shows the description of the following commands.
* **QUIT:** closes the virtual machine.
* **LOAD FICH:** loads the file named FICH as the source program. It doesn't check the syntax.
* **REPLACEBC N:** asks for a new bytecode instruction and replaces the line N of the bytecode program by the new introduced instruction.
* **COMPILE:** does the lexical analysis of the source program, generating a new parsed program and, later from the parsed program generates a bytecode program.
* **RUN:** executes the bytecode program.

## Examples
A while instruction looks like this:
```
while BooleanCond
instruccion_1
instruccion_2
...
endwhile
```
And a if one should look as below:
```
if BooleanCond
instruccion_1
instruccion_2
...
endif
```
### Nested whiles source program example
```
x = 5
r = 0
while 0 < x
y = x
s = 1
while 0 < y
s = s * y
y = y - 1
endwhile
write s
r = r + s
x = x - 1
endwhile
return
end
```

## Credits
* Built from scratch by *David Arroyo*
