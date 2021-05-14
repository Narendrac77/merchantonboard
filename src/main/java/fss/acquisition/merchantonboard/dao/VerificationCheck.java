package fss.acquisition.merchantonboard.dao;

public class VerificationCheck {

    private int identityVerification;

    private int businessVerificationpan;

    private int businessVerificationgstin;


    private int accountVerification;

    public int getIdentityVerification() {
        return identityVerification;
    }

    public void setIdentityVerification(int identityVerification) {
        this.identityVerification = identityVerification;
    }


    public int getAccountVerification() {
        return accountVerification;
    }

    public void setAccountVerification(int accountVerification) {
        this.accountVerification = accountVerification;
    }

    public int getBusinessVerificationpan() {
        return businessVerificationpan;
    }

    public void setBusinessVerificationpan(int businessVerificationpan) {
        this.businessVerificationpan = businessVerificationpan;
    }

    public int getBusinessVerificationgstin() {
        return businessVerificationgstin;
    }

    public void setBusinessVerificationgstin(int businessVerificationgstin) {
        this.businessVerificationgstin = businessVerificationgstin;
    }

    @Override
    public String toString() {
        return "VerificationCheck{" +
                "identityVerification=" + identityVerification +
                ", businessVerificationpan=" + businessVerificationpan +
                ", businessVerificationgstin=" + businessVerificationgstin +
                ", accountVerification=" + accountVerification +
                '}';
    }
}
