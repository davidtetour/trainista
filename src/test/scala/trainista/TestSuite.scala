package trainista

import trainista.Communication.Command._
import trainista.Communication.Message._
import trainista.Trainista.LineIO

import scala.collection.mutable.ArrayBuffer

object TestSuite extends App {

  case class TestIO(input: Seq[String]) extends LineIO {

    private val inputs = input.iterator

    override def readLine(text: String): String = inputs.next()

    override def readLine(): String = inputs.next()

    override def printLine(x: Any): Unit = output.append(x.toString)

    val output: ArrayBuffer[String] = ArrayBuffer()
  }

  // Menu specification

  // quit recognition test
  assert ({
    val input = Seq(Quit)
    val io = TestIO(input)

    Menu(io, exercises = Nil).start()
    io.output.last == Bye
  }, s"Menu did not respond to '$Quit' input with '$Bye' message.")

  // todo implement improper input handling test
  assert {
    true
  }

  // todo implement test for correct listing of all exercises

  // Exercise specification

  // todo

  println("All tests passed")
}
