package cn.thinkjoy.hsll.bean.adminBean;

/**
 * Created by wpliu on 17/8/20.
 */
public class BatchInfo {
    private long id;
    private String name;
    private int number;
    private int usedNum;
    private String goodsName;
    private String goodsCode;
    private String goodSpecName;
    private String batchCode;
    private String goodSpecId;
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGoodSpecId() {
        return goodSpecId;
    }

    public void setGoodSpecId(String goodSpecId) {
        this.goodSpecId = goodSpecId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodSpecName() {
        return goodSpecName;
    }

    public void setGoodSpecName(String goodSpecName) {
        this.goodSpecName = goodSpecName;
    }
}
