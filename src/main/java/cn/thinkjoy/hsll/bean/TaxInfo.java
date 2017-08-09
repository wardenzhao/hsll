package cn.thinkjoy.hsll.bean;

/**
 * 发票信息
 * Created by warden on 17/8/8.
 */
public class TaxInfo {

    private String receiptType;
    private String receiptTitle;
    private String receiptNo;


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
}
