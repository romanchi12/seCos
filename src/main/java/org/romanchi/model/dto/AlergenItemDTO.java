package org.romanchi.model.dto;

import org.romanchi.model.AlergenItem;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AlergenItemDTO {

    private long id;

    private String name;

    private String disease;


    public AlergenItemDTO() {
    }

    public AlergenItemDTO(AlergenItem alergenItem) {
        this.id = alergenItem.getId();
        this.name = alergenItem.getName();
        this.disease = alergenItem.getDisease();
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

    public AlergenItem toEntity(){
        AlergenItem alergenItem = new AlergenItem();
        alergenItem.setId(this.getId());
        alergenItem.setName(this.getName());
        alergenItem.setDisease(this.getDisease());
        return alergenItem;
    }


}
