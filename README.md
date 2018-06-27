# SundayAssemblyLosAngeles
## Live Better, Help Often and Wonder More!
An Android app for the Sunday Assembly community. Intended to help immerse 
assemblers 
into the Assembly with the program schedule, song lyrics and speaker profiles. 
It also enables assemblers to connect with each other and Sunday Assembly 
organizers in between assemblies, with notifications about service and social 
events and a "calendar of events" widget.
### Current State of App
![screenshot_1](sala_handheld_portrait_1.png?raw=true "MainActivity 
on a Handheld Device in Portrait")
![screenshot_2](sala_tablet_land_2.png?raw=true "MainActivity on a Tablet in
Landscape")
#### Firebase Services
The App has currently implemented the Firebase Realtime Database and Firebase 
Authorization services and is using them to create and store users. The 
Realtime Database is currently setup to store the user's emails, usernames and 
the Firebase Auth generated unique userIds.
##### Future Plans
The database should be storing full names, profile pics and essentially any 
other user information that can be pulled from providers like Google, 
Facebook and Twitter.
#### Sign-in Flow
The sign-in flow is handled with the help of the Firebase-Auth-UI library. It
 is currently set up with two sign-up/sign-in options: Email/Password and 
 Google Sign-in. 
 ![screenshot_3](sala_sign_in_handheld_portrait_1.png "The current Sign-in 
 Activity on a Handheld Device in Portrait")
##### Future Plans
The Email sign-in flow does not have an option for a user to add a profile 
picture or any other user information that can be provided from Google. Also 
the dependencies for the Twitter and Facebook sign-in options are included in
 the app but not yet implemented, so adding all those features are the next 
 step for sign-in.
 
### Issues
#### IntentService
There is an IntentService that is currently querying the Sunday Assembly Los 
Angeles website and parsing the HTML to display event info. It is 
successfully fetching and parsing the nessecary data but the app is only 
displaying the "Assembly Date" headers and hasn't been successfully 
displaying the themes, photos or descriptions that have been parsed from the 
HTML.

###### Note: The most recent commits do not reflect the issue
To address the issue earlier commits (prior to commit 2929)will have to be 
checked out and commit 
to new dev branches. 
#### Missing Features
There's a large list of drawer items in the app but only two actually provide
 new views. These all need to be implemented and the beginnings of their 
 implementation can be found in earlier commits as noted above.
 ### Contributing
 Feel free to fork it any time! Love to share.