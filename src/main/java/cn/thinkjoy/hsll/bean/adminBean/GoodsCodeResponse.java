package cn.thinkjoy.hsll.bean.adminBean;

import java.util.List;

/**
 * Created by wpliu on 17/8/20.
 */
public class GoodsCodeResponse {
    private int pageNo;
    private int listCount;
    private List<BatchInfo> batchInfos;

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

    public List<BatchInfo> getBatchInfos() {
        return batchInfos;
    }

    public void setBatchInfos(List<BatchInfo> batchInfos) {
        this.batchInfos = batchInfos;
    }
}
