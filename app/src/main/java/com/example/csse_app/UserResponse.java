package com.example.csse_app;

public class UserResponse {

    private String cardnum;
    private String ccnum;
    private String cvv;
    private String exdate;
    private String amount;
    private String dates;

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDates() {
        return dates;
    }

    public void setCcnum(String ccnum) {
        this.ccnum = ccnum;
    }

    public String getCcnum() {
        return ccnum;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExdate() {
        return exdate;
    }

    public void setExdate(String exdate) {
        this.exdate = exdate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
