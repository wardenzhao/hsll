package cn.thinkjoy.hsll.resp;

import java.util.List;

/**
 * Created by warden on 17/7/31.
 */
public class GoodResp {


    private long goodId;
    private String goodName;
    private String goodTitle;
    private String advImg;
    private String goodDes;
    private Double goodPrice;
    private String goodAddress;
    private String goodIcon;
    private String goodSubTitle;
    private List<GoodSpecResp> goodSpec;
    private String thumb;


    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getGoodDes() {
        return goodDes;
    }

    public void setGoodDes(String goodDes) {
        this.goodDes = goodDes;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodAddress() {
        return goodAddress;
    }

    public void setGoodAddress(String goodAddress) {
        this.goodAddress = goodAddress;
    }

    public String getGoodIcon() {
        return goodIcon;
    }

    public void setGoodIcon(String goodIcon) {
        this.goodIcon = goodIcon;
    }

    public String getGoodSubTitle() {
        return goodSubTitle;
    }

    public void setGoodSubTitle(String goodSubTitle) {
        this.goodSubTitle = goodSubTitle;
    }

    public List<GoodSpecResp> getGoodSpec() {
        return goodSpec;
    }

    public void setGoodSpec(List<GoodSpecResp> goodSpec) {
        this.goodSpec = goodSpec;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
