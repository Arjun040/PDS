object FunctionalCombinators {

  def main(args: Array[String]): Unit = {

    // Step 1: List of integers from 1 to 10
    val numbers: List[Int] = (1 to 10).toList
    println("Original list         : " + numbers)

    // Step 2: filter - keep only even numbers
    val evens = numbers.filter(n => n % 2 == 0)
    println("filter (even numbers) : " + evens)

    // Step 3: map - multiply each element by 3
    val tripled = numbers.map(n => n * 3)
    println("map (x * 3)           : " + tripled)

    // Step 4: flatMap - pair each integer with its square
    val pairs: List[(Int, Int)] = numbers.flatMap(n => List((n, n * n)))
    println("flatMap (n, n^2)      : " + pairs)

    // Step 5: foldLeft - product of all elements
    val product: Long = numbers.foldLeft(1L)((acc, n) => acc * n)
    println("foldLeft (product)    : " + product)
  }
}
