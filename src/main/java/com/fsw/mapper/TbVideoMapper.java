package com.fsw.mapper;

import com.fsw.pojo.TbVideo;
import com.fsw.pojo.TbVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbVideoMapper {
    int countByExample(TbVideoExample example);

    int deleteByExample(TbVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbVideo record);

    int insertSelective(TbVideo record);

    List<TbVideo> selectByExampleWithBLOBs(TbVideoExample example);

    List<TbVideo> selectByExample(TbVideoExample example);

    TbVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbVideo record, @Param("example") TbVideoExample example);

    int updateByExampleWithBLOBs(@Param("record") TbVideo record, @Param("example") TbVideoExample example);

    int updateByExample(@Param("record") TbVideo record, @Param("example") TbVideoExample example);

    int updateByPrimaryKeySelective(TbVideo record);

    int updateByPrimaryKeyWithBLOBs(TbVideo record);

    int updateByPrimaryKey(TbVideo record);
}