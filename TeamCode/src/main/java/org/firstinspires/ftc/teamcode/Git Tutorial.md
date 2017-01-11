[settings]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/as_settings.png
[settings circle]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/as_settings_circl.png
[Directory circle]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/Directory_circle.png
[Directory circle2]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/Directory_circle2.png
[Directory git]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/Directory_Git_circle.png
[select git]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/selectgit_circle.png
[executable]: https://raw.githubusercontent.com/EliSauder/RandomImages/master/exacutable_circle.png

# Git VCS (Version control System) With Android Studio

For our robot we used git as a version control to keep a backup of our code. This is a command line/android studio tutorial on how to setup and use it. If you don't want to use command line the Github app is a good idea it will let you do the main processes that we will be doing in the command line tools using a GUI (Graphical User Interface).

  * ##### Github app download
    * **Mac:**
      ```
      https://mac-installer.github.com/mac/GitHub%20Desktop%20222.zip
      ```

    * **Windows:**
      ```
      https://github-windows.s3.amazonaws.com/GitHubSetup.exe
      ```
    * **EULA:** </br>
      ```
      https://desktop.github.com/eula/
      ```

## Setting Up Git With Android Studio
### Configuring Android Studio To Work With Git

</br>

  1. **Mac Users:** </br> Find the ```Android Studio``` tab in the menu bar and open it. </br> </br> **Windows Users:** </br> Go to the ```Window``` tab in the menu bar and open it. </br> </br> </br>
  2. **Windows and Mac Users:** </br> Go down to ```Preferences``` and open it. </br> </br> </br>
  3. **Windows and Mac Users:** </br> In preferences find the ```Version Control```! tab and click on it. </br> The window that opens should look something like this: </br></br>![][settings] </br></br></br>
  4. **Windows and Mac Users:** </br> Click on the small + sign in the bottom left hand side of the upper box area. </br></br>![][settings circle] </br> </br> </br>
  5. **Windows and Mac Users:** </br> Once in the new window that opens up you will want to click on ```Configure VCS...``` to check wether or not git is setup properly or not. </br></br> ![][Directory circle] </br></br></br>
  6. **Windows and Mac Users:** </br> In the new window select git. </br></br>![][select git] </br></br>If there is a file directory in ```path to Git executable``` skip to step 9. If not, follow along.</br></br> ![][executable] </br></br>
  7. <p>**Mac Users:** </br> This problem means that you probably don't have git installed, if you do skip to step 8. </br> Go to:

    ```
    https://git-scm.com/download/mac
    ```

    to get the installer. </br></br></p>
  **Windows Users:** </br> This problem means that you probably don't have git installed, if you do skip to step 8. </br> Go to:
    ```
    https://git-scm.com/download/win
    ```
    to get the installer. </br></br></br>
  8. **Mac Users:** </br> After installing git go back to the window we stopped on last. In the field type: ```/usr/local/bin/git``` Then hit ok. </br></br> ![][executable] </br></br>
  **Windows Users:** </br> After installing git go back to the window we stopped on last. In the field type: ```C:\Program Files\Git\bin\git.exe``` if you have a 62-but system or ```C:\Program Files (x86)\Git\bin\git.exe``` if you have a 32-bit system. Then hit ok.</br></br> ![][executable] </br></br></br>
  9. **Mac and Windows Users:** </br> After setting up the git executable if you needed to. You need to put the desired repository directory in the directory box. </br></br> ![][Directory circle2] </br></br> You only need to have the ```TeamCode``` folder, so it will look something like: ```<DIRECTORY TO FOLDER>/ftc-app-master/TeamCode/``` Then after you have put your desired directory in, go to step 10. </br></br></br>
  10. **Mac and Windows Users:** </br> After adding the directory you need to set the VCS to git. </br></br> ![][Directory git] </br></br> After that hit ```Apply``` then ```Ok```(if any errors pop up ignore them we will fix those in *Configuring Git*).

### Configuring Git

Now that Android studio recognizes that directory as a repository you have to actually create one. You can do this from the Github app but I will only be using Android Studio's build in ```Terminal``` feature. ***This assumes that you have at least a little experience with command line if you don't know what I am referring to look it up on your own.***

