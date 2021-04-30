package fss.acquisition.merchantonboard.domain;
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
@Table(name = "gstindetails")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GstinDeatils implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "mid")
    private String mid;

    @NotNull
    @Column(name = "gstinno", nullable = false)
    private String gstinno;

    @Lob
    @Column(name = "gstindoc")
    private byte[] gstindoc;

    @Column(name = "gstindoc_content_type", nullable = false)
    private String gstindocContentType;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    @ApiModelProperty(hidden = true)
    @OneToOne
    @JsonManagedReference(value = "business-incorporation")
    @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private BusinessIncorporation businessIncorporation;

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

    public String getMid() {

        return this.mid;
    }

    public GstinDeatils mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
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

    public BusinessIncorporation getBusinessIncorporation() {
        return businessIncorporation;
    }

    public void setBusinessIncorporation(BusinessIncorporation businessIncorporation) {
        this.businessIncorporation = businessIncorporation;
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
