## Matching with *
In this challenge we have to cd to /challenge using at most 4 characters. We use * to do so
```
cd /ch*
./run
```
## Matching with ?
In this we introduced to ? as a single char wildcard. same as before
```
cd /?ha??enge
./ru?
```
## Matching with []

This  introduces [] glob, we cd into /challenge/files then run the "run" program with argument file_[bash]
```
 cd /ch*/f*
 /ch*/r* file_[bash]
```
## Matching Paths with []
Relatively the same as the previous challenge. We use the path as an argument using `[]` glob to get the flag. 
```
/ch*/run /ch*/files/file_[bash]
```
## Mixing globs
In this challenge, we have to ensure that the glob is not more than 6 characters. Used [cep] to match with the required files. Tried to  use it as it is [cep]. Did not work. Then used [cep]*
```
/challenge/files$ /challenge/run [cep]*
```
## Exclusionary globbing
Similar to the last challenge. We use !/^ when we want to filter the files
```
cd /ch*/files
/ch*/r* [^pwn]*
```
