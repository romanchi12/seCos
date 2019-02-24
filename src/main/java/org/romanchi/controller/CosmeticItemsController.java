package org.romanchi.controller;

import org.romanchi.model.CosmeticItem;
import org.romanchi.model.dto.CosmeticItemDTO;
import org.romanchi.service.CosmeticItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cosmeticitems")
public class CosmeticItemsController {

    @Autowired
    CosmeticItemService cosmeticItemService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CosmeticItemDTO getById(@PathVariable long id){
        return new CosmeticItemDTO(cosmeticItemService.findById(id));
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Set<CosmeticItemDTO> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "DESC") String sort){
        Sort.Direction direction = (sort.equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC: Sort.Direction.ASC;
        Set<CosmeticItem> cosmeticItems = cosmeticItemService.findAll(PageRequest.of(page,pageSize, new Sort(direction, "id")));
        return cosmeticItems.stream()
                .map(CosmeticItemDTO::new)
                .collect(Collectors.toSet());
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CosmeticItemDTO addCosmeticItem(@RequestBody CosmeticItemDTO cosmeticItemDTO){
        CosmeticItem cosmeticItem = cosmeticItemDTO.toEntity();
        return new CosmeticItemDTO(cosmeticItemService.add(cosmeticItem));
    }


}
