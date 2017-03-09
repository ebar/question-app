import scala.io.StdIn

class CommandLineInterface {

  def showQuestion(question: String, answerType: AnswerType.Value): String = {
    println(s"$question (answer type: $answerType)")
    val answer = StdIn.readLine().toString
    println(s"Your answer: $answer")
    answer
  }

}
