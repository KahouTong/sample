package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

//TODO: Practical bonus final
//complete this as Dependencies Injection Service
@Service
@Slf4j
@Transactional
public class CovidBonusServiceImpl implements CovidBonusService {

	// hint
	// the method is similar to getCovidDesc() CovidServiceImpl file
	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	@Override
	public List<CovidCasesBonus> getCovidBonus() {
		log.info("getCovidBonus started");
		
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<CovidCasesBonus>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("entity description={}", entity.getDescription());
			}
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}
		log.info("getCovidBonus ended --> covidCasesBonusList",covidCasesBonusList);
		return covidCasesBonusList;

	}

	@Override
	public CovidCasesBonus addCovid(String bonus) {
		log.info("addCovidBonus started");
		CovidCasesBonus covidCasesBonus = null;
		CovidCasesBonusEntity covidAreaBonusEntity = new CovidCasesBonusEntity();

		 covidAreaBonusEntity.setDescription(bonus);

		 CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);

		 CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

		 covidCasesBonus = mapper.asResource(savedEntity);
		return covidCasesBonus;
	}

	@Override
	public int deleteCovid(long id) throws Exception {
		log.info("deleteCovidBonus started");
		
		try {

			Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

			log.info("Entity found == " + entityOptional.isPresent());

			if (entityOptional.isPresent()) {
				CovidCasesBonusEntity covidAreaBonusEntity = entityOptional.get();
				covidCasesBonusRepository.delete(covidAreaBonusEntity);
				return 1;
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return 0;
	}

	@Override
	public CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("putCovidBonus() started, covidCasesDesc={}", covidCasesBonus);

		try {
			CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
			CovidCasesBonusEntity covidAreaBonusEntity = mapper.asEntity(covidCasesBonus);
			CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
			covidCasesBonus = mapper.asResource(savedEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		log.info("putCovidbonus() ends, covidCasesBonusSaved={}", covidCasesBonus);
		return covidCasesBonus;
	}

	@Override
	public CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		try {
			// complete the implementation below
			CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
			CovidCasesBonusEntity covidAreaBonusEntity = mapper.asEntity(covidCasesBonus);
			CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
			covidCasesBonus = mapper.asResource(savedEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		log.info("postCovidBonus() ends, covidCasesBonusSaved={}", covidCasesBonus);
		return covidCasesBonus;
	}

	@Override
	public int deleteCovidSoap(String bonus) {
		log.info("deleteCovidSoapBonus() started bonus={}", bonus);
		
		// complete the implementation below
		int deleted = covidCasesBonusRepository.deleteDescWithCondition(bonus);
		
		log.info("deleteCovidSoapBonus() ended deleted={}", deleted);
		return deleted;
	}

	@Override
	public List<String> deleteCovidDupl() {
		List<String> temp1 = new ArrayList<String>();
		temp1  = covidCasesBonusRepository.findDuplicate();
		log.info("temp1={}",temp1);
//		for (String f:temp1) 
//		{
//		covidCasesBonusRepository.deleteDescWithCondition(f);
//		}
//		log.info("after removed dupli temp1={}",temp1);

		return temp1;
	}
}
