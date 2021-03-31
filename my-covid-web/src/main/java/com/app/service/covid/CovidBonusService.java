package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {

	List<CovidCasesBonus> getCovidBonus();
	
	CovidCasesBonus addCovid(String bonus);
	
	int deleteCovid(long id) throws Exception;

	CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus) throws Exception;

	CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus) throws Exception;
	
	int deleteCovidSoap(String desc);

	List<String> deleteCovidDupl();
}
