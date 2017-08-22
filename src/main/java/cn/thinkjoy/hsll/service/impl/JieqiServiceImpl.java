package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.Jieqi;
import cn.thinkjoy.hsll.dao.JieqiDao;
import cn.thinkjoy.hsll.service.JieqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("jieqiService")
public class JieqiServiceImpl implements JieqiService{

    @Autowired
    private JieqiDao jieqiDao;


    @Override
    public List<Jieqi> getJieqi() {
        return jieqiDao.getJieqi();
    }
}
