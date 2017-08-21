package cn.thinkjoy.hsll.bean.adminBean;

/**
 * Created by wpliu on 17/8/14.
 * 商品规格
 */
public class ProductSpec {
    private long goodId;
    private long specId;
    private String specName;
    private String specPrice;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    private String specStatus;

    public long getSpecId() {
        return specId;
    }

    public void setSpecId(long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(String specPrice) {
        this.specPrice = specPrice;
    }

    public String getSpecStatus() {
        return specStatus;
    }

    public void setSpecStatus(String specStatus) {
        this.specStatus = specStatus;
    }
}
