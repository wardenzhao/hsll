package cn.thinkjoy.hsll.resp;

/**
 * Created by warden on 17/8/9.
 */
public class TakeCodeResp {

    private String goodName;
    private String goodImg;
    private String goodBatch;
    private String reserveMember;
    private long goodId;
    private String takeCode;
    private String sendTime;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public String getGoodBatch() {
        return goodBatch;
    }

    public void setGoodBatch(String goodBatch) {
        this.goodBatch = goodBatch;
    }

    public String getReserveMember() {
        return reserveMember;
    }

    public void setReserveMember(String reserveMember) {
        this.reserveMember = reserveMember;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
