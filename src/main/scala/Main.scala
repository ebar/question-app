
object Main extends App {

  val flow = FlowCreator.buildFlow()
  new FlowEngine(new CommandLineInterface, flow).run

}

