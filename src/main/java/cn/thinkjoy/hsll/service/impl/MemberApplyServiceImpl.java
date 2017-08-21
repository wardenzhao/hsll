package cn.thinkjoy.hsll.service.impl;


import cn.thinkjoy.hsll.bean.MemberApply;
import cn.thinkjoy.hsll.dao.MemberApplyDao;
import cn.thinkjoy.hsll.service.MemberApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("memberApplyService")
public class MemberApplyServiceImpl implements MemberApplyService{


    @Autowired
    private MemberApplyDao memberApplyDao;

    @Override
    public void insertData(MemberApply memberApply) {
        memberApplyDao.insertData(memberApply);
    }

    @Override
    public List<MemberApply> getMemberApplys(int i, int pageNo) {
        return memberApplyDao.getMemberApplys(i,pageNo);
    }

    @Override
    public int getMemberApplysCount() {
        return memberApplyDao.getMemberApplysCount();
    }

    @Override
    public MemberApply getMemberApplyDetail(long id) {
        return memberApplyDao.getMemberApplyDetail(id);
    }

    @Override
    public void updateApplyStatus(long id, int status) {
         memberApplyDao.updateApplyStatus(id,status);
    }
}
