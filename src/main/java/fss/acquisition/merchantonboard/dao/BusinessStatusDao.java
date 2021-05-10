package fss.acquisition.merchantonboard.dao;

import fss.acquisition.merchantonboard.domain.enumeration.Status;
import java.util.*;

public class BusinessStatusDao {
    private VerifiactionStatus verifiactionStatus;
    private Status bankStatus;

    public VerifiactionStatus getVerifiactionStatus() {
        return verifiactionStatus;
    }

    public void setVerifiactionStatus(VerifiactionStatus verifiactionStatus) {
        this.verifiactionStatus = verifiactionStatus;
    }

    public Status getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(Status bankStatus) {
        this.bankStatus = bankStatus;
    }

    @Override
    public String toString() {
        return "BusinessStatusDao{" +
                "verifiactionStatus=" + verifiactionStatus +
                ", bankStatus=" + bankStatus +
                '}';
    }
}
