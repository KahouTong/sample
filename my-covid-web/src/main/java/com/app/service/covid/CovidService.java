package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	CovidCasesDesc addCovid(String desc);

	List<CovidCasesDesc> getCovidDesc();

	int deleteCovid(long id);

	CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc);

	CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc);

	int deleteCovidSoap(String desc);


}
