package cn.thinkjoy.hsll.bean.adminBean;

/**
 * Created by wpliu on 17/8/20.
 */
public class BuyInfoResponse {
    private String orderNo;
    private String orderTime;
    private String specName;
    private String num;
    private String recAddress;
    private String recPerson;
    private String recPhone;
    private int status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecPerson() {
        return recPerson;
    }

    public void setRecPerson(String recPerson) {
        this.recPerson = recPerson;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
