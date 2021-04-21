package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

import fss.acquisition.merchantonboard.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "businesspan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusinessPan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "mid")
    private UUID mid;

    @NotNull
    @Column(name = "panno", nullable = false)
    private String panno;

    @Lob
    @Column(name = "pandoc")
    private byte[] pandoc;

    @Column(name = "pandoc_content_type", nullable = false)
    private String pandocContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

   // @JsonIgnoreProperties(value = { "businessPan", "businessIncoperation", "businessContacts", "bankAccounts" }, allowSetters = true)
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private Business business;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessPan id(Long id) {
        this.id = id;
        return this;
    }

    public UUID getMid() {
        return this.mid;
    }

    public BusinessPan mid(UUID mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public String getPanno() {
        return this.panno;
    }

    public BusinessPan panno(String panno) {
        this.panno = panno;
        return this;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public byte[] getPandoc() {
        return this.pandoc;
    }

    public BusinessPan pandoc(byte[] pandoc) {
        this.pandoc = pandoc;
        return this;
    }

    public void setPandoc(byte[] pandoc) {
        this.pandoc = pandoc;
    }

    public String getPandocContentType() {
        return this.pandocContentType;
    }

    public BusinessPan pandocContentType(String pandocContentType) {
        this.pandocContentType = pandocContentType;
        return this;
    }

    public void setPandocContentType(String pandocContentType) {
        this.pandocContentType = pandocContentType;
    }

    public Status getStatus() {
        return this.status;
    }

    public BusinessPan status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Business getBusiness() {
        return this.business;
    }

    public BusinessPan business(Business business) {
        this.setBusiness(business);
        return this;
    }

    public void setBusiness(Business business) {
        if (this.business != null) {
            this.business.setBusinessPan(null);
        }
        if (business != null) {
            business.setBusinessPan(this);
        }
        this.business = business;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessPan)) {
            return false;
        }
        return id != null && id.equals(((BusinessPan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusinessPan{" +
                "id=" + getId() +
                ", mid='" + getMid() + "'" +
                ", panno='" + getPanno() + "'" +
                ", pandoc='" + getPandoc() + "'" +
                ", pandocContentType='" + getPandocContentType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
