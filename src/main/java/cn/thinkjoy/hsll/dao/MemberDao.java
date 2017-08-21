package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberDao {

    Member getMemberByOpenId(@Param("openId")String openId);

    Member getMemberByInviteCode(@Param("inviteCode")String inviteCode);

    Member getMemberById(@Param("id")long id);

    void insertData(@Param("member")Member member);


    List<Member> getMemberList(@Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

    int getMemberCount();

    void add(@Param("member")Member member);

    void update(@Param("member")Member member);

    void delete(@Param("id")long id);
}
