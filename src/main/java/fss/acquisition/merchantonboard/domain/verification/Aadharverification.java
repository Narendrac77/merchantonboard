package fss.acquisition.merchantonboard.domain.verification;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


@Entity
@Table(name = "aadharverification")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Aadharverification {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "aadharverificationid")
    private String aadharverificationid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAadharverificationid() {
        return aadharverificationid;
    }

    public void setAadharverificationid(String aadharverificationid) {
        this.aadharverificationid = aadharverificationid;
    }

    @Override
    public String toString() {
        return "Aadharverification{" +
                "id=" + id +
                ", aadharverificationid='" + aadharverificationid + '\'' +
                '}';
    }
}
