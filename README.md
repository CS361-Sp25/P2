# Project 2: Nondeterministic Finite Automata

* Author: Jayce Lowry and Chase Stombaugh
* Class: CS361 Section 1
* Semester: Spring 2025

## Overview

This program models non-deterministic finite automata with the 5-tuple. Operations are
defined for creating NFAs and testing their acceptance of strings.

## Reflection

### Jayce

This project was certainly more complicated than implementing the DFA. However, I think
our design choices for the DFA ultimately made it easier to determine where things would
go for the NFA. For example, representing transitions was fairly trivial because now
in each state, we just store a set of states in our HashMap rather than single states.
I did realize that my first implementation was incorrect after studying the provided
test suite and noticing that we need to union the transition sets when adding transitions,
and not overwrite them.

The other thing I was responsible for was writing additional unit test cases. I think
the way I went about it at first was not very effective. I spent time drawing out
more complicated machines and then implemented them in the tester, but that approach
was more time-consuming than what was practical, and it likely didn't catch as many of
the edge cases that we actually care about. My second approach was to start with smaller
machines and work out the possibilities, which I think made it easier and faster to write
more tests and catch edge cases.

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

```shell
javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java
```


This will compile all necessary java files, including the NFA implementation and 
JUnit tests.

### Running

To ensure the implementation is correct, run the JUnit test cases using:

```shell
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.nfa.NFATest
```


This will execute all test cases, including:
* NFA creation (including states and transitions)
* NFA correctness testing
* E-closure computation
* String acceptance testing
* Tracking maximum state copies
* Validation of DFA properties

These tests improve our confidence that the implementation correctly models 
an NFA with epsilon transitions.

## Sources used

https://docs.oracle.com/javase/8/docs/api/java/util/Set.html - Used for managing state 
transitions Course notes - guidance on NFA structure, E-closure.
