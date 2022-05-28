package CETC10S.Main20220523;

import NoteJavaBean.UDFOneSchema;

import java.util.ArrayList;
import java.util.List;

public class ListAddUdfRight {
    public static void main(String[] args) {
        List<UDFOneSchema> list = new ArrayList<>();
        for (int i=0;i<=2;i++){
            UDFOneSchema udfOneSchema = new UDFOneSchema();
            list.add(udfOneSchema);
        }
        for (int i=0;i<list.size();i++){
            list.get(i).setNo(i);
            list.get(i).setAge(i);
            list.get(i).setName("jiangjiming");
        }
        for (UDFOneSchema u: list){
            System.out.println(u.toString());
        }

    }
}
