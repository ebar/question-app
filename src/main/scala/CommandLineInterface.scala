import scala.io.StdIn

class CommandLineInterface {

  def showQuestion(message: String): String = {
    println(message)
    val answer = StdIn.readLine().toString
    println(s"Your answer: $answer")
    answer
  }

}
