package cn.thinkjoy.hsll.service.impl;


import cn.thinkjoy.hsll.bean.MemberApply;
import cn.thinkjoy.hsll.dao.MemberApplyDao;
import cn.thinkjoy.hsll.service.MemberApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
