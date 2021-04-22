package fss.acquisition.merchantonboard.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "businesscontact")
public class BusinessContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "contactname", nullable = false)
    private String contactname;

    @NotNull
    @Column(name = "contactmobileno", nullable = false)
    private String contactmobileno;

    @NotNull
    @Column(name = "contactemailid", nullable = false)
    private String contactemailid;

    @Column(name = "mid")
    private UUID mid;

    @Column(name = "contacttype")
    private Integer contacttype;



    @ManyToOne
   // @JsonIgnoreProperties(value = { "businessPan", "businessIncoperation", "businessContacts", "bankAccounts" }, allowSetters = true)
    @JsonIgnore
    @JoinColumn(name = "mid",insertable = false,updatable = false,unique = true,referencedColumnName = "mid")
    private Business business;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessContact id(Long id) {
        this.id = id;
        return this;
    }

    public String getContactname() {
        return this.contactname;
    }

    public BusinessContact contactname(String contactname) {
        this.contactname = contactname;
        return this;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactmobileno() {
        return this.contactmobileno;
    }

    public BusinessContact contactmobileno(String contactmobileno) {
        this.contactmobileno = contactmobileno;
        return this;
    }

    public void setContactmobileno(String contactmobileno) {
        this.contactmobileno = contactmobileno;
    }

    public String getContactemailid() {
        return this.contactemailid;
    }

    public BusinessContact contactemailid(String contactemailid) {
        this.contactemailid = contactemailid;
        return this;
    }

    public void setContactemailid(String contactemailid) {
        this.contactemailid = contactemailid;
    }

    public UUID getMid() {
        return this.mid;
    }

    public BusinessContact mid(UUID mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public Integer getContacttype() {
        return this.contacttype;
    }

    public BusinessContact contacttype(Integer contacttype) {
        this.contacttype = contacttype;
        return this;
    }

    public void setContacttype(Integer contacttype) {
        this.contacttype = contacttype;
    }



    public Business getBusiness() {
        return this.business;
    }

    public BusinessContact business(Business business) {
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
        if (!(o instanceof BusinessContact)) {
            return false;
        }
        return id != null && id.equals(((BusinessContact) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusinessContact{" +
                "id=" + getId() +
                ", contactname='" + getContactname() + "'" +
                ", contactmobileno='" + getContactmobileno() + "'" +
                ", contactemailid='" + getContactemailid() + "'" +
                ", mid='" + getMid() + "'" +
                ", contacttype=" + getContacttype() +
                "}";
    }
}
