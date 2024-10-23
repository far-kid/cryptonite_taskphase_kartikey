## Listing Processes
Here we are introduced with the ps aux/ps-ef command. It lists all the different processes running in the system. There I found the /challenge/
```
ps aux
 /challenge/28536-run-12391
```
## Killing Processes
Here we found out how to kill a process using kill command
somehow ps-ef command is not working for me
```
ps aux | grep dont_run
kill 73
/challenge/run
```
## Interrupting Processes
Using ctrl +c to interrupt a process
```
/challenge/run
^C
```
## Suspending Processes
Here we used ctrl +z to suspend a process
## Resuming Processes
Here we first suspended the program using ctrl+z and then used fg to resume it
```
/challenge/run
ctrl+z
fg
```
## Backgrounding Processes
In this, we used bg to resume the process in background
```
/challenge/run
ctrl+z
bg
/challenge/run
```
## Foregrounding Processes
In this, we first used ran the command then stopped it using ctrl z , ran it in background using bg and then in foreground using fg command
```
/challenge/run
ctrl+z
bg
fg
```
## Starting Backgrounded Processes
We can directly start a program in background using &
```
/challenge/run &
```
## Process Exit Codes
In this we used ? to find the exit code and $ to read it
```
/challenge/get-code
/challenge/submit-code $?
```
