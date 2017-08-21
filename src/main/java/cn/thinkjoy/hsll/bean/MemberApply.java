package cn.thinkjoy.hsll.bean;

import java.io.Serializable;

/**
 * Created by warden on 17/7/22.
 */
public class MemberApply implements Serializable {

    private long id;
    private String name;
    private String openId;
    private int sex;
    private String address;
    private String phone;
    private long inviteMemberId;
    private long status;
    private String inviteMemberName;

    public String getInviteMemberName() {
        return inviteMemberName;
    }

    public void setInviteMemberName(String inviteMemberName) {
        this.inviteMemberName = inviteMemberName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getInviteMemberId() {
        return inviteMemberId;
    }

    public void setInviteMemberId(long inviteMemberId) {
        this.inviteMemberId = inviteMemberId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
