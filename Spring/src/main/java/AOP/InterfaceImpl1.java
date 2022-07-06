package AOP;

public class InterfaceImpl1  implements Interface1{
    public Integer add(Integer num1, Integer num2) {
        return num1+num2;
    }
    public String update(String id) {
        return id;
    }
}
