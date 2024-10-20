## Cat
We are introduced with cat command, using it to read the flag
## Catting absolute paths
We use cat with absolute path to read the file
## More catting practice
Same thing as above just the directory is changed
## Grepping for a needle in Haystack
Grep command is used to find a specific string. Here since the flag starts with pwn.college we grep it.
grep pwn.college /challenge/data.txt
## Listing Files
To list all the files in the current directory ls is used.
## Touching Files
Touch command to create a file is introduced. We create 2 files in the tmp directory and then run the flag command
## Removing Files
Use 'rm' command to remove a file.
rm delete_me
## Hidden Files
ls -a command it used to list out all including the hidden files in the directory. .file is a hidden file.
## Epic File Quest Game
Longest challenge. But quite easy.ls -a into a number of directories and using cat to read the clues. Also sometimes had to cd and sometimes without had to cat the clue
## Making Directory
We are introduced with mkdir command to make a new directory. We first make a dir. pwn in temp.
```
cd temp
mkdir pwn
```
then make file 'college' using touch
```
touch college
/challenge/run
```
## Finding Flags
We are introduced with find command. Here we have to find a file named flag which is hidden the filesystem. Using-
```
find / -name flag
```
It gives us all the files/directories named flag. Through trial and error we found the flag.
## Linking Files
In this challenge we learn to use ln -s command to link files. We link the /flag to /home/hacker/not-the-flag using 
```
ln -s /flag /home/hacker/not-the-flag
```
and then /challenge/catflag reads content from the symbolic link we created giving us the flag.
