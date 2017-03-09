sealed trait Question
case class ConditionalQuestion(message: String, nextQuestions: List[Conditional]) extends Question
case class DefaultQuestion(message: String, nextQuestion: Option[Question]) extends Question

case class Conditional(condition: IntegerCondition, nextQuestion: Question)
case class IntegerCondition(c: Int => Boolean)