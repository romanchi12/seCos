package org.romanchi.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.romanchi.exceptions.NotFoundException;
import org.romanchi.model.AlergenItem;
import org.romanchi.model.CosmeticItem;
import org.romanchi.model.dto.CosmeticItemDTO;
import org.romanchi.repository.CosmeticItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CosmeticItemService {

    @Autowired
    CosmeticItemRepository cosmeticItemRepository;
    @Autowired
    AlergenService alergenService;

    public boolean existByBarcode(String barcode){
        return cosmeticItemRepository.existsByBarcode(barcode);
    }

    public CosmeticItem findById(long id){
        return cosmeticItemRepository.findById(id).orElseThrow(()->
                new NotFoundException("No cosmetic item by given id"));
    }

    public Set<CosmeticItem> findAll(PageRequest pageRequest) {
        return new HashSet<>(cosmeticItemRepository.findAll(pageRequest).getContent());
    }

    public CosmeticItem add(CosmeticItem cosmeticItem) {
        if(cosmeticItem.getId() > 0){
            throw new InvalidParameterException("Id must be empty. To update use PATCH");
        }
        if(existByBarcode(cosmeticItem.getBarcode())){
            throw new InvalidParameterException("Cosmetic item with such barcode already exists");
        }

        if(cosmeticItem.getAlergenItems()!=null){
            cosmeticItem.setAlergenItems(cosmeticItem.getAlergenItems().stream().map(alergenItem -> alergenService.findById(alergenItem.getId())).collect(Collectors.toSet()));
        }

        return cosmeticItemRepository.save(cosmeticItem);
    }

    public CosmeticItem findByBarcode(String barcode) {
        return cosmeticItemRepository.findByBarcode(barcode).orElseThrow(()->new NotFoundException("No cosmetic item found by given barcode"));
    }

    public void loadIntoDB() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Roman\\IdeaProjects\\рфсл\\src\\main\\resources\\data.csv"));

        ) {
            List<String> lines = ((BufferedReader) reader).lines().collect(Collectors.toList());
            for (String line: lines) {
                String row[] = line.split(";");
                System.out.println("Barcode: " + row[0]);
                System.out.println("Cosmetic: " + row[1]);
                System.out.println("Alergens: " + row[2]);
                List<String> alergens = Arrays.asList(row[2].split(","));
                CosmeticItem cosmeticItem = new CosmeticItem();
                cosmeticItem.setBarcode(row[0]);
                cosmeticItem.setName(row[1]);
                cosmeticItem.setAlergenItems(alergens.stream().map(alergen->{
                   AlergenItem alergenItem = new AlergenItem();
                   alergenItem.setDisease("Disease");
                   alergenItem.setName(alergen);
                   return alergenItem;
                }).collect(Collectors.toSet()));
                cosmeticItemRepository.save(cosmeticItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
