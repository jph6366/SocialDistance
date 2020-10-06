# SocialDistance
A app that accepts a distance, duration, and exhalation level from user input and determines whether the user's interaction is safe. This applies concepts including MVC, Modularity, Polymorphism/Inheritance, Factory Classes, Imperative principles, and Machine Learning. coded in Java.


You will implement three parts of a social distancing application in Java, involving the
use of (1) static Java methods, (2) the MVC pattern, and (3) the Weka decision tree
classifier.
Parts of the assignment will be added as we cover relevant aspects in class and our
tests get written. The first part can be written with just a knowledge of Java.
You will additionally need the Comp524All.jar file from the Downloads folder.
You will be graded for both correctness and style. There will be extra credit for meeting
both kinds of requirements.
The general style guide for all Java programs is here. It has some formatting rules that
we will remove and do not hurt to obey. These rules do not contradict what you have
learned from previous classes on how to write well documented programs. The modified
general style guide should be complete by Monday morning, September 7 th .
Additional, assignment specific style rules are given in this document.
Part 1: Social Distancing
Our social distancing application assumes some subject has had a safe encounter with
a guest present in the same room. It considers three input integer parameters:
1. Distance to the guest.
2. Duration the guest was in the room.
3. The exhalation level of the guest, which would vary based on whether, for
instance, the guest was quiet, talking or coughing/sneezing.
For each parameter, three points are defined: small, medium, and large. The following
table gives the current values of these points.

Small Medium Large
Distance 6 13 27
Duration 15 30 120
Exhalation Level 10 30 50

Table 1 Small, Medium and Large Values

Assume the following table contains the safe combinations of these points.
Distance Duration Exhalation Level
Medium Medium Medium
Small Medium Small
Large Medium Large
Medium Small Large
Medium Large Small
Large Large Medium
Small Small Medium

Table 2 Assumed Given Safe Combinations

Some of these assumed combinations are based on results from web searches that
indicate that &lt; 15 minute interaction is always safe, a person sitting two rows ahead or
behind a person in an airplane (which is about 6 feet) was infected, and a person’s
breath travels 13 feet when talking and 27 feet when sneezing/coughing.
We will call these assumed combinations as data given to the application.
isGivenSafe() static function
Write a static function called isGivenSafe that returns a boolean, and takes three int
parameters representing a distance, duration, and exhalation level. It thus, has the
signature:
isGivenSafe: int*int*int��boolean

In general, the syntax of a signature is:
&lt;function name&gt;: &lt;parameter 1 type&gt;*&lt;parameter 2 type&gt;*&lt;parameter type&gt;��&lt;retur
type&gt;
If the combination of the method parameters is safe, based on the given data, the
function returns true. Otherwise, it returns false. In other words, only if the combination
is an exact match in Table 2, it returns true. The next function considers ranges.
Let us call the class in which this method is implemented as the social distancing
utility class. All static methods required in this assignment should be in this class. The
class should be public and the required methods in it should all also be public;
otherwise our grader cannot access them. The name of the class is up to you and
specified in the registry.
In general, a function or procedure you are asked to implement can, and probably
should, call other methods in the same or different class.
Three-Parameter isInterpolatedSafe() static function
Write another static function in the social distancing utility class with the signature:
isInterpolatedSafe: int*int*int��boolean
Again, the three parameters represent distance, duration, and exhalation level. The
function interpolates each of the parameters to a value in Table 2, and determines if the
interpolated values are safe based on whether they occur in Table 2.
The interpolation is conservative. If higher values of the parameter are safer, then it is
interpolated low. If lower values of the parameter are safer, then it is interpolated high.
Consider a value V and a sorted sequence of values I 1 , I 2 , … I N to which it must be
interpolated.
Then its high interpolation, H, to this sequence of values is defined as follows:
H = Maximum Integer If V &gt; I N
H = I 1 , if V &lt;= I 1.
H = I M, if V &gt; I M-1 , V &lt;= I M for 1 &lt; M &lt;= N
Its low interpolation, L, to this sequence of values is defined as follows:
L = I N , if V &gt;= I N.
L = I M-1, if V &gt;= I M-1 , V &lt; I M for 1 &lt; M &lt;= N
L = 0 If V &lt; I 1
Thus, low interpolation to a sequence of values is either an element of the sequence or
zero.
Similarly, high interpolation to a sequence of values is either an element of the
sequence or max integer.

