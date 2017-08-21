package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Member;
import cn.thinkjoy.hsll.dao.MemberDao;
import cn.thinkjoy.hsll.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member getMemberByOpenId(String openId) {
        return memberDao.getMemberByOpenId(openId);
    }

    @Override
    public Member getMemberByInviteCode(String inviteCode) {
        return memberDao.getMemberByInviteCode(inviteCode);
    }

    @Override
    public Member getMemberById(long id) {
        return memberDao.getMemberById(id);
    }

    @Override
    public void insertData(Member member) {
        memberDao.insertData(member);
    }

    @Override
    public List<Member> getMemberList(int pageNo, int pageSize) {
        return memberDao.getMemberList(pageNo,pageSize);
    }

    @Override
    public int getMemberCount() {
        return memberDao.getMemberCount();
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    @Override
    public void update(Member member) {
        memberDao.update(member);
    }

    @Override
    public void delete(long id) {
        memberDao.delete(id);
    }
}
