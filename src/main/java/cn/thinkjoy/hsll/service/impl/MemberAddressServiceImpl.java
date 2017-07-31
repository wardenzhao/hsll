package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.MemberAddress;
import cn.thinkjoy.hsll.dao.MemberAddressDao;
import cn.thinkjoy.hsll.service.MemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("memberAddressService")
public class MemberAddressServiceImpl implements MemberAddressService{

    @Autowired
    private MemberAddressDao memberAddressDao;

    @Override
    public MemberAddress getMemberById(long memberId) {
        return memberAddressDao.getMemberById(memberId);
    }

    @Override
    public List<MemberAddress> getMemberByMemberId(long memberId) {
        return memberAddressDao.getMemberByMemberId(memberId);
    }

    @Override
    public void updateData(MemberAddress memberAddress) {
        memberAddressDao.updateData(memberAddress);
    }

    @Override
    public void insertData(MemberAddress memberAddress) {
        memberAddressDao.insertData(memberAddress);
    }
}
