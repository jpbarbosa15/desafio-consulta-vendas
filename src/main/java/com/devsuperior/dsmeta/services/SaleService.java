package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTOP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTOP> findRelatorio(String name, String minDate, String maxDate, Pageable pageable){
			LocalDate startDate = null;
			LocalDate endDate = null;

			if (minDate != null) {
				startDate = LocalDate.parse(minDate);
			} else {
				startDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L);
			}

			if (maxDate != null) {
				endDate = LocalDate.parse(maxDate);
			} else {
				endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			}

			Page<Sale> result = repository.searchReport(name, startDate, endDate, pageable);
			Page<SaleReportDTOP> dto = result.map(x -> new SaleReportDTOP(x));
			return dto;

	}
}
