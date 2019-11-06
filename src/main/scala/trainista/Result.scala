package trainista

sealed trait Result

object Result {

  case object Completed extends Result

  case class Continued(message: String) extends Result

}
