package AnnotationOnly.Dao;

import org.springframework.stereotype.Repository;

@Repository(value = "userDaoImpl1")
public class UserDaoImpl implements UserDao {
    public void dao() {
        System.out.println("dao add ......");
    }
}
