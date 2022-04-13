package SparkMllib

import org.apache.spark.mllib.linalg.Vectors

object Vector_Method1 extends App {
  val vd = Vectors.dense(2,0,6)//密集向量
  println(vd(2))//打印第3个值
  /**
   * 6.0
   */
  //建立稀疏向量
  val vs = Vectors.sparse(4,Array(0,1,2,3),Array(9,5,2,7))
  //打印稀疏向量的第三个值
  println(vs(2))
  /**
   * 2.0
   */

}
