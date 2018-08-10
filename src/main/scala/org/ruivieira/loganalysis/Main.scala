package org.ruivieira.loganalysis

import org.apache.spark.{SparkConf, SparkContext}

object Main {

  private val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("SparkApp")

  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    println("Hello World!")
  }

}
