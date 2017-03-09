
class QuestionEngine(cli: CommandLineInterface) {

  def serve(q: Question) : Unit = {

    def serveInternal(q: Question) : Option[Question] = {
      val next = q match {
        case ConditionalQuestion(message, nextQuestions) =>
          val answer = cli.showQuestion(message)
          nextQuestions.map { nq =>
            nq.condition match {
              case IntegerCondition(cond) => (cond(answer.toInt), nq.nextQuestion)
            }
          }.filter(_._1).map(_._2).headOption
        case DefaultQuestion(message, nextQuestion) =>
          val answer = cli.showQuestion(message)
          nextQuestion
      }

      next match {
        case Some(nextQuestion) => serveInternal(nextQuestion)
        case None => None
      }
    }
    serveInternal(q)
  }



  def run(questionGraph: QuestionGraph) = {
    val q = questionGraph.firstQuestion
    serve(q)
    println("Thankyou.")
  }

}

case class QuestionGraph(firstQuestion: Question)