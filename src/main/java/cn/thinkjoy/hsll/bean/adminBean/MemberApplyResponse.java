package cn.thinkjoy.hsll.bean.adminBean;

import cn.thinkjoy.hsll.bean.MemberApply;

import java.util.List;

/**
 * Created by wpliu on 17/8/20.
 */
public class MemberApplyResponse {
    private int pageNo;
    private int listCount;
    private List<MemberApply> applyList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public List<MemberApply> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<MemberApply> applyList) {
        this.applyList = applyList;
    }
}
