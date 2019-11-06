package trainista

import java.util.UUID

import trainista.Result._

/* todo
    Exercise will become de-formalized into a class and its sub-classes will become de-formalized into its instances,
    allowing easy deserialization from configuration or other record type, by mapping value-to-value, not value-to-class
 */
trait Exercise {
  import Exercise._

  type Nick = String

  // auxiliary members
  def code: Nick = UUID.randomUUID().toString // will be used as identifier by other model objects
  def form: String // note will be used to deserialize into specific

  // essential members
  def name: String
  def description: String
  def solution: Seq[Part] // todo not all exercises will have multiple parts, consider refactoring to 'solution'
  def validate(input: String): Result
}

object Exercise {

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

}

