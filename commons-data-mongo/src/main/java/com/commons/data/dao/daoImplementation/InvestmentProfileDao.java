package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.InvestmentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentProfileDao extends MongoRepository<InvestmentProfile, Long> {
    List<InvestmentProfile> findByHierarchicalcodeContaining(String referalCode);
    List<InvestmentProfile> findByRecstatusAndHierarchicalcodeContaining(String status, String referalCode);
}
