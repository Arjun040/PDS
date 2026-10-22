// Case class representing a person
case class Person(name: String, age: Int, city: String)

object CaseClasses {

  // Filter out people older than 30 (keep age <= 30)
  def filterOlderThan30(people: List[Person]): List[Person] =
    people.filterNot(_.age > 30)

  // Group people by their city
  def groupByCity(people: List[Person]): Map[String, List[Person]] =
    people.groupBy(_.city)

  // Transform each person into a descriptive string
  def describe(people: List[Person]): List[String] =
    people.map(p => s"${p.name} is ${p.age} years old and lives in ${p.city}")

  // Sort people by age in ascending order
  def sortByAge(people: List[Person]): List[Person] =
    people.sortBy(_.age)

  def main(args: Array[String]): Unit = {

    // Step 1: List of five Person objects
    val people = List(
      Person("Arun", 28, "Chennai"),
      Person("Divya", 34, "Vellore"),
      Person("Karthik", 22, "Chennai"),
      Person("Meena", 41, "Bengaluru"),
      Person("Rahul", 30, "Vellore")
    )
    println("All people:")
    people.foreach(p => println("  " + p))

    // Step 2: Filter out people older than 30
    println("\nAfter filtering out people older than 30:")
    filterOlderThan30(people).foreach(p => println("  " + p))

    // Step 3: Group by city
    println("\nPeople grouped by city:")
    groupByCity(people).toSeq.sortBy(_._1).foreach { case (city, ps) =>
      println(s"  $city -> ${ps.map(_.name).mkString(", ")}")
    }

    // Step 4: Transform into strings
    println("\nDescriptions:")
    describe(people).foreach(s => println("  " + s))

    // Step 5: Sort by age ascending
    println("\nSorted by age (ascending):")
    sortByAge(people).foreach(p => println("  " + p))
  }
}
