package cn.thinkjoy.hsll.bean;

import java.io.Serializable;

/**
 * Created by warden on 17/7/22.
 */
public class Member implements Serializable {

    private long id;
    private String name;
    private String openId;


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
}
