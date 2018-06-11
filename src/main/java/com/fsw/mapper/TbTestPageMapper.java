package com.fsw.mapper;

import com.fsw.pojo.TbTestPage;
import com.fsw.pojo.TbTestPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTestPageMapper {
    int countByExample(TbTestPageExample example);

    int deleteByExample(TbTestPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTestPage record);

    int insertSelective(TbTestPage record);

    List<TbTestPage> selectByExample(TbTestPageExample example);

    TbTestPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTestPage record, @Param("example") TbTestPageExample example);

    int updateByExample(@Param("record") TbTestPage record, @Param("example") TbTestPageExample example);

    int updateByPrimaryKeySelective(TbTestPage record);

    int updateByPrimaryKey(TbTestPage record);
}