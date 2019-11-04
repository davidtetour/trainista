import java.util.UUID

import scala.io.StdIn

object Trainista extends App {

  // todo make menu, parametrized by line IO, runnable, instantiable, and testable

  // model

  type Nick = String

  trait Exercise {
    def code: Nick = UUID.randomUUID().toString
    def name: String
    def form: String
    def intro: String
    def parts: Seq[Part] // todo not all exercises will have multiple parts, consider refactoring to 'solution'

    // todo add runExercise which handles offsets, name, intro, run, and conclusion
    def run(): Unit = {
      println(intro)
    }
  }

  trait Part {
    def answers: Seq[String]
  }

  case class ListingPart(answers: String*) extends Part

  case class Listing(name: String, intro: String, parts: ListingPart*) extends Exercise {
    override val form: String = "listing"

    override def run(): Unit = {
      super.run() // todo refactor this away
      println("Exercise completed.\n")
    }
  }

  case class PairingPart(term: String, answers: String*) extends Part

  case class Pairing(name: String, intro: String, parts: PairingPart*) extends Exercise {
    override val form: String = "pairing"

    override def run(): Unit = {
      super.run()
      println("Exercise completed.\n")
    }
  }

  // data

  val southAmericanCountries: Listing = Listing(
    "South American Countries",
    "Write the names of all countries in South America separated by comma.",
    ListingPart("Brazil"),
    ListingPart("Chile")
  )

  val southAmericanCapitals: Pairing = Pairing(
    "South American Capitals",
    "Write the name of a capital city for each given country.",
    PairingPart("Brazil", "Brasilia"),
    PairingPart("Chile", "Santiago")
  )

  val exercises: Seq[Exercise] = Seq(southAmericanCapitals, southAmericanCountries)

  // controller

  def runMenu(): Unit = {
    println("Exercise options:")
    exercises.map(_.name).zipWithIndex.foreach {
      case (name, index) => println(s"$index: $name")
    }
    println("To quit the program enter 'quit'.")
    runInput()

    /* todo
    *   take user input
    *   if matches exercise index, run exercise, then menu again
    *   if matches 'quit' exit
    *   else inform input not recognized and ask for input again.
    */

    def runInput(input: String = StdIn.readLine("entered option: ")): Unit =
      input match {
        case Int(n) if exercises.indices.contains(n) =>
          exercises(n).run()
          runMenu()
        case "quit" => println("Bye.")
        case unknown => println(s"Input '$unknown' not recognized, try again.")
      }
  }

  // source: https://stackoverflow.com/questions/1075676/scala-match-and-parse-an-integer-string
  object Int {
    def unapply(s : String) : Option[Int] = try {
      Some(s.toInt)
    } catch {
      case _ : java.lang.NumberFormatException => None
    }
  }


  // program execution

  println("Trainista launched.")
  runMenu()

}
