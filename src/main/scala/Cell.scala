import scala.util.Random

class Cell(state: Boolean) {
  private[this] var alive = state

  def toDeath(): Unit = {alive = false}

  def toLife(): Unit = {alive = true}

  def isAlive: Boolean = alive

  override def toString: String = if (isAlive) "O" else "*"
}
