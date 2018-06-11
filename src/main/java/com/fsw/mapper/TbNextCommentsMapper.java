package com.fsw.mapper;

import com.fsw.pojo.TbNextComments;
import com.fsw.pojo.TbNextCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbNextCommentsMapper {
    int countByExample(TbNextCommentsExample example);

    int deleteByExample(TbNextCommentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbNextComments record);

    int insertSelective(TbNextComments record);

    List<TbNextComments> selectByExampleWithBLOBs(TbNextCommentsExample example);

    List<TbNextComments> selectByExample(TbNextCommentsExample example);

    TbNextComments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbNextComments record, @Param("example") TbNextCommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") TbNextComments record, @Param("example") TbNextCommentsExample example);

    int updateByExample(@Param("record") TbNextComments record, @Param("example") TbNextCommentsExample example);

    int updateByPrimaryKeySelective(TbNextComments record);

    int updateByPrimaryKeyWithBLOBs(TbNextComments record);

    int updateByPrimaryKey(TbNextComments record);
}