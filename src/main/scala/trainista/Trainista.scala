package trainista

import trainista.Exercise._

object Trainista extends App {

  trait LineIO {
    // todo keep both readLine methods?
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

  // program execution

  Menu(ConsoleIO, exercises).start()

}
