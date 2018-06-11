package com.fsw.mapper;

import com.fsw.pojo.TbComments;
import com.fsw.pojo.TbCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCommentsMapper {
    int countByExample(TbCommentsExample example);

    int deleteByExample(TbCommentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbComments record);

    int insertSelective(TbComments record);

    List<TbComments> selectByExampleWithBLOBs(TbCommentsExample example);

    List<TbComments> selectByExample(TbCommentsExample example);

    TbComments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbComments record, @Param("example") TbCommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") TbComments record, @Param("example") TbCommentsExample example);

    int updateByExample(@Param("record") TbComments record, @Param("example") TbCommentsExample example);

    int updateByPrimaryKeySelective(TbComments record);

    int updateByPrimaryKeyWithBLOBs(TbComments record);

    int updateByPrimaryKey(TbComments record);
}