## Printing Variables
In this, we used echo to print out variable (FLAG in this case)
```
echo $FLAG
```
## Setting Variables
This challenge told how to set a variable in the terminal, doing this gave the flag.
```
PWN=COLLEGE
```
(no spaces)
## Multiword variables
Similar to the previous ones. Use "" when more than 1 word
```
PWN="COLLEGE YEAH"
```
## Exporting Variables
In this challenge first we had to export the variable PWN with value COLLEGE and then variable COLLEGE=PWN. Then /challenge/run PWN.
```
export PWN="COLLEGE"
COLLEGE=PWN
/challenge/run PWN
```
## Printing exported variables
In this challenge we had to use the command env. This command prints all the exported variables.
```
env
```
## Storing command output
In this, we are introduced with the method to store the output of a command into a var.$()
```
PWN=$(/challenge/run)
echo $PWN
```
## Reading Input
In this challenge we are introduced with the read command.
```
read PWN
>COLLEGE
```
## Reading Files
In this challenge we directly read a file into a variable using the command read
```
read /challenge/read_me <PWN
```
