package com.company;

public class transactioninfo {
    private String transactionId;
    private String transaction_date;
    private String trans_mode;
    private String trans_type;
    private int trans_amount;

    public transactioninfo(String transactionId, String transaction_date, String trans_mode, String trans_type, int trans_amount) {
        this.transactionId = transactionId;
        this.transaction_date = transaction_date;
        this.trans_mode = trans_mode;
        this.trans_type = trans_type;
        this.trans_amount = trans_amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTrans_mode() {
        return trans_mode;
    }

    public void setTrans_mode(String trans_mode) {
        this.trans_mode = trans_mode;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public int getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(int trans_amount) {
        this.trans_amount = trans_amount;
    }


}
