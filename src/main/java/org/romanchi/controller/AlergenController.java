package org.romanchi.controller;

import org.romanchi.model.AlergenItem;
import org.romanchi.model.CosmeticItem;
import org.romanchi.model.dto.AlergenItemDTO;
import org.romanchi.service.AlergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/alergens")
public class AlergenController {

    @Autowired
    AlergenService alergenService;

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Set<AlergenItemDTO> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "DESC") String sort){
        Sort.Direction direction = (sort.equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC: Sort.Direction.ASC;
        return alergenService.findAll(PageRequest.of(page,pageSize, new Sort(direction, "id"))).stream().map(AlergenItemDTO::new).collect(Collectors.toSet());
    }

    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AlergenItemDTO addAlergenItem(@RequestBody AlergenItemDTO alergenItemDTO){
        AlergenItem alergenItem = alergenItemDTO.toEntity();
        return new AlergenItemDTO(alergenService.addAlergenItem(alergenItem));
    }

    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlergenItem(@PathVariable long id){
        alergenService.deleteById(id);
    }
}
