package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "business_incoperation")
public class BusinessIncorporation implements Serializable {

    private static final long serialVersionUID = 1L;

    /*@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;*/

    @Id
    @Column(name = "mid")
    private String mid;

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
    @Column(name = "incorporationdoc")
    private byte[] incorporationdoc;

    @Column(name = "incorporationdoc_content_type", nullable = false)
    private String incorporationdocContentType;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @JsonBackReference(value = "business-incorporation")
    @OneToOne(mappedBy = "businessIncorporation")
    private GstinDeatils gstinDeatils;

    @ApiModelProperty(hidden = true)
    @JsonManagedReference(value = "business-incorporation")
    @OneToOne
    @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private Business business;

   /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessIncorporation id(Long id) {
        this.id = id;
        return this;
    }*/

    public String getMid() {
        return this.mid;
    }

    public BusinessIncorporation mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

   /* public GstinDeatils getGstinDeatils() {
        return gstinDeatils;
    }

    public void setGstinDeatils(GstinDeatils gstinDeatils) {
        this.gstinDeatils = gstinDeatils;
    }
*/
   /* @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessIncorporation)) {
            return false;
        }
        return id != null && id.equals(((BusinessIncorporation) o).id);
    }*/

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusinessIncoperation{" +
              //  "id=" + getId() +
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
