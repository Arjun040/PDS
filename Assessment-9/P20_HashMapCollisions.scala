import scala.collection.mutable

object HashMapCollisions {

  // Merge two HashMaps; when a key exists in both, keep the higher grade
  def mergeHigherGrade(m1: mutable.HashMap[String, Int],
                       m2: mutable.HashMap[String, Int]): mutable.HashMap[String, Int] = {
    val merged = mutable.HashMap[String, Int]() ++= m1
    for ((name, grade) <- m2) {
      merged.get(name) match {
        case Some(old) => merged(name) = math.max(old, grade)
        case None      => merged(name) = grade
      }
    }
    merged
  }

  // Collision handling by chaining: each key stores a List of values
  def addWithChaining(table: mutable.HashMap[String, List[Int]],
                      name: String, grade: Int): Unit = {
    table(name) = table.getOrElse(name, List()) :+ grade
  }

  // Print a map sorted by key
  def show(title: String, m: collection.Map[String, _]): Unit = {
    println(title)
    m.toSeq.sortBy(_._1).foreach { case (k, v) => println(s"  $k -> $v") }
  }

  def main(args: Array[String]): Unit = {

    // Step 1: HashMap of student names and grades
    val grades = mutable.HashMap[String, Int]()
    grades += ("Arun" -> 78)
    grades += ("Divya" -> 85)
    grades += ("Karthik" -> 69)
    grades += ("Meena" -> 92)
    show("Initial HashMap:", grades)

    // Step 2: Insert a duplicate key with a different grade
    println("\nInserting duplicate key: Arun -> 88")
    grades += ("Arun" -> 88)
    show("HashMap after duplicate insert (old value overwritten):", grades)

    // Step 3: Second HashMap and merge resolving conflicts by higher grade
    val grades2 = mutable.HashMap("Divya" -> 80, "Karthik" -> 75, "Rahul" -> 81)
    show("\nSecond HashMap:", grades2)
    val merged = mergeHigherGrade(grades, grades2)
    show("\nMerged HashMap (higher grade kept):", merged)

    // Step 4: Handle collisions by chaining using lists
    val chained = mutable.HashMap[String, List[Int]]()
    val entries = List(("Arun", 78), ("Divya", 85), ("Arun", 88),
                       ("Karthik", 69), ("Divya", 80), ("Karthik", 75))
    for ((n, g) <- entries) addWithChaining(chained, n, g)
    show("\nHashMap with chaining (all grades kept per key):", chained)
  }
}
