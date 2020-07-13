package com.equity.position.domain.service.rule;

import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.domain.vo.ActionEnum;
import com.equity.position.interfaces.dto.TransactionDTO;
import com.equity.position.domain.exception.BizErrorCodeEnum;
import com.equity.position.domain.exception.RuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by chenfei on 2020/7/11.
 *
 * NSERT will always be 1st version of a Trade,
 * CANCEL will always be last version of Trade.
 */

@Service("InsertCancelRule")
public class InsertCancelRule extends AbstractRule {
    private final static Logger logger = LoggerFactory.getLogger(InsertCancelRule.class);
    @Value("${business.rules.insertCancelRule.isActive}")
    private boolean isActive;
    @Autowired
    private TransactionRepository repository;
    @Override
    public void displayRule() {
        logger.info(" INSERT will always be 1st version of a Trade, CANCEL will always be last version of Trade ");
    }

    @Override
    public void validate(TransactionDTO transaction) {
        if(transaction.getAction().equals(ActionEnum.INSERT)){
            Integer minVersion = repository.findMinVersionByTradeId(transaction.getTradeId());
            if(null != minVersion && transaction.getVersion() > minVersion)
            throw new RuleException(BizErrorCodeEnum.INSERT_CANCEL_ERROR);
        }
        if(transaction.getAction().equals(ActionEnum.CANCEL)){
            Integer maxVersion = repository.findMaxVersionByTradeId(transaction.getTradeId());
            if(null != maxVersion && transaction.getVersion() < maxVersion)
            throw new RuleException(BizErrorCodeEnum.INSERT_CANCEL_ERROR);
        }
    }

    @Override
    public Boolean isActive() {
        return isActive;
    }
}
