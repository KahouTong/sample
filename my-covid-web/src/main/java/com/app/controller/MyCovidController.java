package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.error.ControllerException;
import com.app.model.CovidCasesArea;
import com.app.service.covid.api.CovidAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyCovidController {

	private static final String GET_MY_LAST_5_COVID = "/covid/get5/my";

	private static final String GET_MY_LAST_5_COVID_PARAM = "/covid/get5/withsize";
	
	@Autowired
	CovidAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_MY_LAST_5_COVID)
	public List<CovidCasesArea> getLast5Records() throws ControllerException {
		log.info("getLast5Records() started");
		List<CovidCasesArea> cList = null;
		try {
			cList = covidMiningAPITotalCases.getLast5RecordsMY();
	} catch (Exception e) {
		//   Auto-generated catch block
		log.error(" getLatest() exception " + e.getMessage());
		throw new ControllerException(GET_MY_LAST_5_COVID_PARAM, e.getMessage());
	}
		
		for (CovidCasesArea c : cList) {
			log.info("c at Controller Date-->" + c.getDate() + " Cases-->" + c.getCases());	
		}
		log.info("getLast5Records() ends. It supposes to return last 5 records from listLast5Records(). (CovidCasesRepository)");
		
		return cList ;
	}
	
	@GetMapping(GET_MY_LAST_5_COVID_PARAM)
	public List<CovidCasesArea> getLast5RecordsWithParam(@RequestParam int size) throws ControllerException {
		log.info("getLast5RecordsWithParam() started size ={}", size);
		List<CovidCasesArea> cList = null;
		try {
			cList = covidMiningAPITotalCases.getLast5RecordsMYWithSize(size);
	} catch (Exception e) {
		//   Auto-generated catch block
		log.error(" getLatest() exception " + e.getMessage());
		throw new ControllerException(GET_MY_LAST_5_COVID_PARAM, e.getMessage());
	}
		log.info("getLast5RecordsWithParam() ends. It supposes to return last 5 records from listLast5Records(). (CovidCasesRepository)");
		return cList;
	}
}
