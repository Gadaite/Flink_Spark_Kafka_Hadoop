package CETC10S.Main20220523;

import JavaBean.JJM;

import java.util.ArrayList;
import java.util.List;

public class ListAddUdfRight {
    public static void main(String[] args) {
        List<JJM> list = new ArrayList<>();
        for (int i=0;i<=2;i++){
            JJM udfOneSchema = new JJM();
            list.add(udfOneSchema);
        }
        for (int i=0;i<list.size();i++){
            list.get(i).setNo(i);
            list.get(i).setAge(i);
            list.get(i).setName("jiangjiming");
        }
        for (JJM u: list){
            System.out.println(u.toString());
        }

    }
}
