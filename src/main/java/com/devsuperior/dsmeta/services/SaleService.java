package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
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

	public Page<SaleReportDTOP> findRelatorio(String name, String dataInicial, String dataFinal, Pageable pageable){
		LocalDate datain = LocalDate.parse(dataInicial);
		LocalDate datafin = LocalDate.parse(dataFinal);
		Page<Sale> result = repository.searchReport(name,datain,datafin,pageable);
		Page<SaleReportDTOP> dto = result.map(x -> new SaleReportDTOP(x));
		return dto;
	}
}
