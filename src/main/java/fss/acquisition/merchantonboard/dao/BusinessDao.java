package fss.acquisition.merchantonboard.dao;

import fss.acquisition.merchantonboard.dao.enumeration.RiskEnum;

import java.util.UUID;

public class BusinessDao {

    private String mid;

    private RiskEnum riskEnum;

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

    @Override
    public String toString() {
        return "BusinessDao{" +
                "mid='" + mid + '\'' +
                ", riskEnum=" + riskEnum +
                '}';
    }
}
