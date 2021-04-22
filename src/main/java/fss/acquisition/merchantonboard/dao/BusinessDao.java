package fss.acquisition.merchantonboard.dao;

import fss.acquisition.merchantonboard.dao.enumeration.RiskEnum;

import java.util.UUID;

public class BusinessDao {

    private String mid;

    private RiskEnum riskEnum;

    private VerificationCheck verificationCheck;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public RiskEnum getRiskEnum() {
        return riskEnum;
    }

    public void setRiskEnum(RiskEnum riskEnum) {
        this.riskEnum = riskEnum;
    }

    public VerificationCheck getVerificationCheck() {
        return verificationCheck;
    }

    public void setVerificationCheck(VerificationCheck verificationCheck) {
        this.verificationCheck = verificationCheck;
    }

    @Override
    public String toString() {
        return "BusinessDao{" +
                "mid='" + mid + '\'' +
                ", riskEnum=" + riskEnum +
                ", verificationCheck=" + verificationCheck +
                '}';
    }
}
