package fss.acquisition.merchantonboard.domain.verification;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "bankverification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bankverification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "bankverificationid")
    private Integer bankverificationId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankverificationId() {
        return bankverificationId;
    }

    public Bankverification bankverificationId(Integer bankverificationId) {
        this.bankverificationId = bankverificationId;
        return this;
    }

    public void setBankverificationId(Integer bankverificationId) {
        this.bankverificationId = bankverificationId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bankverification)) {
            return false;
        }
        return id != null && id.equals(((Bankverification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bankverification{" +
            "id=" + getId() +
            ", bankverificationId=" + getBankverificationId() +
            "}";
    }
}
