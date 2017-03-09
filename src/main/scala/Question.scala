sealed trait Question

case class ConditionalQuestion(message: String, nextQuestions: List[Condition]) extends Question
case class DefaultQuestion(message: String, nextQuestion: Option[Question]) extends Question


case class Condition(condition: String => Boolean, nextQuestion: Question)
