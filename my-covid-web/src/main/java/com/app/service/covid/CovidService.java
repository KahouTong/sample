package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	CovidCasesDesc addCovid(String desc);

	List<CovidCasesDesc> getCovidDesc();

	int deleteCovid(long id) throws Exception;

	CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc) throws Exception;

	CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc) throws Exception;

	int deleteCovidSoap(String desc);


}
