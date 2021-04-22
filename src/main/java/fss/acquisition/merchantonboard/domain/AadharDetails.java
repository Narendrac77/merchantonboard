package fss.acquisition.merchantonboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fss.acquisition.merchantonboard.domain.enumeration.Status;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "aadhardetails")
public class AadharDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @NotNull
    @Column(name = "aadharno", nullable = false)
    private UUID aadharno;

    @NotNull
    @Column(name = "businessid", nullable = false,unique = true)
    private Long businessid;

    @Lob
    @Column(name = "aadhardoc")
    private byte[] aadhardoc;

    @Column(name = "aadhardoc_content_type", nullable = false)
    private String aadhardocContentType;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne
   // @JsonIgnoreProperties(value = {"aadharDetails","business"}, allowSetters = true)
    @JsonIgnore
    @JoinColumn(name = "businessid",insertable = false,updatable = false,unique = true)
    private BusinessOwner businessOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AadharDetails id(Long id) {
        this.id = id;
        return this;
    }



    public UUID getAadharno() {
        return this.aadharno;
    }

    public AadharDetails aadharno(UUID aadharno) {
        this.aadharno = aadharno;
        return this;
    }

    public void setAadharno(UUID aadharno) {
        this.aadharno = aadharno;
    }

    public byte[] getAadhardoc() {
        return this.aadhardoc;
    }

    public AadharDetails aadhardoc(byte[] aadhardoc) {
        this.aadhardoc = aadhardoc;
        return this;
    }

    public Long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public void setAadhardoc(byte[] aadhardoc) {
        this.aadhardoc = aadhardoc;
    }

    public String getAadhardocContentType() {
        return this.aadhardocContentType;
    }

    public AadharDetails aadhardocContentType(String aadhardocContentType) {
        this.aadhardocContentType = aadhardocContentType;
        return this;
    }

    public void setAadhardocContentType(String aadhardocContentType) {
        this.aadhardocContentType = aadhardocContentType;
    }

    public Status getStatus() {
        return this.status;
    }

    public AadharDetails status(Status status) {
        this.status = status;
        return this;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public BusinessOwner getBusinessOwner() {
        return this.businessOwner;
    }

    public AadharDetails businessOwner(BusinessOwner businessOwner) {
        this.setBusinessOwner(businessOwner);
        return this;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        if (this.businessOwner != null) {
            this.businessOwner.setAadharDetails(null);
        }
        if (businessOwner != null) {
            businessOwner.setAadharDetails(this);
        }
        this.businessOwner = businessOwner;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AadharDetails)) {
            return false;
        }
        return id != null && id.equals(((AadharDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AadharDetails{" +
                "id=" + getId() +
                ", aadharno='" + getAadharno() + "'" +
                ", aadhardoc='" + getAadhardoc() + "'" +
                ", aadhardocContentType='" + getAadhardocContentType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
