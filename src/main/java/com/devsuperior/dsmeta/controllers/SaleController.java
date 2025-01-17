package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTOP;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTOP>> getReport(@RequestParam(name="name",defaultValue = "")String name,
		@RequestParam(required = false) String minDate,
		@RequestParam(required = false) String maxDate, Pageable pageable) {
			Page<SaleReportDTOP> dto = service.findRelatorio(name, minDate, maxDate,pageable);
			return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryDTO>> getSummary(@RequestParam(required = false) String minDate,@RequestParam(required = false) String maxDate)
	{
		List<SaleSummaryDTO> dto = service.findsummary(minDate,maxDate);
		return ResponseEntity.ok(dto);
	}
}
