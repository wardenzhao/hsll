package cn.thinkjoy.hsll.bean.adminBean;

import java.util.List;

/**
 * Created by wpliu on 17/8/14.
 */
public class ProductResponse {
    private long productId;
    private String productName;
    private String title;
    private String subTitle;
    private String images;
    private String describe;
    private List<ProductSpec> specs;
    private String content;
    private String specTips;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSpecTips() {
        return specTips;
    }

    public void setSpecTips(String specTips) {
        this.specTips = specTips;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<ProductSpec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<ProductSpec> specs) {
        this.specs = specs;
    }
}
