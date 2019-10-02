package dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Administrator;

@Repository
public interface AdministratorMapper {
    public Administrator getAdministrator(@Param("userName") String userName);
}
