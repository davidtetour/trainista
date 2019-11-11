# Trainista
Trivia in command line interface.

## Overview

The objective is to develop a command line application for trivia exercises,
such as 'what is the capital of Czechia?', that could serve as a precedent
for a web or mobile application.

The nature of the exercises will likely be rather limited to validating specific
information structures, such as the collections of countries or rivers on a continent,
or the translations of common colors for a foreign language.

Ideally, the user will only be required to compose structured notes and the program,
upon parsing the notes will store the underlying information structures to compose and use it
to compose and schedule exercises.

Currently, the repository has barely any source code and majority of effort is spent on
planning and tinkering.

## Features

Main feature of the application will be concern for skill/knowledge maintenance.
Time spent on exercises and time expected to be spent on exercises will be measured
and communicated to the user.

User's fluency based on speed and correctness will determine the scheduling of future exercises
for a given topic.

## Design 

Implementation:
Object-oriented and functional scala with an effort given to avoid sophisticated techniques,
to make this recreational code adaptable and maintainable.
As a personal exercise, I intend to develop the codebase in a test-driven fashion,
relying primarily on unit testing. 

Storage:
Initially, exercises will be instantiated in main source code.
Eventually, exercises will be stored in a NoSQL database as json.
Possibly, as an intermediate step, exercises will be stored in a json resource file.
