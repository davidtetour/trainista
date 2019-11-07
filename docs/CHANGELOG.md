# Changelog

### version 0.0.1 
19-11-03
single file console program written
console menu introduced

### version 0.0.2
19-11-04
git version control introduced
added CHANGELOG, README, and LICENSE documents

### version 0.0.3
19-11-05
introduced simplified IO object as parameter of menu

### version 0.0.4
19-11-06
refactored code into proper package structure
introduce simplified unit test stub structure via assertions

### version 0.0.5
19-11-07
commands and messages were refactored out into `Communication` object
testable io `TestIO' implemented
unit test for quit implemented

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

### test for handling terminating and invalid user menu input

## Implemented

### testing prerequisites
introduce simplified IO object
refactor menu into instantiable object  
introduce simplified unit tests
19-11-06
