//> using scala 2.11.12
//> using jvm 8
//> using dep org.scala-saddle::saddle-core:1.3.4
//> using dep org.spire-math::spire:0.13.0
//> using dep org.vegas-viz::vegas:0.3.11

import org.saddle._
import org.saddle.io._
import spire.math.Rational
import vegas._
import java.io.PrintWriter

object SpireSaddleVegas {

  // Exact mean using Spire Rational arithmetic
  def mean(xs: Seq[Rational]): Rational = xs.foldLeft(Rational.zero)(_ + _) / Rational(xs.size)

  // Exact population variance using Spire Rational arithmetic
  def variance(xs: Seq[Rational]): Rational = {
    val m = mean(xs)
    xs.map(x => (x - m) * (x - m)).foldLeft(Rational.zero)(_ + _) / Rational(xs.size)
  }

  def main(args: Array[String]): Unit = {

    // Step 1: Load the fruit dataset into a Saddle Frame
    val raw = CsvParser.parse(CsvFile("fruit_prices.csv")).withColIndex(0)
    println("Fruit Prices Dataset (Saddle Frame):")
    raw.print(30, 4)

    // Step 2: Build a Saddle price Frame (rows = Date, columns = Fruit)
    val dates  = raw.colAt(0).toVec.toSeq
    val fruits = raw.colAt(1).toVec.toSeq
    val prices = raw.colAt(2).toVec.toSeq
    val fruitTypes = fruits.distinct.sorted
    val dateIdx = Index(dates.distinct: _*)
    val cols = fruitTypes.map { f =>
      val ps = dates.indices.filter(i => fruits(i) == f).map(i => prices(i).toDouble)
      f -> Series(Vec(ps: _*), dateIdx)
    }
    val priceFrame = Frame(cols: _*)
    println("Price table (Date x Fruit):")
    priceFrame.print(10, 6)

    // Step 3: Mean and variance per fruit using Spire Rational (exact)
    println("Statistics per fruit (computed exactly with Spire Rational):")
    println(f"${"Fruit"}%-8s${"Mean"}%12s${"Variance"}%14s   Exact variance")
    fruitTypes.foreach { f =>
      val xs = dates.indices.filter(i => fruits(i) == f).map(i => Rational(BigDecimal(prices(i))))
      val m = mean(xs)
      val v = variance(xs)
      println(f"$f%-8s${m.toDouble}%12.4f${v.toDouble}%14.4f   $v")
    }

    // Step 4: Visualize price trends over time for every fruit using Vegas
    val points = dates.indices.map(i =>
      Map("Date" -> dates(i), "Fruit" -> fruits(i), "Price" -> prices(i).toDouble))
    val plot = Vegas("Fruit Price Trends Over Time", width = 600.0, height = 320.0)
      .withData(points)
      .encodeX("Date", Ordinal)
      .encodeY("Price", Quantitative)
      .encodeColor("Fruit", Nominal)
      .mark(Line)

    val out = new PrintWriter("P25_fruit_price_trends.html")
    out.write(plot.html.pageHTML())
    out.close()
    println("Vegas chart saved to P25_fruit_price_trends.html")
  }
}
