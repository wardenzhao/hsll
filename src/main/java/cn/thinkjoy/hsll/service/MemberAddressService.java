package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.MemberAddress;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberAddressService {


    List<MemberAddress> getMemberByMemberId(long memberId);

    MemberAddress getMemberById(long id);

    void updateData(MemberAddress memberAddress);

    void insertData(MemberAddress memberAddress);

}
