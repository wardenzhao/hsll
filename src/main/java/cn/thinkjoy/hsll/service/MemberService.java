package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.Member;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberService {


    Member getMemberByOpenId(String openId);

    Member getMemberByInviteCode(String inviteCode);

    Member getMemberById(long buyMemberId);

    void insertData(Member memberNew);

    List<Member> getMemberList(int pageNo, int pageSize);

    int getMemberCount();

    void add(Member member);

    void update(Member member);

    void delete(long id);

    Member getMemberByInviteName(String inviteName);
}
