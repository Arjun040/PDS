//> using scala 2.11.12
//> using jvm 8
//> using dep org.scala-saddle::saddle-core:1.3.4
//> using dep org.vegas-viz::vegas:0.3.11

import org.saddle._
import org.saddle.io._
import vegas._
import java.io.PrintWriter

object SaddleVegas {

  def main(args: Array[String]): Unit = {

    // Step 1: Load the fruit dataset from CSV into a Saddle Frame
    val file = CsvFile("fruit_prices.csv")
    val raw = CsvParser.parse(file).withColIndex(0)
    println("Fruit Prices Dataset (Saddle Frame):")
    raw.print(30, 4)

    // Step 2: Extract the columns as Series
    val dates  = raw.colAt(0).toVec
    val fruits = raw.colAt(1).toVec
    val prices = raw.colAt(2).toVec.map(_.toDouble)
    val qty    = raw.colAt(3).toVec.map(_.toInt)

    // Step 3: Filter the rows for apples
    val appleIdx = fruits.find(_ == "Apple").toSeq
    val appleFrame = raw.rowAt(appleIdx: _*)
    println("Filtered rows for Apple:")
    appleFrame.print(10, 4)

    // Step 4: Average price of apples
    val applePrices = Series(Vec(appleIdx.map(i => prices.raw(i)): _*),
                             Index(appleIdx.map(i => dates.raw(i)): _*))
    println("Apple price series (Date -> Price):")
    println(applePrices)
    val avgApple = applePrices.mean
    println(f"Average price of Apple = Rs. $avgApple%.2f")
    println("Total quantity of Apple = " + appleIdx.map(i => qty.raw(i)).sum)

    // Step 5: Visualize the Apple price trend over time using Vegas
    val points = appleIdx.map(i => Map("Date" -> dates.raw(i), "Price" -> prices.raw(i)))
    val plot = Vegas("Apple Price Trend Over Time", width = 600.0, height = 320.0)
      .withData(points)
      .encodeX("Date", Ordinal)
      .encodeY("Price", Quantitative, scale = Scale(zero = false))
      .mark(Line)

    // Save the interactive Vegas chart as an HTML page
    val out = new PrintWriter("P23_apple_price_trend.html")
    out.write(plot.html.pageHTML())
    out.close()
    println("Vegas chart saved to P23_apple_price_trend.html")
  }
}
