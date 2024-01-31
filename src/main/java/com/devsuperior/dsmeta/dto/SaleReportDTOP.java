package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleReportDTOP {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String sellerName;

    public SaleReportDTOP(Long id, LocalDate date,Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = name;
    }
    public SaleReportDTOP (Sale x){
        this.id = x.getId();
        this.amount = x.getAmount();
        this.date = x.getDate();
        this.sellerName = x.getSeller().getName();
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
        return sellerName;
    }
}
