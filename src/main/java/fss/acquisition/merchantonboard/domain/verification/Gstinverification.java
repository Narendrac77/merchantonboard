package fss.acquisition.merchantonboard.domain.verification;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "gstinverification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gstinverification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "gstinverificationid")
    private String gstinverificationId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGstinverificationId() {
        return gstinverificationId;
    }

    public Gstinverification gstinverificationId(String gstinverificationId) {
        this.gstinverificationId = gstinverificationId;
        return this;
    }

    public void setGstinverificationId(String gstinverificationId) {
        this.gstinverificationId = gstinverificationId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gstinverification)) {
            return false;
        }
        return id != null && id.equals(((Gstinverification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Gstinverification{" +
            "id=" + getId() +
            ", gstinverificationId=" + getGstinverificationId() +
            "}";
    }
}
