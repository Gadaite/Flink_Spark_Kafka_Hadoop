package SparkMllib

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics

object Summmary_method extends App{
    override def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("app").setMaster("local[*]")
        val sc = new SparkContext(conf)
        val rdd = sc.textFile("src\\main\\scala\\GadaiteGroupID\\DataSets\\DataSet_sparklearn\\D04\\testSummary.txt") //创建RDD文件路径
            .map(_.split(' ') //按“ ”分割
            .map(_.toDouble)) //转成Double类型
            .map(line => Vectors.dense(line)) //转成Vector格式
        /**
          * 百度翻译结果：
          * Statistics：统计数字
          * Vectors：向量
          * linalg模块包含线性代数的函数。使用这个模块，可以计算逆矩阵、求特征值、解线性方程组以及求解行列式等
          */
        val summary = Statistics.colStats(rdd) //获取Statistics实例
        println(summary.mean) //计算均值
        println(summary.variance) //计算标准差
        println(summary.count) // 计算行内数据个数
        println(summary.max) // 计算最大值
        println(summary.normL1) // 计算曼哈顿距离     标明两个点在标准坐标系上的绝对轴距总和，相当于 1+2+3+4+5
        println(summary.normL2) // 计算欧几里得距离   欧几里得距离，就是勾股定理那个
        println(summary.numNonzeros) // 计算不含0值的个数
    }
}