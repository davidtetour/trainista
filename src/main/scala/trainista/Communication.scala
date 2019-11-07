package trainista

object Communication {

  object Command {
    val Quit = "quit"
  }

  object Message {
    val ApplicationStarted: String = "Trainista started.\n"
    val ExerciseOptions: String = "Exercise options:"
    val ExerciseOption: (Int, Exercise) => String = (index, ex) => s"$index: ${ex.name}"
    val HowToQuit: String = s"To quit the program enter '${Command.Quit}'."
    val RequestInput: String = "enter option: "
    val InputNotRecognized: Any => String = unknown => s"Input '$unknown' not recognized, provide another input."
    val ExerciseCompleted: String = "Exercise completed.\n"
    val Bye: String = "Bye!"
  }

}
