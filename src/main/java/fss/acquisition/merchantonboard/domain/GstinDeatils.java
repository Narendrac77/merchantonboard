package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

import fss.acquisition.merchantonboard.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "gstindetails")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GstinDeatils implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "mid")
    private UUID mid;

    @NotNull
    @Column(name = "gstinno", nullable = false)
    private String gstinno;

    @Lob
    @Column(name = "gstindoc", nullable = false)
    private byte[] gstindoc;

    @Column(name = "gstindoc_content_type", nullable = false)
    private String gstindocContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @JsonIgnoreProperties(value = { "gstinDeatils", "business" }, allowSetters = true)
    @OneToOne
    @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private BusinessIncoperation businessIncoperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GstinDeatils id(Long id) {
        this.id = id;
        return this;
    }

    public UUID getMid() {
        return this.mid;
    }

    public GstinDeatils mid(UUID mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public String getGstinno() {
        return this.gstinno;
    }

    public GstinDeatils gstinno(String gstinno) {
        this.gstinno = gstinno;
        return this;
    }

    public void setGstinno(String gstinno) {
        this.gstinno = gstinno;
    }

    public byte[] getGstindoc() {
        return this.gstindoc;
    }

    public GstinDeatils gstindoc(byte[] gstindoc) {
        this.gstindoc = gstindoc;
        return this;
    }

    public void setGstindoc(byte[] gstindoc) {
        this.gstindoc = gstindoc;
    }

    public String getGstindocContentType() {
        return this.gstindocContentType;
    }

    public GstinDeatils gstindocContentType(String gstindocContentType) {
        this.gstindocContentType = gstindocContentType;
        return this;
    }

    public void setGstindocContentType(String gstindocContentType) {
        this.gstindocContentType = gstindocContentType;
    }

    public Status getStatus() {
        return this.status;
    }

    public GstinDeatils status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BusinessIncoperation getBusinessIncoperation() {
        return this.businessIncoperation;
    }

    public GstinDeatils businessIncoperation(BusinessIncoperation businessIncoperation) {
        this.setBusinessIncoperation(businessIncoperation);
        return this;
    }

    public void setBusinessIncoperation(BusinessIncoperation businessIncoperation) {
        if (this.businessIncoperation != null) {
            this.businessIncoperation.setGstinDeatils(null);
        }
        if (businessIncoperation != null) {
            businessIncoperation.setGstinDeatils(this);
        }
        this.businessIncoperation = businessIncoperation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GstinDeatils)) {
            return false;
        }
        return id != null && id.equals(((GstinDeatils) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GstinDeatils{" +
                "id=" + getId() +
                ", mid='" + getMid() + "'" +
                ", gstinno='" + getGstinno() + "'" +
                ", gstindoc='" + getGstindoc() + "'" +
                ", gstindocContentType='" + getGstindocContentType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
