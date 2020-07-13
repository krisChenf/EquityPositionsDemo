package com.equity.position.domain.service.rule;

import com.equity.position.domain.entity.Transaction;
import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.domain.vo.ActionEnum;
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
 * For UPDATE, SecurityCode or Quantity or Buy/Sell can change
 */

@Service("UpdateRule")
public class UpdateRule extends AbstractRule {
    private final static Logger logger = LoggerFactory.getLogger(UpdateRule.class);

    @Autowired
    private TransactionRepository repository;
    @Value("${business.rules.updateRule.isActive}")
    private boolean isActive;
    @Override
    public void displayRule() {
        logger.info("For UPDATE, SecurityCode or Quantity or Buy/Sell can change");
    }

    @Override
    public Boolean isActive() {
        return isActive;
    }

    @Override
    public void validate(TransactionDTO transaction) {
        logger.info("start updateRule validate...");
        List<Transaction> list = repository.findByTradeId(transaction.getTradeId());
        if(!list.isEmpty()){
            if(!transaction.getAction().equals(ActionEnum.UPDATE)){
                boolean flag = false;
                for (Transaction t:list) {
                    if(t.getAction().equals(ActionEnum.UPDATE)){
                        flag = true;
                        break;
                    }
                }
                if(!flag) return;
            }
            for (Transaction t:list) {
                if( 0 == t.getQuantity().compareTo(transaction.getQuantity()) &&
                        t.getSecurityCode().equals(transaction.getSecurityCode()) &&
                        t.getType().equals(transaction.getType()))
                    throw new RuleException(BizErrorCodeEnum.UPDATE_ERROR);
            }
        }

    }
}
