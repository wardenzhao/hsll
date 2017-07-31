package cn.thinkjoy.hsll.resp;

/**
 * Created by warden on 17/7/27.
 */
public class BaseResp {

    private String code;
    private String opedId;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpedId(String openid) {
        return opedId;
    }

    public void setOpedId(String opedId) {
        this.opedId = opedId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
