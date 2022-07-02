package ServiceAndDao.Service;

import ServiceAndDao.Dao.UserDao;
import ServiceAndDao.Dao.UserDaoImpl;

public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void welcome(){
        System.out.println("Welcome to service,Gadaite!");
        //  传统方式
        UserDao userdao = new UserDaoImpl();
        userdao.func1();
        //  使用Spring实现
        userDao.func1();
    }
}
