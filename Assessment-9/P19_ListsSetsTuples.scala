object ListsSetsTuples {

  // Function that returns a new list with each element squared
  def squareList(lst: List[Int]): List[Int] = {
    lst.map(x => x * x)
  }

  // Function that returns the intersection of two sets
  def intersectSets(s1: Set[Char], s2: Set[Char]): Set[Char] = {
    s1.intersect(s2)
  }

  def main(args: Array[String]): Unit = {

    // Step 1: List of the first five prime numbers
    val primes: List[Int] = List(2, 3, 5, 7, 11)
    println("List of first five primes : " + primes)

    // Step 2: Square each element of the list
    val squared = squareList(primes)
    println("Squared list              : " + squared)

    // Step 3: Set of unique characters from "hello world"
    val text = "hello world"
    val charSet: Set[Char] = text.replace(" ", "").toSet
    println("\nUnique characters of \"hello world\" : " + charSet.toList.sorted.mkString("{", ", ", "}"))

    // Step 4: Intersection of two sets
    val otherSet: Set[Char] = "scala world".replace(" ", "").toSet
    println("Second set from \"scala world\"      : " + otherSet.toList.sorted.mkString("{", ", ", "}"))
    val common = intersectSets(charSet, otherSet)
    println("Intersection of the two sets       : " + common.toList.sorted.mkString("{", ", ", "}"))

    // Step 5: Tuple with an integer, a string and a boolean
    val record: (Int, String, Boolean) = (26, "Scala", true)
    println("\nTuple : " + record)
    println("First element  (Int)     : " + record._1)
    println("Second element (String)  : " + record._2)
    println("Third element  (Boolean) : " + record._3)
  }
}