Again, V is the value you have, and I 1 , I 2 , … I N are the ordered values to which you must
interpolate, which will be low, medium and high values for the three safety parameters.
As higher distances are safer, the distance parameter is interpolated low.
This means the distance parameter is interpolated to small, medium, large distances or
the value 0.
As lower duration and exhalation levels are safer, duration and exhalation levels are
interpolated high.
Thus, the duration parameter is interpolated to small, medium large durations or the
maximum integer.
Similarly, the exhalation level parameter is interpolated to small, medium large
exhalation levels or the maximum integer.
In Java, the maximum integer is Integer.MAX_VALUE.
Two-Parameter isInterpolatedSafe() static function
Write a static function in the social distancing utility class with the signature:
isInterpolatedSafe: int*int��boolean
The two parameters represent distance and duration. It fixes the value of exhalation
level to medium exhalation. It determines if the combination of interpolated distance,
interpolated duration, and medium exhalation level is safe.
One-Parameter isInterpolatedSafe() static function
Write a static function in the social distancing utility class with the signature:
isInterpolatedSafe: int��boolean
The single parameter represents a distance. It fixes the value of duration and
exhalation level to medium duration and medium exhalation level respectively. It
determines if the combination of interpolated distance, medium duration, and medium
exhalation level is safe.
printGeneratedCombinationDerivedSafety() static function
This procedure has the signature:
printGeneratedCombinationDerivedSafety: �� void.
It uses Math.random() function to generate a distance, duration, and exhalation level
combination.

It determines whether this combination is safe based on the isDerivedSafe() function
described below
It prints the (distance, duration, exhalation level, and safety) tuple, using a comma to
separate the elements of the 4-tuple.
The next method will call this method repeatedly to generate these 4-tuples. These
tuples can be examples for (a) testing your solutions, (b) demonstrating to us that your
code works, and (c) data you give in the last part of this assignment to the Weka
classifier. To help train it, it would be useful to choose values near the edge cases in
Table 2. You are free to choose the range of values for each parameter from which you
pick randomly. For training the classifier, it might be useful to make the maximum value
in this range to, say, 1.2 times the maximum value for the parameter in the table. Play
around to get good results in version 3 of this program, it is not clear twice will give you
the desired values. For this version, it does not matter what the range is.
printGivenAndGeneratedCombinationsDerivedSafety() static function
This procedure has the signature:
printGivenAndGeneratedCombinationsDerivedSafety: �� void.
It prints the following header:
Distance,Duration,Exhalation,IsSafe
For each 3-tuple combination in Table 2, it adds the Boolean true to create a 4-tuple
combination and prints the 4-tuple, again using a comma to separate the elements of
the tuple.
It prints a separator line with one or more hyphens.
It then calls printGeneratedCombinationDerivedSafety() ten times.
Thus, its output should look like this:
Distance,Duration,Exhalation,IsSafe
13,30,30,true
6,30,10,true
27,30,50,true
13,15,50,true
13,120,10,true
27,120,30,true
6,15,30,true
----------------
7,15,9,true
11,11,2,true
12,14,20,true
10,8,9,true
3,2,23,false

11,28,16,false
0,13,11,false
12,5,3,true
1,20,10,false
8,21,28,false

Please check all outputs with all constraints imposed.
generateSafeDistancesAndDurations() static function
This procedure has the signature:
generateSafeDistancesAndDurations:int��List&lt;Integer[]&gt;.
The parameter represents an exhalation level.
The procedure computes a (possibly empty) list of given safe distance and duration
pairs that are associated with an interpolation of the exhalation level in Table 2.
Each pair is returned by a two-element array whose first element is the distance and
second element is the duration.
printSafeDistancesAndDurations() static function
Signature:
printSafeDistancesAndDurations:int�� void
The parameter is an exhalation-level. It uses the method above to determine the list of
safe distances and durations for the passed exhalation-level and prints the passed
exhalation level together with the returned list using the format below:
&lt;exhalation level&gt;, [{&lt;destination,duration1}, … {&lt;destination&gt;, &lt;duration&gt;}]
Thus, the following three calls:
printGivenSafeDistancesAndDuration(9);
printGivenSafeDistancesAndDuration(11);
printGivenSafeDistancesAndDuration(51);
output the following three lines:
9,[{6,30}{13,120}]
11,[{13,30}{27,120}{6,15}]
51,[]
Three-Parameter isDerivedSafe() static function
Write another static function in the social distancing utility class with the signature:
isDerivedSafe: int*int*int��boolean
Again, the three parameters represent distance, duration, and exhalation level.

