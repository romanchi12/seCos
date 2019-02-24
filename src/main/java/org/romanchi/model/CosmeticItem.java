package org.romanchi.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="cosmetic_item")
public class CosmeticItem {
    @Id
    @Column(name="cosmetic_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cosmetic_item_name")
    private String name;

    @Column(name = "barcode")
    private String barcode;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cosmetic_alergen"
            , joinColumns = @JoinColumn(name = "cosmetic_item_id", referencedColumnName = "cosmetic_item_id")
            ,inverseJoinColumns = @JoinColumn(name ="alergen_item_id", referencedColumnName = "alergen_item_id"))
    private Set<AlergenItem> alergenItems;

    public CosmeticItem() {
    }

    public CosmeticItem(String cosmeticName, Set<AlergenItem> alergenItems, String barcode) {
        this.name = cosmeticName;
        this.alergenItems = alergenItems;
        this.barcode = barcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String cosmeticName) {
        this.name = cosmeticName;
    }

    public Set<AlergenItem> getAlergenItems() {
        return alergenItems;
    }

    public void setAlergenItems(Set<AlergenItem> alergenItems) {
        this.alergenItems = alergenItems;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmeticItem that = (CosmeticItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
