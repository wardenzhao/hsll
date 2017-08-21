package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.MemberApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberApplyDao {

    int insertData(@Param("memberApply") MemberApply memberApply);

    List<MemberApply> getMemberApplys(@Param("startPage")int i, @Param("pageSize")int pageNo);

    int getMemberApplysCount();

    MemberApply getMemberApplyDetail(@Param("id")long id);

    void updateApplyStatus(@Param("id")long id,@Param("status")int status);
}
