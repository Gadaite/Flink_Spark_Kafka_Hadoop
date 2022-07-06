package Annotation.Dao;

import org.springframework.stereotype.Repository;

@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao{
    public void dao() {
        System.out.println("dao add ......");
    }
}
