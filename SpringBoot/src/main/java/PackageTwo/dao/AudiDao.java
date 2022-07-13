package PackageTwo.dao;

import PackageTwo.domain.Audi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//   Mybatis 会扫描接口并创建接口的代码对象交给 Spring 管理，但是现在并没有告诉 Mybatis 哪个是 dao 接口
@Mapper
public interface AudiDao {
    @Select("select * from audi where year = #{year}")
    public Audi getByYear(Integer year);
}
