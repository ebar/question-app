import org.scalatest._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers.any


class FlowEngineTest extends FlatSpec with MockitoSugar with Matchers {

  it should "serve questions to CLI" in {
    val cli = mock[CommandLineInterface]

    val block1 = Block(1, "What is your name?", AnswerType.FreeText, Some(2))
    val block2 = Block(2, "How old are you?", AnswerType.FreeText, None)

    val flowEngine = new FlowEngine(cli,  Flow(Map(1 -> block1, 2 -> block2)))
    flowEngine.run

    verify(cli).showQuestion("What is your name?", AnswerType.FreeText)
    verify(cli).showQuestion("How old are you?", AnswerType.FreeText)

  }

  it should "serve questions to CLI if condition is met" in {
    val cli = mock[CommandLineInterface]

    val block1 = Block(1, "Enter a number less than 5", AnswerType.FreeText, Some(3))
    val block2 = Block(2, "end", AnswerType.FreeText, None)
    val decision1 = Decision(3, List(Conditional(IntegerCondition((x) => x < 5), 2)))


    val flowEngine = new FlowEngine(cli,  Flow(Map(1 -> block1, 2 -> block2, 3 -> decision1)))
    when(cli.showQuestion("Enter a number less than 5", AnswerType.FreeText)).thenReturn("3")
    flowEngine.run

    verify(cli).showQuestion("Enter a number less than 5", AnswerType.FreeText)
    verify(cli).showQuestion("end", AnswerType.FreeText)

  }

  it should "not serve question to CLI if condition is not met" in {
    val cli = mock[CommandLineInterface]

    val block1 = Block(1, "Enter a number less than 5", AnswerType.FreeText, Some(3))
    val block2 = Block(2, "end", AnswerType.FreeText, None)
    val decision1 = Decision(3, List(Conditional(IntegerCondition((x) => x < 5), 2)))


    val flowEngine = new FlowEngine(cli,  Flow(Map(1 -> block1, 2 -> block2, 3 -> decision1)))
    when(cli.showQuestion("Enter a number less than 5", AnswerType.FreeText)).thenReturn("8")
    flowEngine.run

    verify(cli).showQuestion("Enter a number less than 5", AnswerType.FreeText)
    verify(cli, never()).showQuestion("end", AnswerType.FreeText)
  }

}
