package cn.thinkjoy.hsll.req;

/**
 * Created by warden on 17/8/22.
 */
public class ChangeOrderAddressReq extends BaseReq{

    private String orderNo;
    private String person;
    private String phone;
    private String address;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
