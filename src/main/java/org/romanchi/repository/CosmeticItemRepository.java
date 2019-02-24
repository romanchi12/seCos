package org.romanchi.repository;

import jdk.nashorn.internal.runtime.options.Option;
import org.romanchi.model.CosmeticItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CosmeticItemRepository extends JpaRepository<CosmeticItem, Long> {
    Page<CosmeticItem> findAll(Pageable pageable);
    boolean existsByBarcode(String barcode);
    Optional<CosmeticItem> findByBarcode(String barcode);
}
