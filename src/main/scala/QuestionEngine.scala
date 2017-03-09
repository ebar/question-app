
class QuestionEngine(cli: CommandLineInterface) {

  def serve(q: Question) : Unit = {
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
        case Some(nextQuestion) => serve(nextQuestion)
        case None => None
      }
  }



  def run(questionGraph: QuestionGraph) = {
    serve(questionGraph.firstQuestion)
    println("Thankyou.")
  }

}

case class QuestionGraph(firstQuestion: Question)