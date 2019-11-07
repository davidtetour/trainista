package trainista

import trainista.Communication.Message._
import trainista.Communication._
import trainista.Trainista.LineIO
import trainista.Result._
import trainista.extractor.Int

import scala.annotation.tailrec

case class Menu(io: LineIO, exercises: Seq[Exercise]) {

  def start(): Unit = {
    io.printLine(ApplicationStarted)
    stateOptions()
  }

  def stateOptions(): Unit = {
    io.printLine(ExerciseOptions)
    exercises.zipWithIndex.foreach {
      case (exercise, index) => io.printLine(Message.ExerciseOption(index, exercise))
    }
    io.printLine(HowToQuit)
    handleOption()

    def run(exercise: Exercise): Unit = {
      io.printLine(exercise.description)
      exercise.validate(io.readLine()) match {
        case Completed =>
          io.printLine(ExerciseCompleted)
        case Continued(message) =>
          io.printLine(message)
          exercise.validate(io.readLine())
      }

    }

    @tailrec
    def handleOption(input: String = io.readLine(RequestInput)): Unit =
      input match {
        case Int(n) if exercises.indices.contains(n) =>
          run(exercises(n))
          stateOptions()
        case Command.Quit =>
          io.printLine(Bye)
        case unknown =>
          io.printLine(InputNotRecognized(unknown))
          handleOption()
      }
  }

}