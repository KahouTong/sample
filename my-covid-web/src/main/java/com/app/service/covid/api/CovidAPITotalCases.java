package com.app.service.covid.api;

import java.util.List;

import com.app.model.CovidCasesArea;

public interface CovidAPITotalCases {

	List<CovidCasesArea> getLast5RecordsMY() throws Exception;

	String getTotalfromDB() throws Exception;

	List<CovidCasesArea> getLast5RecordsMYWithSize(int size) throws Exception;
}