It returns true if the combination of these three parameters is safer than at least one of
the combinations in the table.
A combination (distance 1 , duration 1 , exhalation_level 1 ) is safer than combination
(distance 2 , duration 2 , exhalation_level 2 ) if:
distance 1 &gt;= distance 2 and duration 1 &lt;= duration 2 and exhalation_level 1 &lt;=
exhalation_level 2 .
Style Constraints: Regular and Extra Credit
Several of the general style constraints are applicable there such as use of mnemonic
names, named constants, final parameters, block count and levels.
In addition, in the implementation of the required static methods, you must pay
particular emphasis on the related metrics of modularity, extensibility and reusability.
Doing so may earn you extra credit based on whether our automatic and manual
grading objectively recognizes optional decisions that improve the solution in these
three dimensions.
You may also earn extra credit points for elegance of solution measured by the number
of relational and boolean operators, and conditional and looping constructs your solution
uses. Less use of them should also lead to improvement in other metrics.
As mentioned above, to improve these metrics, you are allowed to and, in fact,
encouraged to write additional optional methods called by the methods required in the
social distancing utility class. These methods can be in the same or different classes
and packages. The style credit you get will not consider the class or package of these
additional methods.
The description of the problem is modular to encourage a modular solution.
If an optional or required method you write is related to some requirement sentences in
this document, then, as in A1, put all of the relevant sentences, verbatim, as a Javadoc
comment before the header of the method. Such a comment has the format:
/** comment */
For instance, the isGivenSafe() method should have the comment:
/**
If the combination of the method parameters is safe, based on the given data, the
function returns true. Otherwise, it returns false.
*/
A sentence should not have a line break in it. Different sentences can be on different
lines.

You do not have to but can put obvious text such as the following describing method
headers:
/**
Write a static function called isGivenSafe that returns a boolean, and takes three int
parameters representing a distance, duration, and exhalation level. It thus, has the
signature:
isGivenSafe: int*int*int��boolean
*/
We will try to match the JavaDoc of the non-required methods you write to various
parts of the requirements. The more such methods are matched to the requirements,
the more modular your solution is.
The requirements of some of the more complex functions such as isInterpolatedSafe()
have multiple sentences describing different sub-requirements. It is possible to
implement a monolithic method that supports all of these sub-requirements or write
different methods for different sub-requirements. The latter approach will result in more
matched methods and also, of course, a more modular solution.
Part 2: Three Interactive Programs for Three Safety Functions
In the next part of the assignment, you will create three different main classes, all of
which create the following user interface, illustrated below:
Please enter data regarding your guest interaction.
Distance?
20
Duration?
20
Exhalation Level?
20
java.beans.PropertyChangeEvent[propertyName=Distance; oldValue=null;
newValue=20; propagationId=null; source=&lt;Object toString()&gt;]
java.beans.PropertyChangeEvent[propertyName=Duration; oldValue=null;
newValue=20; propagationId=null; source=&lt;Object toString()&gt;]
java.beans.PropertyChangeEvent[propertyName=ExhalationLevel; oldValue=null;
newValue=20; propagationId=null; source= &lt;Object toString()&gt;]
java.beans.PropertyChangeEvent[propertyName=Safe; oldValue=null; newValue=true;
propagationId=null; source=&lt;Object toString()&gt;]
Safe.
Please enter data regarding your guest interaction.
Distance?
5
Duration?
5

Exhalation Level?
5
java.beans.PropertyChangeEvent[propertyName=Distance; oldValue=null; newValue=5;
propagationId=null; source=&lt;Object toString()&gt;]
java.beans.PropertyChangeEvent[propertyName=Duration; oldValue=null; newValue=5;
propagationId=null; source=&lt;Object toString()&gt;]
java.beans.PropertyChangeEvent[propertyName=ExhalationLevel; oldValue=null;
newValue=5; propagationId=null;
source=safeSocialization.model.ADerivingSafeSocializationModel@511baa65]
java.beans.PropertyChangeEvent[propertyName=Safe; oldValue=null; newValue=false;
propagationId=null; source=&lt;Object toString()&gt;]
Not Safe!
Please enter data regarding your interaction with your guest interaction.
Distance?
-1
Quitting
The three main classes, which we will refer to as basic main, interpolating main, and
deriving main, differ in the functions they use to compute the safety of a combination
of input safety parameters, using isGivenSafe(), isInterpolatedSafe(), and
isDerivedSafe(), respectively.
The main methods quit if a negative value is input for any of the input safety
parameters.
To promote reusability, you should use the MVC and Factory design pattern:

