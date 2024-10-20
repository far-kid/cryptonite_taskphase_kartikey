# Chaining with Semicolons
Semicolon can be used to chain multiple commands together . /challenge/pwn ; /challenge/college.
# Your First Shell Script
Wasnt able to figure out how to create an sh script. Decided to echo /challenge/pwn >x.sh and then >>. Now run the script using bash x.sh.
# Redirecting script Output
first echo /challenge/pwn >script.sh and then echo /challenge/college >>script.sh.
Now ./script.sh | /challenge/solve to pipe the output of script into challenge/solve.

# Executable Shell Script
First we create a shell script using echo /challenge/solve > script.sh. And then chmod +x script.sh. Now run the executable file using ./script.sh
