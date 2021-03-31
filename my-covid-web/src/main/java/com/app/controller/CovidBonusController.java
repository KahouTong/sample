package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {

	private final static String GET_MY_BONUS = "/covid/get/bonus";

	private final static String PUT_API = "/covid/put/bonus";
	
	private final static String POST_COVID = "/covid/post/bonus";
	
	private final static String DELETE_COVID = "/covid/delete/bonus";
	
	private final static String ADD_COVID = "/covid/add/bonus";
	
	private final static String DELETE_COVID_SOAPUI = "/covid/delete/soap/bonus";
	
	private final static String DELETE_DUPLICATE_COVID = "/covid/delete/duplicate/bonus";

	@Autowired
	private CovidBonusService covidBonusService;

	// TODO: Practical Bonus Desc Final
	// Objective: to create a set of spring and hibernate services to retrieve data from a new table call "trx_covid_cases_bonus"
	
	// 1. Complete the CovidCasesBonusEntity.java and auto generate a table on DB
	// Enable the line below from application.properties to create new bonus table
	// # spring.jpa.hibernate.ddl-auto=update
	// Then restart application and table being created on the log
	// CREATE TABLE / PRIMARY KEY will create implicit index "trx_covid_cases_bonus_pkey" for table "trx_covid_cases_bonus"
	
	// 2. Insert the dummy data into trx_covid_cases_bonus using PGAdmin
	
	// 3. Complete the method below to return list of CovidCasesBonus from table trx_covid_cases_bonus
	// Files to be modified as below
	
	// CovidCasesBonus - Java POJO 
	// CovidCasesBonusEntity - DB Entity File
	// CovidAreaBonusMapper - Mapper from Java Entity file above to POJO 
	// CovidCasesBonusRepository - Spring JPA Repository or library to query DB. i.e. FindAll() method
	// CovidBonusService - Interface for the service below
	// CovidBonusServiceImpl - Implementation of the service between controller and repo
	
	
	@GetMapping(GET_MY_BONUS)
	List<CovidCasesBonus> bonus() throws Exception {
		log.info("bonus() started");
		List<CovidCasesBonus> covidCasesBonus = null;
		try {
			covidCasesBonus = covidBonusService.getCovidBonus();
			if (covidCasesBonus == null) {
				throw new Exception("No bonus yet");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("bonus() exception " + e.getMessage());
			throw new Exception(e);
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_COVID)
	CovidCasesBonus addCovidBonus(@RequestParam(required = true) String bonus) throws Exception {
		log.info("addCovidBonus() started={}", bonus);

		CovidCasesBonus covidCasesBonus = null;
		try {

			if (bonus == null || bonus.equals("undefined") || bonus.equals(""))  {
				throw new NullPointerException(ADD_COVID + ", bonus is null or empty");
			}
			covidCasesBonus = covidBonusService.addCovid(bonus);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("addCovidBonus() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return covidCasesBonus;
	}
	
	@DeleteMapping(DELETE_COVID)
	int deleteCovidBonus(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovidBonus() started id={}", id);

		return covidBonusService.deleteCovid(id);
	}
	
	@PutMapping(PUT_API)
	CovidCasesBonus putCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {
		
		// complete the implementation below

		
		// return should be the Saved CovidCasesDesc with values
		return covidBonusService.putCovid(covidCasesBonus);
	}
	
	
	@PostMapping(POST_COVID)
	CovidCasesBonus postCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {

	// return should be the Saved CovidCasesDesc with values
		return covidBonusService.postCovid(covidCasesBonus);
	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI)

	int deleteCovidSoapBonus(@RequestParam(required = true) String bonus) throws Exception {

		return covidBonusService.deleteCovidSoap(bonus);
	}
	
	@DeleteMapping(DELETE_DUPLICATE_COVID)

	List<String> findDuplicateNdelete() throws Exception {
		log.info("findDuplicateNdelete() started");
		
		// complete the implementation below
		// ensure logic related to repo move to service implementation
		List<String> e = covidBonusService.deleteCovidDupl();
//		
		for (String s: e) {
			log.info ("Duplicate value found on Description Table--->" + s);
			covidBonusService.deleteCovidSoap(s);
			log.info ("Value Deleted--->" + s);
		}
//		
		log.info("findDuplicateNdelete() ended");
//		return null;
		return e;
	}
}
