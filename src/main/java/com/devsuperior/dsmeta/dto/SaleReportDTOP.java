package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleReportDTOP {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String name;

    public SaleReportDTOP(Long id, Double amount, LocalDate date, String name) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.name = name;
    }
    public SaleReportDTOP (Sale x){
        this.id = x.getId();
        this.amount = x.getAmount();
        this.date = x.getDate();
        this.name = x.getSeller().getName();
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