1. You will want to log into git to start off. ***This assumes you have a github account if not make one.*** </br> Go into the in the bottom bar of android studio and open: ```Terminal```*(Or just open terminal (Mac) or cmd (Windows) either way works).* </br> Once there type:
  ```
  $ git config --global user.name "<YOUR GIT USERNAME>"
  ```
  and
  ```
  $ git config --global user.email "<YOUR GIT LOGIN EMAIL>"
  ```
This sets up the global git settings to use your account. </br></br>

2. After you have logged in, navigate to the directory you made Android studio look for a repository by using the ```cd``` command. </br> Once you are there type:
  ```
  $ git init
  ```
This initiates the git repository so you may now close any errors you had. </br></br>
3. You will want to add the local (where you are in the file system at the time of the command) repository (folder that you went to using ```cd```).</brr> To do that type:
  ```
  $ git add .
  ```
If you want to add individual files that you need to git type:
  ```
  $ git add <FILE NAME>
  ```
If you want to add whole folders do:
  ```
  $ git add <FOLDER NAME>/*
  ```
  </br>
4. The next step is the make your first ```commit``` of what you have. </br> To do this type:
  ```
  $ git commit -m "<YOUR COMMIT MESAGE>"
  ```
A commit message is a short blip of information that describes the changes you made to the contents of the repository.</br></br>
5. You now want to sync this repository with one you have already created on the website ***This assumes that you know, and have made a repository on the github.com website with your account for the project.*** </br> To do this type:
  ```
  $ git remote add <NAME OF ONLINE REPOSITORY> <REPOSITORY URL>
  ```
  After that we want to verify that that url works to do this type:
  ```
  $ git remote -v
  ```
  Next we want to push what we committed earlier:
  ```
  $ git push -u <NAME OF ONLINE REPOSITORY> master
  ```
  "master" is the "branch" that you are pushing to. If you have a different branch that you want to push to use that branch name instead of master. I will not be going over how to make/use branches in this markdown file though. But if you want experiment go for it.

## Using Git After Setting Up

### General Use
After you get used to Git is really a very simple process. After you make changes that you are happy with and want to upload you commit them by typing:
```
$ git commit
```
While you are in the local repository directory. After you have made all the edits for a certain amount of time (for example after every day's work) you push all your commits (changes) to the online repository by typing:
```
$ git push
```
While in the local repository directory. Every time you want to add new files you just need to type:
```
$ git add <FILE NAME>
```
Then commit it.

### Downloading and setting up a git repository that you made and have set up online

1. Navigate to a folder where you want to put the repository on your computer in Terminal (Mac) or CMD (Windows).</br></br>
2. You next want to "clone" what you have online to the folder you just made. </br> You do this by typing:
  ```
  $ git clone <YOUR REPOSITORY URL>
  ```
  </br>
3. Next you will need to initiate that repository for your computer. </br> You do this by typing:
  ```
  $ git init
  ```
While in the repository directory. **Make sure that you are in the repository directory because when you clone a repository it creates a new folder named after the online name** </br> </br>
4. After that make sure that everything is added by typing:
  ```
  $ git add .
  ```
  </br>
5. Then you will be able to commit it and push it.



This was written by Elijah Sauder if you have any questions contact team 6226-Bambusa at bambusachsr@gmail.com </br></br></br>



All downloads are property of github not team Bambusa. </br></br>


Git: https://github.com/git/git-scm.com </br>
The source code for the site is licensed under the MIT license, which you can find in the MIT-LICENSE.txt file.
</br>All graphical assets are licensed under the Creative Commons Attribution 3.0 Unported License.</br></br>
Git license: <hr> *Copyright (c) 2012 Scott Chacon*

*Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:* </br></br>
*The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.* </br></br>
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.*
<hr></br></br>
GitHub: *© 2016 GitHub, Inc. All rights reserved.*
</br>GitHub EULA: https://desktop.github.com/eula/
</br></br>
***Team 6226-Bambusa takes zero credit for the design and creation of either of these programs.***
