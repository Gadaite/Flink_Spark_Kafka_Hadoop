package CETC10S.Main20220523;

import JavaBean.JJM;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误写法，这样会导致出现重复的udfschema对象，set会操作所有的成员变量
 */
public class ListAddUdfError {
    public static void main(String[] args) {
        List<JJM> list = new ArrayList<>();
        JJM udfOneSchema = new JJM();
        udfOneSchema.setNo(1);
        for (int i=0;i<=2;i++){
            udfOneSchema.setName("zhangsan");
            udfOneSchema.setAge(i);
            udfOneSchema.setNo(1);
            list.add(udfOneSchema);
        }
        list.add(udfOneSchema);
        for (JJM i : list){
            System.out.println(String.valueOf(i.getAge()) + " " + i.getName() + " " + String.valueOf(i.getNo()));
        }
    }
}
