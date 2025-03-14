# Project 2: Nondeterministic Finite Automata

* Author: Jayce Lowry and Chase Stombaugh
* Class: CS361 Section 1
* Semester: Spring 2025

## Overview

// TODO Jayce

## Reflection

### Jayce

// TODO Jayce

### Chase

This project was a big step up from the DFA implementation. While DFA was relatively 
straightforward with one active state at a time, NFA required managing multiple state 
transitions in parallel. The hardest part for me was making sure to get the accepts() 
to work properly. Since an NFA can be in multiple states at once, tracking all possible 
states at each step in the string was challenging. My initial implementation wasn’t 
properly handling epsilon transitions which led me to get some incorrect acceptance 
results. The second part that was a little difficult was the maxCopies() which led 
me to counting duplicate state copies. Both of these issues were pretty much straightened 
out by using print statements to see what was going on behind the scenes and what was 
being registered.

Then again, time management was also a bear. Thankfully I didn’t have as many assignments 
due and no wedding to attend, which made my life a little easier than last time. There 
were just other assignments to worry about though, including the HW5 for this class. Another 
issue that I personally had, and Jayce was able to help fix, was basically just GitHub as a 
whole. I made another mistake, and I think the same one as during P1 where I messed up and 
didn’t pull everything correctly before making changes. This time it definitely led to more 
confusion and workarounds.

## Compiling and Using

### Compiling
To compile the NFA implementation and test cases on the Onyx server, navigate to the 
project's root directory and run:

`javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java`


This will compile all necessary java files, including the NFA implementation and provided 
JUnit tests.

### Running

To ensure the implementation is correct, run the JUnit test cases using:

`java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar 
org.junit.runner.JUnitCore test.nfa.NFATest`


This will execute all test cases, including:
* State creation and transitions
* E-closure computation
* String acceptance testing
* Tracking maximum state copies
* Validation of DFA properties

If and only if all tests pass, the implementation correctly models an NFA with epsilon 
transitions.

## Sources used

https://docs.oracle.com/javase/8/docs/api/java/util/Set.html - Used for managing state 
transitions Course notes - guidance on NFA structure, E-closure.
