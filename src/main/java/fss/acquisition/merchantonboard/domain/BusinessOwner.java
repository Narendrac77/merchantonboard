package fss.acquisition.merchantonboard.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "businessowner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusinessOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "mobileno", nullable = false,unique = true)
    private String mobileno;

    @NotNull
    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

   // @JsonIgnoreProperties( allowSetters = true)
    /*@OneToOne(mappedBy = "businessOwner")
    @JsonBackReference(value = "aadhar-details")
    private AadharDetails aadharDetails;*/

    @OneToMany(mappedBy = "businessOwner")
    @JsonManagedReference(value = "business-owner")
    private Set<Business> businesses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessOwner id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public BusinessOwner name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return this.mobileno;
    }

    public BusinessOwner mobileno(String mobileno) {
        this.mobileno = mobileno;
        return this;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return this.email;
    }

    public BusinessOwner email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public BusinessOwner address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*public AadharDetails getAadharDetails() {
        return this.aadharDetails;
    }

    public BusinessOwner aadharDetails(AadharDetails aadharDetails) {
        this.setAadharDetails(aadharDetails);
        return this;
    }

    public void setAadharDetails(AadharDetails aadharDetails) {
        this.aadharDetails = aadharDetails;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessOwner)) {
            return false;
        }
        return id != null && id.equals(((BusinessOwner) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Set<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        this.businesses = businesses;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusinessOwner{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", mobileno='" + getMobileno() + "'" +
                ", email='" + getEmail() + "'" +
                ", address='" + getAddress() + "'" +
                "}";
    }
}
