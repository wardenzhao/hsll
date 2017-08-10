package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.Member;
import org.apache.ibatis.annotations.Param;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberDao {

    Member getMemberByOpenId(@Param("openId")String openId);

    Member getMemberByInviteCode(@Param("inviteCode")String inviteCode);

    Member getMemberById(@Param("id")long id);

    void insertData(@Param("member")Member member);
}
