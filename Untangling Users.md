## Becoming root with su
In this challenge we become root using su command. Normally we dont get the password but in this challenge it was given.
## Other users with su
In this challenge with the help of su command we switch users. su zardus and then /challenge/run.
## Cracking Passwords
Took a little time could not figure how to use john the ripper. john /challenge/shadow-leak. Then the password flashed on the screen and was able to change the user using su zardus.
## Using Sudo
In this challenge we use sudo to run the command with root access. sudo cat /flag.
