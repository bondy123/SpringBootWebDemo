package com.research.service;

import com.research.mapper.PoliticsMapper;
import com.research.model.Pagination;
import com.research.model.Politics;
import com.research.repository.PoliticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsService {
    @Autowired
    PoliticsMapper politicsMapper;

    public int insertPolitics(Politics politics) {
        return politicsMapper.insertPolitics(politics);
    }

    public int deletePoliticsById(Integer id) {
        return politicsMapper.deletePoliticsById(id);
    }

    public int updatePoliticsById(Politics politics) {
        return politicsMapper.updatePoliticsById(politics);
    }

    public Politics getPoliticsById(Integer id) {
        return politicsMapper.getPoliticsById(id);
    }

    public List<Politics> getPolitics(Pagination pagination) {
        if(pagination.getPageIndex() == null) {
            pagination.setPageIndex(0);
        }
        if(pagination.getPageSize() == null) {
            pagination.setPageSize(10);
        }
        pagination.setPageIndex(pagination.getPageIndex() * pagination.getPageSize());
        return politicsMapper.getPolitics(pagination);
    }

    public Integer getPoliticsCounts() {
        return politicsMapper.getPoliticsCounts();
    }

    public List<Politics> queryProjects(String date, String state) {
        return politicsMapper.queryProjects(date, state);
    }

}
