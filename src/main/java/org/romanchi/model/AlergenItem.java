package org.romanchi.model;

import javax.persistence.*;

@Entity
@Table(name = "alergen_item")
public class AlergenItem {
    @Id
    @Column(name = "alergen_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alerrgen_name")
    private String name;

    @Column(name = "alergen_caused_disease")
    private String disease;

    public AlergenItem() {
    }

    public AlergenItem(String name, String disease) {
        this.name = name;
        this.disease = disease;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
