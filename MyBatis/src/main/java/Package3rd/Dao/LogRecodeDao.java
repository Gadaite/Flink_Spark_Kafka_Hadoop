package Package3rd.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface LogRecodeDao {
    @Insert("insert into accountLog values(#{log})")
    void wLog(@Param("log") String log);
}
