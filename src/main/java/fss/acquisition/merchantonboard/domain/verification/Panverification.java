package fss.acquisition.merchantonboard.domain.verification;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "panverification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Panverification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "panverificationid")
    private String panverificationId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPanverificationId() {
        return panverificationId;
    }

    public Panverification panverificationId(String panverificationId) {
        this.panverificationId = panverificationId;
        return this;
    }

    public void setPanverificationId(String panverificationId) {
        this.panverificationId = panverificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panverification)) {
            return false;
        }
        return id != null && id.equals(((Panverification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Panverification{" +
            "id=" + getId() +
            ", panverificationId=" + getPanverificationId() +
            "}";
    }
}
