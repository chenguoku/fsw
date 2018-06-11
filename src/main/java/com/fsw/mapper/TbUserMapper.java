package com.fsw.mapper;

import com.fsw.pojo.TbUser;
import com.fsw.pojo.TbUserExample;
import com.fsw.pojo.TbUserWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
    int countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbUserWithBLOBs record);

    int insertSelective(TbUserWithBLOBs record);

    List<TbUserWithBLOBs> selectByExampleWithBLOBs(TbUserExample example);

    List<TbUser> selectByExample(TbUserExample example);

    TbUserWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserWithBLOBs record, @Param("example") TbUserExample example);

    int updateByExampleWithBLOBs(@Param("record") TbUserWithBLOBs record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByPrimaryKeySelective(TbUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbUserWithBLOBs record);

    int updateByPrimaryKey(TbUser record);
}