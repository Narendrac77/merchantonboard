package fss.acquisition.merchantonboard.dao;

public class VerificationCheck {

    private int identityVerification;

    private int businessVerification;

    private int accountVerification;

    public int getIdentityVerification() {
        return identityVerification;
    }

    public void setIdentityVerification(int identityVerification) {
        this.identityVerification = identityVerification;
    }

    public int getBusinessVerification() {
        return businessVerification;
    }

    public void setBusinessVerification(int businessVerification) {
        this.businessVerification = businessVerification;
    }

    public int getAccountVerification() {
        return accountVerification;
    }

    public void setAccountVerification(int accountVerification) {
        this.accountVerification = accountVerification;
    }

    @Override
    public String toString() {
        return "VerificationCheck{" +
                "identityVerification=" + identityVerification +
                ", businessVerification=" + businessVerification +
                ", accountVerification=" + accountVerification +
                '}';
    }
}
