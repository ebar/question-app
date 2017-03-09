abstract class FlowComponent(val id : Int)

case class Block(override val id: Int, question: String, answerType: AnswerType.Value, nextId: Option[Int])
  extends FlowComponent(id)

case class Decision(override val id: Int, conditions: List[Conditional])
  extends FlowComponent(id)

case class Conditional(condition: IntegerCondition, nextId: Int)

case class IntegerCondition(c: Int => Boolean)

