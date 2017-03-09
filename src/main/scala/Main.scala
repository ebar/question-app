
object Main extends App {



  val questionGraph = QuestionGraphLoader.loadQuestionGraph()

  QuestionEngine.run(questionGraph)
}

