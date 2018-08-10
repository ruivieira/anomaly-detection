package org.ruivieira.loganalysis

import io.radanalytics.silex.som.SOM
import org.apache.spark.ml.linalg
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.rdd.RDD

object AnomalyDetection {

  def loadData(): RDD[linalg.Vector] = {
    sc.textFile("data/data.csv")
      .map(line => line.split(","))
      .map(values => Vectors.dense(values.drop(1).dropRight(1).map(_.toDouble)))
  }


  val sc = Main.sc
  sc.setLogLevel("error")

  def main(args: Array[String]): Unit = {
    val data = loadData()
    val model = SOM.train(xdim = 4, ydim = 4, examples = data, iterations = 20, fdim = 9)

    data.collect().foreach { v =>
      val s = model.closestWithSimilarity(v, None)
      if (s._2 < 0.8) {
        println(s"$v => $s")
      }

    }

  }



}

