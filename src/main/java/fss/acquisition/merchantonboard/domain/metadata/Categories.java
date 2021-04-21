package fss.acquisition.merchantonboard.domain.metadata;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id",unique = true)
    private Integer categorieid;

    @Column(name = "description",unique = true)
    private String description;

    @Column(name = "mcccode")
    private Integer mcccode;

    @OneToMany(mappedBy = "categories")
    @JsonIgnoreProperties(value = {"categories"},allowSetters = true)
    private Set<Subcategories> subcategories = new HashSet<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(Integer categorieid) {
        this.categorieid = categorieid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMcccode() {
        return mcccode;
    }

    public void setMcccode(Integer mcccode) {
        this.mcccode = mcccode;
    }

    @Override
    public String toString() {
        return "Categories{" +
                ", categorieid=" + categorieid +
                ", description='" + description + '\'' +
                '}';
    }
}
