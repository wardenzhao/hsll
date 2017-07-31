package cn.thinkjoy.hsll.resp;

import java.util.List;

/**
 * Created by warden on 17/7/31.
 */
public class ListDataResp extends BaseResp{

    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
