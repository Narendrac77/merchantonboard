package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import fss.acquisition.merchantonboard.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "business")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

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

    @Column(name = "wesiteurl")
    private String wesiteurl;

    @Column(name = "age")
    private Integer age;

    @Column(name = "turnover")
    private Integer turnover;

    @Column(name = "mid")
    private UUID mid;

    @Column(name = "riskscoring")
    private Integer riskscoring;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @JsonIgnoreProperties(value = { "business" }, allowSetters = true)
    @OneToOne(mappedBy = "business")
    private BusinessPan businessPan;

    @JsonIgnoreProperties(value = { "gstinDeatils", "business" }, allowSetters = true)
    @OneToOne(mappedBy = "business")
    private BusinessIncorporation businessIncorporation;

    @OneToMany(mappedBy = "business")
    @JsonIgnoreProperties(value = { "aadharDetails", "business" }, allowSetters = true)
    private Set<BusinessContact> businessContacts = new HashSet<>();

    @OneToMany(mappedBy = "business")
    @JsonIgnoreProperties(value = { "business" }, allowSetters = true)
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "aadharDetails" }, allowSetters = true)
    @JoinColumn(unique = true)
    private BusinessOwner businessOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business id(Long id) {
        this.id = id;
        return this;
    }

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

    public String getWesiteurl() {
        return this.wesiteurl;
    }

    public Business wesiteurl(String wesiteurl) {
        this.wesiteurl = wesiteurl;
        return this;
    }

    public void setWesiteurl(String wesiteurl) {
        this.wesiteurl = wesiteurl;
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

    public Integer getTurnover() {
        return this.turnover;
    }

    public Business turnover(Integer turnover) {
        this.turnover = turnover;
        return this;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public UUID getMid() {
        return this.mid;
    }

    public Business mid(UUID mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(UUID mid) {
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

    public BusinessPan getBusinessPan() {
        return this.businessPan;
    }

    public Business businessPan(BusinessPan businessPan) {
        this.setBusinessPan(businessPan);
        return this;
    }

    public void setBusinessPan(BusinessPan businessPan) {
        this.businessPan = businessPan;
    }

    public BusinessIncorporation getBusinessIncoperation() {
        return this.businessIncorporation;
    }

    public Business businessIncoperation(BusinessIncorporation businessIncorporation) {
        this.setBusinessIncoperation(businessIncorporation);
        return this;
    }

    public void setBusinessIncoperation(BusinessIncorporation businessIncorporation) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Business)) {
            return false;
        }
        return id != null && id.equals(((Business) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Business{" +
                "id=" + getId() +
                ", displayname='" + getDisplayname() + "'" +
                ", businesstype='" + getBusinesstype() + "'" +
                ", businesscategory='" + getBusinesscategory() + "'" +
                ", businesssubcategory='" + getBusinesssubcategory() + "'" +
                ", communicationaddress='" + getCommunicationaddress() + "'" +
                ", wesiteurl='" + getWesiteurl() + "'" +
                ", age=" + getAge() +
                ", turnover=" + getTurnover() +
                ", mid='" + getMid() + "'" +
                ", riskscoring=" + getRiskscoring() +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
