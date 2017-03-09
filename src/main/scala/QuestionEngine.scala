import scala.io.StdIn

object QuestionEngine {

  def serve(q: Question) : Option[Question] = {

    def serveInternal(q: Question) : Option[Question] = {
      q match {
        case ConditionalQuestion(message, nextQuestions) =>
          val answer = showQuestion(message)
          nextQuestions.filter(_.condition(answer)).map(_.nextQuestion).headOption
        case DefaultQuestion(message, nextQuestion) =>
          val answer = showQuestion(message)
          nextQuestion
      }
    }

      serveInternal(q) match {
      case Some(nextQuestion) => serveInternal(nextQuestion)
      case None => None
    }
  }

  def showQuestion(message: String): String = {
    println(message)
    val answer = StdIn.readLine().toString
    println(s"your answer: $answer")
    answer
  }

  def run(questionGraph: QuestionGraph) = {
    val q = questionGraph.firstQuestion
    serve(q)
  }

}

case class QuestionGraph(firstQuestion: Question)