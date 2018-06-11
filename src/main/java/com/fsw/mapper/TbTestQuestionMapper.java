package com.fsw.mapper;

import com.fsw.pojo.TbTestQuestion;
import com.fsw.pojo.TbTestQuestionExample;
import com.fsw.pojo.TbTestQuestionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTestQuestionMapper {
    int countByExample(TbTestQuestionExample example);

    int deleteByExample(TbTestQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTestQuestionWithBLOBs record);

    int insertSelective(TbTestQuestionWithBLOBs record);

    List<TbTestQuestionWithBLOBs> selectByExampleWithBLOBs(TbTestQuestionExample example);

    List<TbTestQuestion> selectByExample(TbTestQuestionExample example);

    TbTestQuestionWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTestQuestionWithBLOBs record, @Param("example") TbTestQuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") TbTestQuestionWithBLOBs record, @Param("example") TbTestQuestionExample example);

    int updateByExample(@Param("record") TbTestQuestion record, @Param("example") TbTestQuestionExample example);

    int updateByPrimaryKeySelective(TbTestQuestionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbTestQuestionWithBLOBs record);

    int updateByPrimaryKey(TbTestQuestion record);
}