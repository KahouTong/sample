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

//  Practical bonus final
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
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<>();
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
	public int deleteCovid(long id){
		log.info("deleteCovidBonus started");
		
			Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

			log.info("Entity found == " + entityOptional.isPresent());

			if (entityOptional.isPresent()) {
				CovidCasesBonusEntity covidAreaBonusEntity = entityOptional.get();
				covidCasesBonusRepository.delete(covidAreaBonusEntity);
				return 1;
			}
		return 0;
	}

	@Override
	public CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus){
		log.info("putCovidBonus() started, covidCasesDesc={}", covidCasesBonus);

			CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
			CovidCasesBonusEntity covidAreaBonusEntity = mapper.asEntity(covidCasesBonus);
			CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
			covidCasesBonus = mapper.asResource(savedEntity);

		log.info("putCovidbonus() ends, covidCasesBonusSaved={}", covidCasesBonus);
		return covidCasesBonus;
	}

	@Override
	public CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus){
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);

			// complete the implementation below
			CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
			CovidCasesBonusEntity covidAreaBonusEntity = mapper.asEntity(covidCasesBonus);
			CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
			covidCasesBonus = mapper.asResource(savedEntity);

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
		List<String> temp1  = covidCasesBonusRepository.findDuplicate();
		log.info("temp1={}",temp1);

		return temp1;
	}
}
