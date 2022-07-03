package JavaBean;

public class Life {
    private String string;

    public void setString(String string) {
        System.out.println("进入set方法注入值");
        this.string = string;
    }
    //  无参构造方法
    public Life(){
        System.out.println("进入无参构造函数");
    }

    //  初始化方法
    public void init(){
        System.out.println("进入初始化函数");
    }
    // 销毁方法
    public void drop(){
        System.out.println("进入销毁函数");
    }
}
