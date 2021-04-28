package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "bankaccount")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mid")
    private String mid;

    @NotNull
    @Column(name = "accountno", nullable = false)
    private String accountno;

    @NotNull
    @Column(name = "ifsccode", nullable = false)
    private String ifsccode;

    @NotNull
    @Column(name = "bankname", nullable = false)
    private String bankname;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ApiModelProperty(hidden = true)
    @ManyToOne
    @JsonBackReference(value ="bank-accounts")
   @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private Business business;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount id(Long id) {
        this.id = id;
        return this;
    }

    public String getMid() {
        return this.mid;
    }

    public BankAccount mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAccountno() {
        return this.accountno;
    }

    public BankAccount accountno(String accountno) {
        this.accountno = accountno;
        return this;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getIfsccode() {
        return this.ifsccode;
    }

    public BankAccount ifsccode(String ifsccode) {
        this.ifsccode = ifsccode;
        return this;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getBankname() {
        return this.bankname;
    }

    public BankAccount bankname(String bankname) {
        this.bankname = bankname;
        return this;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getType() {
        return this.type;
    }

    public BankAccount type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Status getStatus() {
        return this.status;
    }

    public BankAccount status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Business getBusiness() {
        return this.business;
    }

    public BankAccount business(Business business) {
        this.setBusiness(business);
        return this;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return id != null && id.equals(((BankAccount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + getId() +
                ", mid='" + getMid() + "'" +
                ", accountno='" + getAccountno() + "'" +
                ", ifsccode='" + getIfsccode() + "'" +
                ", bankname='" + getBankname() + "'" +
                ", type='" + getType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
