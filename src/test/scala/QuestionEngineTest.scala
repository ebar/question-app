import org.scalatest._


class QuestionEngineTest extends FlatSpec with Matchers {

  it should "serve next Question" in {

    val endQuestion = DefaultQuestion("A question with no next question", None)
    val question = DefaultQuestion("A question with only one next question", Some(endQuestion))
    val next = QuestionEngine.serve(question)
    next should be (endQuestion)
  }

}
