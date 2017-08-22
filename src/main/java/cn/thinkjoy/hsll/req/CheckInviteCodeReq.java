package cn.thinkjoy.hsll.req;

/**
 * Created by warden on 17/8/1.
 */
public class CheckInviteCodeReq extends BaseReq{

    private String inviteCode;
    private String name;
    private String phone;


    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
