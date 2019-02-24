package org.romanchi.repository;


import org.romanchi.model.AlergenItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlergenRepository extends JpaRepository<AlergenItem, Long> {
    Page<AlergenItem> findAll(Pageable of);
}
