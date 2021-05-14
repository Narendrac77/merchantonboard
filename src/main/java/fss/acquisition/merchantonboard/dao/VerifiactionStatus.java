package fss.acquisition.merchantonboard.dao;


public class VerifiactionStatus {


    private String identityVerification;
    private String businessverificationpan;
    private String businessverificationgstin;
    private String accountVerification;

    public String getIdentityVerification() {
        return identityVerification;
    }

    public void setIdentityVerification(String identityVerification) {
        this.identityVerification = identityVerification;
    }

    public String getBusinessverificationpan() {
        return businessverificationpan;
    }

    public void setBusinessverificationpan(String businessverificationpan) {
        this.businessverificationpan = businessverificationpan;
    }

    public String getBusinessverificationgstin() {
        return businessverificationgstin;
    }

    public void setBusinessverificationgstin(String businessverificationgstin) {
        this.businessverificationgstin = businessverificationgstin;
    }

    public String getAccountVerification() {
        return accountVerification;
    }

    public void setAccountVerification(String accountVerification) {
        this.accountVerification = accountVerification;
    }

    @Override
    public String toString() {
        return "VerifiactionStatus{" +
                "identityVerification='" + identityVerification + '\'' +
                ", businessverificationpan='" + businessverificationpan + '\'' +
                ", businessverificationgstin='" + businessverificationgstin + '\'' +
                ", accountVerification='" + accountVerification + '\'' +
                '}';
    }
}
