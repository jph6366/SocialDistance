# SocialDistance
A app that accepts a distance, duration, and exhalation level from user input and determines whether the user's interaction is safe. This applies concepts including MVC, Modularity, Polymorphism/Inheritance, Factory Classes, Imperative principles, and Machine Learning. coded in Java.

     #THIS IS AN ASSIGNMENT FROM COMP 524 - PROGRAMMING LANGUAGES AT UNC AT CHAPEL HILL TAUGHT BY DR. PRASUN DEWAN FALL 2020
     #REFER TO THE WORD DOC FOR MORE DETAILED PROGRAM GUIDELINES
     #PLEASE DO NO PLAGIARIZE AND I AM NOT RESPONSIBLE IF YOU ARE CAUGHT USING THIS CODE.


You will implement three parts of a social distancing application in Java, involving the
use of (1) static Java methods, (2) the MVC pattern, and (3) the Weka decision tree
classifier.

You will additionally need the Comp524All.jar file from the Downloads folder.

Additionally, assignment specific style rules are given in this document.

Part 1: Social Distancing
Our social distancing application assumes some subject has had a safe encounter with
a guest present in the same room. It considers three input integer parameters:
1. Distance to the guest.
2. Duration the guest was in the room.
3. The exhalation level of the guest, which would vary based on whether, for
instance, the guest was quiet, talking or coughing/sneezing.
For each parameter, three points are defined: small, medium, and large. The following
table gives the current values of these points.

   Small Medium Large <br />
Distance 6 13 27<br />
Duration 15 30 120<br />
Exhalation Level 10 30 50<br />


