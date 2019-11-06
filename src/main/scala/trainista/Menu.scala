package trainista

import trainista.Trainista.LineIO
import trainista.Result._
import trainista.extractor.Int

import scala.annotation.tailrec

case class Menu(io: LineIO, exercises: Seq[Exercise]) {

  def start(): Unit = {
    io.printLine("Trainista started.\n")
    stateOptions()
  }

  def stateOptions(): Unit = {
    io.printLine("Exercise options:")
    exercises.map(_.name).zipWithIndex.foreach {
      case (name, index) => io.printLine(s"$index: $name")
    }
    io.printLine("To quit the program enter 'quit'.")
    handleOption()

    def run(exercise: Exercise): Unit = {
      io.printLine(exercise.description)
      exercise.validate(io.readLine()) match {
        case Completed =>
          io.printLine("Exercise completed.\n")
        case Continued(message) =>
          io.printLine(message)
          exercise.validate(io.readLine())
      }

    }

    @tailrec
    def handleOption(input: String = io.readLine("enter option: ")): Unit =
      input match {
        case Int(n) if exercises.indices.contains(n) =>
          run(exercises(n))
          stateOptions()
        case "quit" =>
          io.printLine("Bye.")
        case unknown =>
          io.printLine(s"Input '$unknown' not recognized, provide another input.")
          handleOption()
      }
  }

}