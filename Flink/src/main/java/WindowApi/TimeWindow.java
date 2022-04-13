package WindowApi;

import Beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.*;
import org.apache.flink.streaming.api.windowing.time.Time;
/*
* Tumbling:滚动窗口分配器
* Sliding:滑动窗口分配器
* Session:会话窗口分配器
* Global:全局窗口分配器（移除器和触发器）
* */
public class TimeWindow {
    public static void main(String[] args) throws Exception{
        /*

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> dataStreamSource = env.readTextFile("F:\\CodeG50\\BiGData\\Flink\\src\\main\\resources\\sensor.txt");
        SingleOutputStreamOperator<SensorReading> map = dataStreamSource.map(new MapFunction<String, SensorReading>() {
            @Override
            public SensorReading map(String value) throws Exception {
                String[] s = value.split(",");
                return new SensorReading(s[0], new Long(s[1]), new Double(s[2]));
            }
        });
        //  1.直接基于datastream 可以使用一个开窗操作 dataStreamSource.windowAll()相当于一个global操作
        //  所有的数据都会被传递到下游的一个分区中，性能很低
        dataStreamSource.keyBy("id").window(TumblingProcessingTimeWindows.of(Time.seconds(15)));

        dataStreamSource.keyBy("id").window(TumblingProcessingTimeWindows.of(Time.seconds(15)));
        dataStreamSource.keyBy("id").window(TumblingEventTimeWindows.of(Time.seconds(15)));
        //  上下两种写法等价
        dataStreamSource.keyBy("id").timeWindow(Time.seconds(15));
        //    依据当前的时间特性判断是调用的上面的那个源码如下：
        //    给两个时间参数就是一个滑动时间窗口
//        public WindowedStream<T, KEY, org.apache.flink.streaming.api.windowing.windows.TimeWindow> timeWindow(Time size) {
//            if (environment.getStreamTimeCharacteristic() == TimeCharacteristic.ProcessingTime) {
//                return window(TumblingProcessingTimeWindows.of(size));
//            } else {
//                return window(TumblingEventTimeWindows.of(size));
//            }
//        }
        //  2.滑动时间窗口
        dataStreamSource.keyBy("id").window(SlidingProcessingTimeWindows.of(Time.seconds(15),Time.seconds(2)));
        dataStreamSource.keyBy("id").window(SlidingEventTimeWindows.of(Time.seconds(15),Time.seconds(2)));
        //  3.sessionwindow只能调用底层
        dataStreamSource.keyBy("id").window(EventTimeSessionWindows.withGap(Time.seconds(60)));

        //  4.计数窗口
        dataStreamSource.keyBy("id").countWindow(10,2);//   滑动计数窗口
        dataStreamSource.keyBy("id").countWindow(10);//     滚动计数窗口
        env.execute();

         */

    }
}
