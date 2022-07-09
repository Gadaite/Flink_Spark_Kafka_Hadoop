package Package3rd.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface LogRecode {
    //  日志信息开启事务，但是这样的事务和其他操作同处于一个事务
    //  try---finally 依旧不会写入
    //  开启一个新事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void getLog(String name1,String name2,double money);
}
