package com.equity.position.domain.service.rule;

import com.equity.position.domain.entity.Transaction;
import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.interfaces.dto.TransactionDTO;
import com.equity.position.domain.exception.BizErrorCodeEnum;
import com.equity.position.domain.exception.RuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by chenfei on 2020/7/11.
 *
 * INSERT / UPDATE / CANCEL are actions on a Trade
 * (with same trade id but different version)
 */

@Service("versionRule")
public class VersionRule extends AbstractRule {
    private final static Logger logger = LoggerFactory.getLogger(VersionRule.class);
    @Value("${business.rules.versionRule.isActive}")
    private boolean isActive;
    @Autowired
    private TransactionRepository repository;
    @Override
    public void displayRule() {
    }

    @Override
    public TransactionDTO validate(TransactionDTO transaction) {
        logger.info("start versionRule validate....");
        List<Transaction> list = repository.findByTradeIdAndVersion(transaction.getTradeId(),transaction.getVersion());
        if(!list.isEmpty()){
            throw new RuleException(BizErrorCodeEnum.SAME_VERSION_ERROR);
        }
        return transaction;
    }

    @Override
    public Boolean isActive() {
        return isActive;
    }


}
