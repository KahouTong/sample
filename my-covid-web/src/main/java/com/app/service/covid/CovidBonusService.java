package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {

	List<CovidCasesBonus> getCovidBonus();
	
	CovidCasesBonus addCovid(String bonus);
	
	int deleteCovid(long id);

	CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus);

	CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus);
	
	int deleteCovidSoap(String desc);

	List<String> deleteCovidDupl();
}
