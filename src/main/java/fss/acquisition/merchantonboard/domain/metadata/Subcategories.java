package fss.acquisition.merchantonboard.domain.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


@Entity
@Table(name = "subcategories")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Subcategories {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id",unique = true)
    private Integer subcategorieid;

    @Column(name = "description")
    private String description;

    @Column(name = "categorieid")
    private Integer categorieid;

    @Column(name = "merchantcategorie")
    private Integer merchantCategorie;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categorieid",insertable = false,updatable = false,referencedColumnName = "id")
    private Categories categories;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSubcategorieid() {
        return subcategorieid;
    }

    public void setSubcategorieid(Integer subcategorieid) {
        this.subcategorieid = subcategorieid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(Integer categorieid) {
        this.categorieid = categorieid;
    }

    public Integer getMerchantCategorie() {
        return merchantCategorie;
    }

    public void setMerchantCategorie(Integer merchantCategorie) {
        this.merchantCategorie = merchantCategorie;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Subcategories{" +
                ", subcategorieid=" + subcategorieid +
                ", description='" + description + '\'' +
                ", categorieid=" + categorieid +
                ", merchantCategorie=" + merchantCategorie +
                '}';
    }
}
