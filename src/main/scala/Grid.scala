import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Grid(val rows: Int, val columns: Int) {

  val grid: Array[Array[Cell]] =
    Array.tabulate(rows){ _ =>
      Array.tabulate(columns){_ =>
        if (Random.nextInt(2) == 1)
          new Cell(true)
        else
          new Cell(false)
      }}

  def show = {
    println("\n" * rows)
    grid.foreach({ ac => ac.foreach(print); println})
    println()
  }

  def updateGrid(): Unit = {
    val toDie = ArrayBuffer[(Int,Int)]()
    val toLive = ArrayBuffer[(Int,Int)]()

    for {
      row <- 0 until rows
      column <- 0 until columns
    } yield {
      val neighbours = findNeighbours(row, column)
      if (neighbours == 3)
        toLive += row -> column
      else if (neighbours < 2 || neighbours > 3)
        toDie += row -> column
    }

    toLive.foreach(x => grid(x._1)(x._2).toLife())
    toDie.foreach(x => grid(x._1)(x._2).toDeath())
  }

  private def findNeighbours(row: Int, column: Int) = {
    Array(
      (row, column - 1),
      (row - 1, column - 1),
      (row - 1, column),
      (row - 1, column + 1),
      (row, column + 1),
      (row + 1, column - 1),
      (row + 1, column),
      (row + 1, column + 1),
    ).filter(x => x._1 >= 0 && x._2 >= 0 && x._1 < rows && x._2 < columns)
      .count(x => grid(x._1)(x._2).isAlive)
  }
}