Bean-based MVC
A model and view should use the Bean API discussed in lectures to define an
observable and observer of property changes.
This means they should use the following external types:
java.beans.PropertyChangeListener; // type of an observer of editable properties
java.beans.PropertyChangeEvent; // type of value containing the notification
java.beans.PropertyChangeSupport; // helper type to keep notify observers in an
observable
A model should define the following Bean instance properties:
1. int Distance (readonly or editable, up to you)
2. int Duration (readonly or editable, up to you)
3. int ExhalationLevel (readonly or editable, up to you)
4. boolean Safe (readonly), returns true if the combination of parameters above is
safe.

Please review the material on Bean properties. The name of a property is not the same
as an internal variable holding its value - in fact, a Bean property may not have an
associated internal variable. Thus, do not begin the name of the internal variable with an
uppercase letter, which violates Java case conventions.
These are the exported Bean properties and not the related Bean instance variables.
Look at the class material carefully for a definition of properties. Instance properties are
defined by instance getter and setter methods; static properties are defined by static
getters and setters.
As the three mains use three different safety conditions, three different model classes
are needed.
(Do you need multiple view and controller classes?)
A controller should handle the input of each of the three safety parameters After their
input, it should call a method in the model, whose signature is up to you, giving the
values of these three parameters. The model should change the value of the three
properties associated with these parameters, and also the value of the Safe property
computed from these parameters. After announcing the values of the four properties to
its observers. The model should announce null as the old values of the properties to
ensure the old values are not the same as the new ones.
A model should announce the change, using the Bean notification scheme illustrated in
class examples.
Whenever a view receives a PropertyChangeEvent, e, it should use
System.out.println(e) to print out the event, as shown above. The toString() method
invoked on the event will, in turn, call the toString() method on the event source - here
the model. What this method returns will depend on the class of the model. In the
examples above, the placeholder
&lt;Object toString()&gt;
stands for this return value.
If the received event announces the value of the Safe property, then the view should not
only print the event, as described above, but also print out the line
Safe.
or
Not Safe!
depending on whether the new value is true or false.
Factories
A model, controller and view class should define a constructor with no parameters. It
can define other optional constructors.

These classes will be singletons, that is, only one instance of them will be created by a
main class.
In addition, for each of them, define a Factory class, each of which has a single static
editable property, named Singleton. The type of the property of course is defined by
you. Thus, you will create three factory classes for the controller, view, and model,
respectively. (In the class examples, the property was named differently in different
factories, here it has the same name.) Even though you have multiple model classes,
there is a single factory associated with them, which chooses the instance of the model
class.
The singleton returned by the model/view/controller factory should either be one that
has been set using the setter method, or an instance of a default model/view/controller
(which is up to you) class known to the factory class.
An instance of a model, view, or controller should be created only by a main class or the
associated factory class and a factory setter method should be called a single time. This
ensures that the three classes are singletons, and also promotes reusability in the three
main classes you will create.
Please check if these constraints are consistent with the output!
Style Constraints for Part 2
There are almost no modularity decisions to be made, as you are following design
constraints. So there is no requirement for JavaDoc comments, but you are free to add
them.
All general style constraints should be obeyed. The ones specially applicable are the
ones requiring classes to define interfaces and the use of interfaces to type variables.
Part 3: Weka and Machine Learning
This part involves using the Weka J48 decision tree classifier to infer whether a
combination of input parameters is safe.
You will create a Weka file with a given name that contains examples for safe and
unsafe social distance.
You will create a new factory for returning a classifier with a built-in model.
You will extend the safe socialization utility class of part 1 to provide additional static
methods that allow prediction-based safety and compare the four kinds of predictions on
the same data. The functions defined so far in the utility class relied on internal global
state (Table 1 and 2) represented as internal constants. The Weka-based functions
instead rely on the external classifier provided by the factory above.

