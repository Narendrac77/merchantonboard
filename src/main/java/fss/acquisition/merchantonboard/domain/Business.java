package fss.acquisition.merchantonboard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "business")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "businessid")
    private Long businessid;


    @Column(name = "displayname")
    private String displayname;

    @Column(name = "businesstype")
    private String businesstype;

    @Column(name = "businesscategory")
    private String businesscategory;

    @Column(name = "businesssubcategory")
    private String businesssubcategory;

    @Column(name = "communicationaddress")
    private String communicationaddress;

    @Column(name = "websiteurl")
    private String websiteurl;

    @Column(name = "age")
    private Integer age;

    @Column(name = "turnover")
    private BigInteger turnover;

    @Id
    @Column(name = "mid",unique = true)
    private String mid;

    @JsonIgnore
    @Column(name = "riskscoring")
    private Integer riskscoring;

    @JsonIgnore
    @Column(name = "accountverification")
    public Integer accountverification;

    @JsonIgnore
    @Column(name = "identityverification")
    public Integer identityverification;

    @JsonIgnore
    @Column(name = "businessverification")
    public Integer businessverification;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

   @JsonBackReference(value = "business-pan")
    @OneToOne(mappedBy = "business",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private BusinessPan businessPan;

    @JsonBackReference(value = "business-incorporation")
    @OneToOne(mappedBy = "business",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private BusinessIncorporation businessIncorporation;

    @JsonManagedReference(value = "business-contacts")
    @OneToMany(mappedBy = "business",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<BusinessContact> businessContacts = new HashSet<>();

    @JsonManagedReference(value ="bank-accounts")
    @OneToMany(mappedBy = "business",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @ApiModelProperty(hidden = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "business-owner")
    @JoinColumn(name = "businessid",insertable = false,updatable = false,unique = true)
    private BusinessOwner businessOwner;

    public String getDisplayname() {
        return this.displayname;
    }

    public Business displayname(String displayname) {
        this.displayname = displayname;
        return this;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getBusinesstype() {
        return this.businesstype;
    }

    public Business businesstype(String businesstype) {
        this.businesstype = businesstype;
        return this;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getBusinesscategory() {
        return this.businesscategory;
    }

    public Business businesscategory(String businesscategory) {
        this.businesscategory = businesscategory;
        return this;
    }

    public void setBusinesscategory(String businesscategory) {
        this.businesscategory = businesscategory;
    }

    public String getBusinesssubcategory() {
        return this.businesssubcategory;
    }

    public Business businesssubcategory(String businesssubcategory) {
        this.businesssubcategory = businesssubcategory;
        return this;
    }

    public void setBusinesssubcategory(String businesssubcategory) {
        this.businesssubcategory = businesssubcategory;
    }

    public String getCommunicationaddress() {
        return this.communicationaddress;
    }

    public Business communicationaddress(String communicationaddress) {
        this.communicationaddress = communicationaddress;
        return this;
    }

    public void setCommunicationaddress(String communicationaddress) {
        this.communicationaddress = communicationaddress;
    }

    public String getWebsiteurl() {
        return this.websiteurl;
    }

    public Business wesiteurl(String wesiteurl) {
        this.websiteurl = wesiteurl;
        return this;
    }

    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }

    public Integer getAge() {
        return this.age;
    }

    public Business age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigInteger getTurnover() {
        return this.turnover;
    }

    public Business turnover(BigInteger turnover) {
        this.turnover = turnover;
        return this;
    }

    public void setTurnover(BigInteger turnover) {
        this.turnover = turnover;
    }

    public String getMid() {
        return this.mid;
    }

    public Business mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Integer getRiskscoring() {
        return this.riskscoring;
    }

    public Business riskscoring(Integer riskscoring) {
        this.riskscoring = riskscoring;
        return this;
    }

    public void setRiskscoring(Integer riskscoring) {
        this.riskscoring = riskscoring;
    }

    public Status getStatus() {
        return this.status;
    }

    public Business status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getAccountverification() {
        return accountverification;
    }

    public void setAccountverification(Integer accountverification) {
        this.accountverification = accountverification;
    }

    public Integer getIdentityverification() {
        return identityverification;
    }

    public void setIdentityverification(Integer identityverification) {
        this.identityverification = identityverification;
    }

    public Integer getBusinessverification() {
        return businessverification;
    }

    public void setBusinessverification(Integer businessverification) {
        this.businessverification = businessverification;
    }

    public Long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public BusinessPan getBusinessPan() {
        return businessPan;
    }

    public void setBusinessPan(BusinessPan businessPan) {
        this.businessPan = businessPan;
    }

    public BusinessIncorporation getBusinessIncorporation() {
        return businessIncorporation;
    }

    public void setBusinessIncorporation(BusinessIncorporation businessIncorporation) {
        this.businessIncorporation = businessIncorporation;
    }

    public Set<BusinessContact> getBusinessContacts() {
        return this.businessContacts;
    }

    public Business businessContacts(Set<BusinessContact> businessContacts) {
        this.setBusinessContacts(businessContacts);
        return this;
    }
    public Business addBusinessContact(BusinessContact businessContact) {
        this.businessContacts.add(businessContact);
        businessContact.setBusiness(this);
        return this;
    }

    public Business removeBusinessContact(BusinessContact businessContact) {
        this.businessContacts.remove(businessContact);
        businessContact.setBusiness(null);
        return this;
    }

    public void setBusinessContacts(Set<BusinessContact> businessContacts) {
        if (this.businessContacts != null) {
            this.businessContacts.forEach(i -> i.setBusiness(null));
        }
        if (businessContacts != null) {
            businessContacts.forEach(i -> i.setBusiness(this));
        }
        this.businessContacts = businessContacts;
    }

    public Set<BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }

    public Business bankAccounts(Set<BankAccount> bankAccounts) {
        this.setBankAccounts(bankAccounts);
        return this;
    }

    public Business addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
        bankAccount.setBusiness(this);
        return this;
    }

    public Business removeBankAccount(BankAccount bankAccount) {
        this.bankAccounts.remove(bankAccount);
        bankAccount.setBusiness(null);
        return this;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        if (this.bankAccounts != null) {
            this.bankAccounts.forEach(i -> i.setBusiness(null));
        }
        if (bankAccounts != null) {
            bankAccounts.forEach(i -> i.setBusiness(this));
        }
        this.bankAccounts = bankAccounts;
    }

    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    public Business() {
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Business)) {
            return false;
        }
        return id != null && id.equals(((Business) o).id);
    }*/

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    // prettier-ignore
   @Override
    public String toString() {
        return "Business{" +
             //   "id=" + getId() +
                ", displayname='" + getDisplayname() + "'" +
                ", businesstype='" + getBusinesstype() + "'" +
                ", businesscategory='" + getBusinesscategory() + "'" +
                ", businesssubcategory='" + getBusinesssubcategory() + "'" +
                ", communicationaddress='" + getCommunicationaddress() + "'" +
                ", wesiteurl='" + getWebsiteurl() + "'" +
                ", age=" + getAge() +
                ", turnover=" + getTurnover() +
                ", mid='" + getMid() + "'" +
                ", riskscoring=" + getRiskscoring() +
                ", status='" + getStatus() + "'" +
                "}";
    }

}
