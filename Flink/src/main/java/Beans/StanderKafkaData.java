package Beans;


public class StanderKafkaData {
    private String kafka_data;

    public StanderKafkaData() {
    }

    public StanderKafkaData(String kafka_data) {
        this.kafka_data = kafka_data;
    }
    public String getKafka_data() {
        return kafka_data;
    }

    public void setKafka_data(String kafka_data) {
        this.kafka_data = kafka_data;
    }
    public static int[] toArray(String kafka_data){
        String[] kafka_data_arr = kafka_data.split(" ");
        int[] tmp = new int[kafka_data_arr.length];
        for(int i=0;i<kafka_data.length();i++){
            tmp[i] = Integer.parseInt(kafka_data_arr[i]);
        }
        return tmp;
    }
    public static int Min(int[] S){
        int min = S[0];
        for(int i : S){
            if(i<min){
                min = i;
            }
        }
        return min;
    }
    public static int Max(int[] S){
        int max = S[0];
        for(int i : S){
            if(i>max){
                max = i;
            }
        }
        return max;
    }
}

