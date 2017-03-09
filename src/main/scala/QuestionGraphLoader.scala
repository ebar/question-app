object QuestionGraphLoader {

  def trueCondition(input: String) = {
    true
  }

  def loadQuestionGraph() : QuestionGraph = {
    new QuestionGraph(ConditionalQuestion("dummy question",
      List(Condition(trueCondition, DefaultQuestion("dummy next question", Some(DefaultQuestion("blah bnlah", None)))))))

  }

}
