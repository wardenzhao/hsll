package cn.thinkjoy.hsll.bean.adminBean;

import java.util.List;

/**
 * Created by wpliu on 17/8/20.
 */
public class BatchResponse {
    private int pageNo;
    private int listCount;
    private List<BatchInfo> batchList;

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

    public List<BatchInfo> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<BatchInfo> batchList) {
        this.batchList = batchList;
    }
}
