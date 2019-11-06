package trainista.extractor

/** source: https://stackoverflow.com/questions/1075676/scala-match-and-parse-an-integer-string */
object Int {
  def unapply(s : String) : Option[Int] = try {
    Some(s.toInt)
  } catch {
    case _ : java.lang.NumberFormatException => None
  }
}
