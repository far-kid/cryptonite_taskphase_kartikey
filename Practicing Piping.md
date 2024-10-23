
## Redirecting output
in this challenge we are introduced to > command which redirects the output to a file.
in this we had to output PWN on file College

## Redirecting more Output
this challenge was similar with the previous challenge. We had to print the output of /challenge/run on the file /myflag then simply using cat command to read the output.

## Appending output
this challenge we are introduced with the >> command which appends the output file instead of overwriting. We simply had to use this for /challenge/run then use cat command to read the-flag file for the flag.

## Redirecting Errors
In this we had to redirect the output to myflag and error to instructions using /challenge/run >myflag 2> instructions

## Redirecting Input
the challenge involved first writing COLLEGE into the PWN file then using < to redirect this to /challenge/file to get the output

## Grepping stored Results
This challenge involved redirecting output of /challenge/run to a text file then using grep command to find the flag, this is achieved by using grep pwn (filename).

## Grepping live results
This challenge introduced the piped operator, we simply do /challenge/run | grep pwn to run and grep the flag.

## Grepping errors
This challenge involved redirecting output of standard error to standard output then simply grepping for the flag.
2>& 1

## Duplicating piped data with tee


## Writing to multiple programs
In this challenge we need to redirect the output of /challenge/hack into two files at once using tee substitution and we achieve it using /challenge/hack | tee>(/challenge/the)>(/challenge/planet) command to get the flag 
## Split piping 
this was a 