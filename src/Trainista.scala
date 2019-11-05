import java.util.UUID

import scala.annotation.tailrec

object Trainista extends App {

  // todo make menu, parametrized by line IO, runnable, instantiable, and testable

  // model

  trait LineIO {
    def readLine(text: String): String
    def readLine(): String
    def printLine(x: Any): Unit
  }

  case object ConsoleIO extends LineIO {
    import scala.io.StdIn

    override def readLine(text: String): String = StdIn.readLine(text)

    override def readLine(): String = StdIn.readLine()

    override def printLine(x: Any): Unit = println(x)
  }

  sealed trait Result

  case object Completed extends Result

  case class Continued(message: String) extends Result

  type Nick = String

  /* todo
      Exercise will become de-formalized into a class and its sub-classes will become de-formalized into its instances,
      allowing easy deserialization from configuration or other record type, by mapping value-to-value, not value-to-class
   */
  trait Exercise {
    // auxiliary members
    def code: Nick = UUID.randomUUID().toString // will be used as identifier by other model objects
    def form: String // note will be used to deserialize into specific

    // essential members
    def name: String
    def description: String
    def solution: Seq[Part] // todo not all exercises will have multiple parts, consider refactoring to 'solution'
    def validate(input: String): Result
  }

  trait Part {
    def answers: Seq[String]
  }

  case class ListingPart(answers: String*) extends Part

  case class Listing(name: String, description: String, solution: ListingPart*) extends Exercise {
    override val form: String = "listing"

    // todo continue later
    val receptors: Map[String, Seq[String]] = solution.flatMap(part => part.answers.map(answer => answer -> part.answers)).toMap
    val progress: Map[Seq[String], Boolean] = null // todo continue later

    override def validate(input: String): Result = Completed // todo later
  }

  case class PairingPart(term: String, answers: String*) extends Part

  case class Pairing(name: String, description: String, solution: PairingPart*) extends Exercise {
    override val form: String = "pairing"

    override def validate(input: String): Result = Completed // todo later
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


  case class Menu(io: LineIO) {

    def start(): Unit = {
      io.printLine("Trainista started.")
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

  // source: https://stackoverflow.com/questions/1075676/scala-match-and-parse-an-integer-string
  object Int {
    def unapply(s : String) : Option[Int] = try {
      Some(s.toInt)
    } catch {
      case _ : java.lang.NumberFormatException => None
    }
  }


  // program execution
  Menu(ConsoleIO).stateOptions()

}
