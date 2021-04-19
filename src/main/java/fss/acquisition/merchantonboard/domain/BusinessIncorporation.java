package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fss.acquisition.merchantonboard.domain.enumeration.Status;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "business_incoperation")
public class BusinessIncorporation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "mid")
    private UUID mid;

    @NotNull
    @Column(name = "incorporationno", nullable = false)
    private String incorporationno;

    @NotNull
    @Column(name = "businessregisteredaddress", nullable = false)
    private String businessregisteredaddress;

    @NotNull
    @Column(name = "businesslegalname", nullable = false)
    private String businesslegalname;

    @Lob
    @Column(name = "incorporationdoc", nullable = false)
    private byte[] incorporationdoc;

    @Column(name = "incorporationdoc_content_type", nullable = false)
    private String incorporationdocContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @JsonIgnoreProperties(value = { "businessIncoperation" }, allowSetters = true)
    @OneToOne(mappedBy = "businessIncoperation")
    private GstinDeatils gstinDeatils;

    @OneToOne
    @JsonIgnoreProperties(value = { "businessPan", "businessIncoperation", "businessContacts", "bankAccounts" }, allowSetters = true)
    @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private Business business;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessIncorporation id(Long id) {
        this.id = id;
        return this;
    }

    public UUID getMid() {
        return this.mid;
    }

    public BusinessIncorporation mid(UUID mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public String getIncorporationno() {
        return this.incorporationno;
    }

    public BusinessIncorporation incorporationno(String incorporationno) {
        this.incorporationno = incorporationno;
        return this;
    }

    public void setIncorporationno(String incorporationno) {
        this.incorporationno = incorporationno;
    }

    public String getBusinessregisteredaddress() {
        return this.businessregisteredaddress;
    }

    public BusinessIncorporation businessregisteredaddress(String businessregisteredaddress) {
        this.businessregisteredaddress = businessregisteredaddress;
        return this;
    }

    public void setBusinessregisteredaddress(String businessregisteredaddress) {
        this.businessregisteredaddress = businessregisteredaddress;
    }

    public String getBusinesslegalname() {
        return this.businesslegalname;
    }

    public BusinessIncorporation businesslegalname(String businesslegalname) {
        this.businesslegalname = businesslegalname;
        return this;
    }

    public void setBusinesslegalname(String businesslegalname) {
        this.businesslegalname = businesslegalname;
    }

    public byte[] getIncorporationdoc() {
        return this.incorporationdoc;
    }

    public BusinessIncorporation incorporationdoc(byte[] incorporationdoc) {
        this.incorporationdoc = incorporationdoc;
        return this;
    }

    public void setIncorporationdoc(byte[] incorporationdoc) {
        this.incorporationdoc = incorporationdoc;
    }

    public String getIncorporationdocContentType() {
        return this.incorporationdocContentType;
    }

    public BusinessIncorporation incorporationdocContentType(String incorporationdocContentType) {
        this.incorporationdocContentType = incorporationdocContentType;
        return this;
    }

    public void setIncorporationdocContentType(String incorporationdocContentType) {
        this.incorporationdocContentType = incorporationdocContentType;
    }

    public Status getStatus() {
        return this.status;
    }

    public BusinessIncorporation status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public GstinDeatils getGstinDeatils() {
        return this.gstinDeatils;
    }

    public BusinessIncorporation gstinDeatils(GstinDeatils gstinDeatils) {
        this.setGstinDeatils(gstinDeatils);
        return this;
    }

    public void setGstinDeatils(GstinDeatils gstinDeatils) {
        this.gstinDeatils = gstinDeatils;
    }

    public Business getBusiness() {
        return this.business;
    }

    public BusinessIncorporation business(Business business) {
        this.setBusiness(business);
        return this;
    }

    public void setBusiness(Business business) {
        if (this.business != null) {
            this.business.setBusinessIncoperation(null);
        }
        if (business != null) {
            business.setBusinessIncoperation(this);
        }
        this.business = business;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessIncorporation)) {
            return false;
        }
        return id != null && id.equals(((BusinessIncorporation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusinessIncoperation{" +
                "id=" + getId() +
                ", mid='" + getMid() + "'" +
                ", incorporationno='" + getIncorporationno() + "'" +
                ", businessregisteredaddress='" + getBusinessregisteredaddress() + "'" +
                ", businesslegalname='" + getBusinesslegalname() + "'" +
                ", incorporationdoc='" + getIncorporationdoc() + "'" +
                ", incorporationdocContentType='" + getIncorporationdocContentType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
