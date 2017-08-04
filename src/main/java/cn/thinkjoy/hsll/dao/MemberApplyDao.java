package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.MemberApply;
import org.apache.ibatis.annotations.Param;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberApplyDao {

    int insertData(@Param("memberApply") MemberApply memberApply);

}
