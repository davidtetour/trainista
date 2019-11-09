package trainista

import trainista.Communication.Command._
import trainista.Communication.Message._
import trainista.Exercise._
import trainista.Trainista.LineIO

import scala.collection.mutable.ArrayBuffer

object TestSuite extends App {

  case class TestIO(input: String*) extends LineIO {

    private val inputs = input.iterator

    override def readLine(text: String): String = inputs.next()

    override def readLine(): String = inputs.next()

    override def printLine(x: Any): Unit = output.append(x.toString)

    val output: ArrayBuffer[String] = ArrayBuffer()
  }

  // todo ? find convenient method to setup test io and test menu with useful defaults, bellow is not that useful
  //  object Menu {
//    def apply(io: LineIO): Menu = new Menu(io, exercises = Nil)
//  }
  // perhaps wrap TestIO inside of Testing class with Menu, output, io fields; with auto-start

  val mockListExercise = Listing("Mock Listing", "mock listing description")
  val mockPairingExercise = Pairing("Mock Pairing", "mock pairing description ")

  // Menu specification

  // quit recognition specification
  assert ({
    val io = TestIO(Quit)
    Menu(io, exercises = Nil).start()
    io.output.last == Bye
  }, s"Menu did not respond to '$Quit' input with '$Bye' message.")

  // improper input handling specification
  assert {
    val unrecognizable = "unrecognizable"
    val io = TestIO(unrecognizable, Quit)
    Menu(io, exercises = Nil).start()
    io.output contains InputNotRecognized(unrecognizable)
  }

  // specification for
  // proper menu introduction
  // listing exercise options in proper order
  // quit command explanation
  assert {
    // todo add other spec parts
    val io = TestIO(Quit)
    val exercises = Seq(mockListExercise, mockPairingExercise)
    Menu(io, exercises).start()
    val options = exercises.zipWithIndex.map { case (ex, index) => ExerciseOption(index, ex)}
    options forall io.output.contains
  }

  // specification for
  // correct menu exercise selection
  assert {
    // todo implement
    true
  }


  // Exercise specification

  // todo

  println("All tests passed")
}
