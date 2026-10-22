# Programming for Data Science Lab (MACSE502) – Scala

ABHISHEK P · 26MML0019 · VIT Vellore, Fall 2026

## Assessment 9 – Weeks 9 to 12

| Sl. No. | Week | Topic | File |
|--------:|-----:|-------|------|
| 18 | 9  | Arrays in Scala | [P18_ArraysInScala.scala](Assessment-9/P18_ArraysInScala.scala) |
| 19 | 10 | Lists, sets, and tuples in Scala | [P19_ListsSetsTuples.scala](Assessment-9/P19_ListsSetsTuples.scala) |
| 20 | 11 | Handling collisions and resolving conflicts in HashMaps | [P20_HashMapCollisions.scala](Assessment-9/P20_HashMapCollisions.scala) |
| 21 | 12 | Advanced functional combinators | [P21_FunctionalCombinators.scala](Assessment-9/P21_FunctionalCombinators.scala) |

## Assessment 10 – Weeks 13 and 14

| Sl. No. | Week | Topic | File |
|--------:|-----:|-------|------|
| 22 | 13 | Case classes | [P22_CaseClasses.scala](Assessment-10/P22_CaseClasses.scala) |
| 23 | 13 | Saddle + Vegas | [P23_SaddleVegas.scala](Assessment-10/P23_SaddleVegas.scala) |
| 24 | 14 | Breeze + Plotly.Scala | [P24_BreezePlotly.scala](Assessment-10/P24_BreezePlotly.scala) |
| 25 | 14 | Spire + Saddle + Vegas | [P25_SpireSaddleVegas.scala](Assessment-10/P25_SpireSaddleVegas.scala) |

Dataset: [`Assessment-10/fruit_prices.csv`](Assessment-10/fruit_prices.csv) – daily price (Rs. per kg) and
quantity (kg) of Apple, Banana, Mango and Orange from 01-10-2026 to 06-10-2026 (24 rows).

Console output and generated charts are in each folder's `output/` directory.

## Running

Programs 18–22 and 24 need [Scala CLI](https://scala-cli.virtuslab.org/); dependencies are declared in
each file.

```bash
cd Assessment-10
scala-cli run P24_BreezePlotly.scala
```

Vegas is published only for Scala 2.11, which Scala CLI no longer supports, so programs 23 and 25 are
compiled with the Scala 2.11.12 compiler on Java 8 using [Coursier](https://get-coursier.io/):

```bash
cd Assessment-10
JAVA=$(cs java-home --jvm 8)/bin/java
SCALAC=$(cs fetch --classpath org.scala-lang:scala-compiler:2.11.12)
LIBS=$(cs fetch --classpath org.scala-saddle:saddle-core_2.11:1.3.4 org.spire-math:spire_2.11:0.13.0 \
       org.vegas-viz:vegas_2.11:0.3.11 org.scala-lang:scala-library:2.11.12)
mkdir -p classes
"$JAVA" -cp "$SCALAC" scala.tools.nsc.Main -usejavacp -cp "$LIBS" -d classes P23_SaddleVegas.scala
"$JAVA" -cp "classes:$LIBS" SaddleVegas
```

Use `;` instead of `:` as the classpath separator on Windows. Program 25 is run the same way with
`P25_SpireSaddleVegas.scala` and the main object `SpireSaddleVegas`.
