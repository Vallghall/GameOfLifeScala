import scala.util.{Failure, Try}

object Game extends App {
  var rows, columns = 0
  // Parse args into rows and columns' quantity
  Try {
    rows = args(0).toInt
    columns = args(1).toInt
  } match {
    case Failure(exception) => {
      println(f"\nArguments entered are invalid!\n\t$exception\nSTARTING WITH DEFAULT GRID SIZE")
      rows = 10
      columns = 100
    }
    case _ =>
  }
  println(f"\t\tConway's Game of Life\n(rows:$rows columns:$columns)")
  // Building grid object
  val grid = new Grid(rows, columns)
  // Printing the initial grid state
  grid.show
  // Continuous evolution
  while (true) {
    grid.updateGrid()
    grid.show
    Thread.sleep(200)
  }
}
