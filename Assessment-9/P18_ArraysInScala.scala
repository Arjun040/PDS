object ArraysInScala {

  // Function to return the sum of all elements of an integer array
  def sumOfElements(arr: Array[Int]): Int = {
    var total = 0
    for (x <- arr) total += x
    total
  }

  // Function to concatenate all strings of an array separated by commas
  def concatenateFruits(arr: Array[String]): String = {
    arr.mkString(", ")
  }

  def main(args: Array[String]): Unit = {

    // Step 1: Create an array of integers
    val numbers: Array[Int] = Array(2, 5, 9, 14, 20)
    println("Integer Array : " + numbers.mkString("[", ", ", "]"))

    // Step 2: Sum of the elements using the function
    val sum = sumOfElements(numbers)
    println("Sum of the elements : " + sum)

    // Step 3: Create an array of strings with five fruit names
    val fruits: Array[String] = Array("Apple", "Banana", "Mango", "Orange", "Grapes")
    println("\nFruit Array : " + fruits.mkString("[", ", ", "]"))

    // Step 4: Concatenate the fruit names separated by commas
    val fruitString = concatenateFruits(fruits)
    println("Concatenated Fruits : " + fruitString)

    // Step 5: Iterate over the integer array and print each element
    println("\nElements of the Integer Array:")
    for (i <- numbers.indices) {
      println(s"Element at index $i = ${numbers(i)}")
    }
  }
}
