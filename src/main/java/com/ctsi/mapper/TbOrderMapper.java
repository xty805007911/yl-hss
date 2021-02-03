package com.ctsi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctsi.entity.TbOrder;
import com.ctsi.vo.GroupCountVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName : TbAvatarMapper
 * @Description :
 * @Author : Xiaotianyu  //作者
 * @Date: 2020-12-31 14:34
 */
@Repository
public interface TbOrderMapper extends BaseMapper<TbOrder> {

    //查询接单数目最多的护工
    @Select("SELECT care_id,COUNT(*) AS`count` FROM `tb_order`\n" +
            "GROUP BY care_id \n" +
            "ORDER BY `count` DESC")
    List<GroupCountVO> selectTopOrderCare();
}
