
class FlowEngine(cli: CommandLineInterface, flow: Flow) {

  def serve(c: FlowComponent, previousAnswer: Option[String]) : Unit = {
      val result = c match {
        case Block(id, question, answerType, nextId) =>
          val answer = cli.showQuestion(question, answerType)
          Result(Some(answer), lookup(nextId))

        case Decision(id, conditions) =>
          val nextId = previousAnswer match {
            case Some(answer) =>
              conditions.filter(c => c.condition.c(answer.toInt)).map(_.nextId).headOption
            case None => println("Cannot have decision with no previous answer"); None
          }
          Result(None, lookup(nextId))
      }
      result.nextComponent match {
          case Some(next) => serve(next, result.answer)
          case None => println("Finished.")
        }
  }


  def lookup(nextId: Option[Int]): Option[FlowComponent] =
    nextId.flatMap(flow.components.get)

  def run = {
    flow.components.get(1) match {
      case Some(component) => serve(component, None)
      case None => println("First block not found!")
    }
  }
}

case class Result(answer: Option[String], nextComponent: Option[FlowComponent])