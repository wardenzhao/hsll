package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.MemberApply;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface MemberApplyService {


    void insertData(MemberApply memberApply);

    List<MemberApply> getMemberApplys(int i, int pageNo);

    int getMemberApplysCount();

    MemberApply getMemberApplyDetail(long id);

    void updateApplyStatus(long id, int status);
}
