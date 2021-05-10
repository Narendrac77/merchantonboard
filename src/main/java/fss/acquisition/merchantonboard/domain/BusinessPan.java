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
@Table(name = "businesspan")
public class BusinessPan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mid")
    private String mid;

    @NotNull
    @Column(name = "panno", nullable = false,unique = true)
    private String panno;

    @Lob
    @Column(name = "pandoc")
    private byte[] pandoc;

    @Column(name = "pandoc_content_type")
    private String pandocContentType;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    // @JsonIgnoreProperties(value = { "businessPan", "businessIncoperation", "businessContacts", "bankAccounts" }, allowSetters = true)
    @ApiModelProperty(hidden = true)
    @JsonManagedReference(value = "business-pan")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mid", insertable = false, updatable = false,referencedColumnName = "mid")
    private Business business;

    public Long getId() {
        return id;
    }

    public String getMid() {
        return this.mid;
    }

    public BusinessPan mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
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
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
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
                ", mid='" + getMid() + "'" +
                ", panno='" + getPanno() + "'" +
                ", pandoc='" + getPandoc() + "'" +
                ", pandocContentType='" + getPandocContentType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
