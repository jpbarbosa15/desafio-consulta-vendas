package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller " +
      "WHERE obj.date >= :dataInicial " +
      "AND obj.date <= :dataFinal " +
      "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
      countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller " +
       "WHERE obj.date >= :dataInicial " +
       "AND obj.date <= :dataFinal " +
       "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Sale> searchReport(String name, LocalDate dataInicial, LocalDate dataFinal, Pageable pageable);


    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO (obj.seller.name, SUM(obj.amount))" +
            "FROM Sale obj " +
            "WHERE obj.date >= :minDate AND obj.date <= :maxDate " +
            "GROUP BY obj.seller.name")
    List<SaleSummaryDTO> searchSummary(LocalDate minDate, LocalDate maxDate);




}
