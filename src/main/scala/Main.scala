
object Main extends App {

  val questionGraph = QuestionGraphLoader.loadQuestionGraph()
  new QuestionEngine(new CommandLineInterface).run(questionGraph)

}

