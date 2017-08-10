package cn.thinkjoy.hsll.req;

/**
 * Created by warden on 17/8/8.
 */
public class BuyGoods extends BaseReq{

    private long goodId;
    private int goodNum;
    private long goodSpecId;
    private String goodSpeName;
    private Double goodPrice;
    private long addressId;
    private String addressName;
    private String receiptType;
    private String receiptTitle;
    private String receiptNo;
    private String buyerMessage;
    private String address;
    private String person;
    private String phone;


    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public long getGoodSpecId() {
        return goodSpecId;
    }

    public void setGoodSpecId(long goodSpecId) {
        this.goodSpecId = goodSpecId;
    }

    public String getGoodSpeName() {
        return goodSpeName;
    }

    public void setGoodSpeName(String goodSpeName) {
        this.goodSpeName = goodSpeName;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
