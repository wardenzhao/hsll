package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.MemberAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberAddressDao {

    List<MemberAddress> getMemberByMemberId(@Param("memberId") long memberId);

    MemberAddress getMemberById(@Param("id") long id);

    int updateData(@Param("memberAddress")MemberAddress memberAddress);

    int insertData(@Param("memberAddress")MemberAddress memberAddress);

}
