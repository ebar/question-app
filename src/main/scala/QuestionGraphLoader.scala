object QuestionGraphLoader {

  def over18Condition(input: Int) = input > 18

  def under18Condition(input: Int) = input <= 18

  def loadQuestionGraph() : QuestionGraph = {

    new QuestionGraph(
      DefaultQuestion("What is your name?", Some(ConditionalQuestion("How old are you?",
      List(Conditional(IntegerCondition(over18Condition), DefaultQuestion("Do you have a driving license?", None)),
        Conditional(IntegerCondition(under18Condition), DefaultQuestion("Enter your pet name please", None)))))))

  }

}
