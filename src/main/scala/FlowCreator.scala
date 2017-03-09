object FlowCreator {

  def over18Condition(input: Int) = input > 18

  def under18Condition(input: Int) = input <= 18

  def buildFlow() : Flow  = {
    Flow(Map(
      1 -> Block(1, "What is your name?", AnswerType.FreeText, Some(2)),
      2 -> Block(2, "How old are you?", AnswerType.FreeText, Some(5)),
      3 -> Block(3, "Do you have a driving license?", AnswerType.Boolean, None),
      4 -> Block(4, "Enter your pet name please", AnswerType.FreeText, None),
      5 -> Decision(5, List(Conditional(IntegerCondition(over18Condition), 3),
                       Conditional(IntegerCondition(under18Condition), 4)))))
  }

}

case class Flow(components: Map[Int, FlowComponent])