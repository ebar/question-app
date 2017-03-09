import org.scalatest._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._


class QuestionEngineTest extends FlatSpec with MockitoSugar with Matchers {

  it should "serve questions to CLI" in {
    val cli = mock[CommandLineInterface]
    val questionEngine = new QuestionEngine(cli)

    val endQuestion = DefaultQuestion("A question with no next question", None)
    val question = DefaultQuestion("A question with only one next question", Some(endQuestion))
    questionEngine.serve(question)

    verify(cli).showQuestion("A question with only one next question")
    verify(cli).showQuestion("A question with no next question")

  }

  it should "serve questions to cli if condition is met" in {
    val cli = mock[CommandLineInterface]
    val questionEngine = new QuestionEngine(cli)

    val endQuestion = DefaultQuestion("A question with no next question", None)
    val question = ConditionalQuestion("A question with a conditional answer",
      List(Conditional(IntegerCondition((x) => x < 5), endQuestion)))
    when(cli.showQuestion("A question with a conditional answer")).thenReturn("3")
    questionEngine.serve(question)

    verify(cli).showQuestion("A question with a conditional answer")
    verify(cli).showQuestion("A question with no next question")

  }

}
