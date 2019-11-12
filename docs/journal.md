## Future changes

### configurability prerequisites
introduce dependency management system, probably sbt
introduce configuration dependency, probably PureConfig
extract exercise content into configuration

### dropping the form field
the `form` field was originally intended as an identifier of the Exercise
type for future deserialization; it seems however, that the structure of the `solution`
field will be unique for different Exercise types and thus sufficient indicator of Exercise type

### prerequisites for proper unit tests
introduce dependency management system, sbt
introduce ScalaTest dependency
organize tests into test suite

### interrupting an exercise
implement stopping an exercise upon 'stop' text input
as a fallback of exercise input validation

### introduce tests for menu-level input handling

### exercise class refactoring
introduce `AtomicExercise` and `CompoundExercise`
include `mascot` as an inherited field from `Mascot` trait
follow up with dropping `form` field, to remove all non-essential fields

### Refactor Menu to a Loop
The core of the program is in fact a state loop that loops between presenting menu and
presenting an exercise until the program is ended with quit. 
The behavior of the program seems very similar to conventional main game loops.
Additional loops may be introduced initiating from the presenting of the menu,
such as showing stats.
Additional refactorings: 
 - exercise option -> exercise choice
 - running menu -> presenting menu
 - running exercise -> presenting exercise
Eventually a proper state transition mechanism may be implemented, where the dialogue
with the user is an effect / trigger of such state changes.

## Implemented

### testing prerequisites
introduce simplified IO object
refactor menu into instantiable object  
introduce simplified unit tests
19-11-06
