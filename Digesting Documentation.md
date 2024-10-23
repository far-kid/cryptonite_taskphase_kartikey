## Learning From Documentation
/challenge/challenge had to be run with the --giveflag argument
```
/challenge/challenge --giveflag
```
## Learning Complex Usage
More complex argument passing
```
/challenge/challenge --printfile /flag
```
## Reading Manuals
In this challenge at first we need to read the manual of challenge by man challenge. Upon reading the man page we found the command to run the flag
```
man challenge
/challenge/challenge --ddglsa 880
```
## Searching Manuals
In this first we had to open the manual and then search for the correct flag command.
```
man challenge
/challenge/challenge --gp
```
Searched the manual by at first / and then flag and then 'n'.
## Searching for Manuals
A tricky challenge. First did man man and then looked for challenge. No pattern found. Then i read the manual and found -k command. Decided to man -k challenge. ```

```
ueahhlfdcz (1)       - print the flag!
man ueahhlfdcz
/challenge/challenge --ueahhl 564
```
found the command then read the documentation of the command and then finally found the flag.
## Helpful Programs
In this challenge first we used the --h argument. Then we needed to find the value using -p. Using the usage page we ran /challenge/challenge -g 339
```
/challenge/challenge --h
/challenge/challenge -p
/challenge/challenge -g 339
```
## Help for Builtins
Same as before. Here we use help challenge instead of -h