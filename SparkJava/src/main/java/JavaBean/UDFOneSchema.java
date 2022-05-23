package JavaBean;

import java.io.Serializable;

public class UDFOneSchema implements Serializable {
    private int no;
    private int age;
    private String name;
    public UDFOneSchema(){}
    public UDFOneSchema(int no, int age, String name) {
        this.no = no;
        this.age = age;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UDFOneSchema{" +
                "no=" + no +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
