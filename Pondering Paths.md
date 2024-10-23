## The Root
We had to invoke the program using its absolute path which is already provided.
## Program and absolute paths
The whole path to a program is provided and you have to type it in the terminal.
## Position thy self
We first had to run the program which gives the directory in which you have to cd into and then run the program again for the flag.
## Position elsewhere
Same as the previous challenge
## Position yet elsewhere
Same as the previous challenge
## Implicit relative paths
We are now dealing with relative paths . cd into / and then challenge/run.
## Explicit relative paths
We can use ./ for relative paths. cd / and then ./challenge/run(Tho  in this case relative and absolute path is same).
## Implicit relative path
A program cant run by calling its name directly when we are in the directory. We need to use relative path to run it. cd into /challenge and them ./run
## Home sweet home
This challenge would take the argument given and write the flag into it. Since the argument had to be absolute path and less than three characters. ~ is used since it expands to /home/hacker.The last argument can by any one letter file. /challenge/run ~/x.