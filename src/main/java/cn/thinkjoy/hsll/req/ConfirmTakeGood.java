package cn.thinkjoy.hsll.req;

/**
 * Created by warden on 17/8/10.
 */
public class ConfirmTakeGood extends BaseReq{

    private String takeCode;
    private long goodId;
    private String reserveMember;
    private String person;
    private String phone;
    private String address;

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public String getReserveMember() {
        return reserveMember;
    }

    public void setReserveMember(String reserveMember) {
        this.reserveMember = reserveMember;
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