Finally you will extend theMVC and factory-based framework of part 2 to create another
main method implementing the user-interface above, that now infers rather than
computes safety.
Weka ARFF File
The file follows the arff format illustrated in class. To allow it to be displayed by default
text editors in Gradescope and your local computers, we require the file name suffix to
be .txt rather than .arff (used in class examples).
Specifically, the file should be named SafeSocialization.txt and be located in the
project directory. If you are not using Eclipse to run your program, you will need to
create a copy of the file also in the directory such as bin from which your programming
environment runs the program. Also the Eclipse project should have a separate src and
bin directory. See A0 writeup for what that means.
In your program, do not use the absolute path of the file to access the file. When we
grade this on our computers or on Gradescope, the absolute path will change. So refer
to it by its local name in the project directory, which is simply the name above. There
should not be any forward or backward slash in the name you use.
After inserting the appropriate header, add example tuples that allow safety to be
inferred from the three input parameters. Your goal is to add as few examples as follows
to give the desired result. You can but do not have to start with the examples output by
printGivenAndGeneratedCombinationsDerivedSafety() in your utility class.
You will need to start the file with the keyword “@relation” and then the name of your
relation (e.g., “social-distancing-safety”).
If the above does not work, you need the following header as the first line of the file:
@relation programmer-weka.filters.supervised.instance.SMOTE-C0-K5-P100.0-S1-
weka.filters.supervised.instance.SMOTE-C0-K5-P100.0-S1-
weka.filters.supervised.instance.SMOTE-C0-K5-P100.0-S1-
weka.filters.supervised.instance.SMOTE-C0-K5-P100.0-S1
Social Distance Classifier Factory
Implement a social distance classifier factory that is like the other factories above,
except that the singleton it stores is an instance of a Weka Classifier.
weka.classifiers.Classifier, with a model built from the Weka file above. This means, the
getter simply returns a Classifier instance after invoking the method on it that builds the
tree model.
Thus, it too should have a property named Singleton, and this time, the type of the
property is given to you, weka.classifiers.Classifier. The default singleton its getter
returns will be an instance of weka.classifiers.trees.J48. To build into the model derived
from the example file, doYou can not use thenot use the utility class, WekaUtil,
fromused in class lectures. Instead use the one in,

gradingTools.comp524f20.assignment1, WekaUtil. to build into the model derived from
the example file.

.
Three-Parameter isInferredSafe() static function
Write a static function (in the social distancing utility class) with the signature:
isInferredSafe: int*int*int��boolean
Again, the three parameters represent distance, duration, and exhalation level.
It returns true if the combination of these three parameters is inferred to be safe based
on the model built from the Weka data file.
printGivenAndGeneratedCombinationsInferredSafety() static function
This is just like the printGivenAndGeneratedCombinationsDerivedSafety() function
except that it prints inferred rather than derived safety.
The two procedures should of course share as much code as possible. You are free to
make the common code reused by the two printing methods to be aware of the ways to
compute safety. Consider what you would have to do if we decided to later also print
given and interpolated safety - could you reuse the common code without making
changes? When you study ML and Lisp, you will see more elegant ways that do not
require the common code to have such awareness. This exercise motivates those ways.
The non-elegant approach assumes you are living in a world of static methods. Java
interfaces and lambda expressions provide an extensible way that is more cumbersome
than ML and the non elegant approaches. Feel free to use them.
compareSafetyComputations
compareSafetyComputations: � intvoid
Here is the example output of the function:
Distance,Duration,Exhalation,Derived,Inferred
8,12,11,true,true
5,6,27,false,false
6,1,26,true,true
6,15,23,true,true
12,15,21,true,true
12,8,5,true,true
11,20,27,false,true
5,27,28,false,false

10,5,13,true,true
1,25,14,false,false

It generates ten random combinations of the three input parameters and prints for each
combination the derived and inferred safety values. As you can see, there can be false
positives and negatives in the inferred values. Play around with your examples in the
examples file and execute this method a few times to reduce/eliminate the false
positives and negatives. The method returns the number of correct inferences. Thus, if it
has one false positive and one false negative, it returns 8.
As the format of the output is different from the other printing procedures, you are not
expected to reuse the println() calls. But you should reuse the code to generate a
random combination.
Weka MVC
Create a new main class, which we will refer to as the inferring main. It has the same
user-interface and MVC/Factory constraints as above. It will thus require the
implementation of a new model class, also with a no parameter constructor. This model
class will use isInferredSafe() function of the utility class to compute safety.

Utility Class Tester
Create also a main class, which we will refer to as the social distance utility tester main, that
calls the following utility class methods at the start:
printGivenAndGeneratedCombinationsDerivedSafety();
printGivenAndGeneratedCombinationsInferredSafety();
compareSafetyComputations();
In addition, it calls printSafeDistancesAndDurations three times, with medium exhalation
level, medium exhalation level -1 and medium exhalation level + 2, respectively. This
class should be separate from the social distance utility class.
Class Registry
The local checks for A1 are not yet completely written, but the class registry interface
exists:
gradingTools.comp524f20.assignment1.SocialDistanceClassRegistry.
Implement this class, and let us know if the method names are not self-explanatory.
