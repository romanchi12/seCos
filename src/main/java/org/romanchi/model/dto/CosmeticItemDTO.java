package org.romanchi.model.dto;

import org.romanchi.model.AlergenItem;
import org.romanchi.model.CosmeticItem;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

public class CosmeticItemDTO {

    private long id;

    private String name;

    private String barcode;

    private Set<AlergenItemDTO> alergenItems;

    public CosmeticItemDTO() {
    }

    public CosmeticItemDTO(CosmeticItem cosmeticItem) {
        this.id = cosmeticItem.getId();
        this.name = cosmeticItem.getName();
        this.barcode = cosmeticItem.getBarcode();
        this.alergenItems = (cosmeticItem.getAlergenItems()==null)?null:cosmeticItem.getAlergenItems()
                .stream()
                .map(AlergenItemDTO::new)
                .collect(Collectors.toSet());
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

    public Set<AlergenItemDTO> getAlergenItems() {
        return alergenItems;
    }

    public void setAlergenItems(Set<AlergenItemDTO> alergenItems) {
        this.alergenItems = alergenItems;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public CosmeticItem toEntity(){
        CosmeticItem cosmeticItem = new CosmeticItem();
        cosmeticItem.setId(this.getId());
        cosmeticItem.setName(this.getName());
        cosmeticItem.setBarcode(this.getBarcode());
        cosmeticItem.setAlergenItems((this.alergenItems==null)?null:this.alergenItems.stream()
                .map(AlergenItemDTO::toEntity)
                .collect(Collectors.toSet()));
        return cosmeticItem;
    }
}
