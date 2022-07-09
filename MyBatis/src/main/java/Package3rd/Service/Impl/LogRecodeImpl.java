package Package3rd.Service.Impl;

import Package3rd.Dao.LogRecodeDao;
import Package3rd.Service.LogRecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class LogRecodeImpl implements LogRecode {

    @Autowired
    private LogRecodeDao logRecodeDao;

    @Override
    public void getLog(String name1, String name2, double money) {
        long l = System.currentTimeMillis();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(l);
        String recode = name1 + "---->" + name2 + "(" + money + ") ," + currentTime;
        logRecodeDao.wLog(recode);
    }
}
