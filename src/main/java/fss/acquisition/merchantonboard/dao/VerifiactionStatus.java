package fss.acquisition.merchantonboard.dao;


public class VerifiactionStatus {

    private String businessverification;
    private String identityVerification;
    private String accountVerification;

    public String getBusinessverification() {
        return businessverification;
    }

    public void setBusinessverification(String businessverification) {
        this.businessverification = businessverification;
    }

    public String getIdentityVerification() {
        return identityVerification;
    }

    public void setIdentityVerification(String identityVerification) {
        this.identityVerification = identityVerification;
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
                "businessverification=" + businessverification +
                ", identityVerification=" + identityVerification +
                ", accountVerification=" + accountVerification +
                '}';
    }
}
