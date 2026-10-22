//> using scala 2.13.16
//> using dep org.scalanlp::breeze:2.1.0
//> using dep org.plotly-scala::plotly-render:0.8.5

import breeze.linalg.{DenseVector, sum}
import plotly._
import plotly.element._
import plotly.layout._
import plotly.Plotly._
import scala.io.Source

object BreezePlotly {

  def main(args: Array[String]): Unit = {

    // Step 1: Load the fruit dataset from CSV
    val src = Source.fromFile("fruit_prices.csv")
    val rows = src.getLines().drop(1).map(_.split(",")).toList
    src.close()
    println("Fruit Prices Dataset:")
    println(f"${"Date"}%-12s${"Fruit"}%-8s${"Price"}%8s${"Quantity"}%10s")
    rows.foreach(r => println(f"${r(0)}%-12s${r(1)}%-8s${r(2).toDouble}%8.2f${r(3).toInt}%10d"))

    // Step 2: Build Breeze vectors for price and quantity
    val fruitNames = rows.map(_(1))
    val price    = DenseVector(rows.map(_(2).toDouble): _*)
    val quantity = DenseVector(rows.map(_(3).toDouble): _*)
    println("\nPrice vector    : " + price)
    println("Quantity vector : " + quantity)

    // Step 3: Revenue = price * quantity (element-wise with Breeze)
    val revenue: DenseVector[Double] = price *:* quantity
    println("Revenue vector  : " + revenue)

    // Step 4: Total revenue for each fruit type
    val fruitTypes = fruitNames.distinct.sorted
    val totals = fruitTypes.map { f =>
      val idx = fruitNames.zipWithIndex.collect { case (n, i) if n == f => i }
      f -> sum(revenue(idx).toDenseVector)
    }
    println("\nTotal revenue for each fruit:")
    totals.foreach { case (f, t) => println(f"  $f%-8s : Rs. $t%,.2f") }
    println(f"Overall revenue  : Rs. ${sum(revenue)}%,.2f")

    // Step 5: Bar chart of revenue per fruit using Plotly.Scala
    val bar = Bar(totals.map(_._1), totals.map(_._2))
      .withName("Revenue")
      .withText(totals.map { case (_, t) => f"$t%,.0f" })
      .withTextposition(BarTextPosition.Outside)
    val layout = Layout()
      .withTitle("Total Revenue by Fruit Type")
      .withXaxis(Axis().withTitle("Fruit"))
      .withYaxis(Axis().withTitle("Revenue (Rs.)").withRange((0.0, 40000.0)))
      .withWidth(700)
      .withHeight(420)
    new java.io.File("P24_revenue_bar_chart.html").delete()
    plot("P24_revenue_bar_chart.html", Seq(bar), layout,
         useCdn = true, openInBrowser = false, addSuffixIfExists = false)
    println("\nPlotly bar chart saved to P24_revenue_bar_chart.html")
  }
}
