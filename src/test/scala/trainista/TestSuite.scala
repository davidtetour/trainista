package trainista

import trainista.Trainista.LineIO

object TestSuite extends App {

  // todo implement testing io
  case class TestIO(input: Seq[String]) extends LineIO {

    private val inputs = input.iterator

    override def readLine(text: String): String = inputs.next()

    override def readLine(): String = inputs.next()

    override def printLine(x: Any): Unit = ()
  }

  // todo stub
  assert {
    val exercises = Nil
    val input = Seq("quit")

    Menu(TestIO(input), exercises).start()
    true
  }

  // todo implement other tests
  assert {
    true
  }

  println("All tests passed")
}
