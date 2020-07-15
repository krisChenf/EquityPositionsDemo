package com.equity.position.domain.service.rule;

import com.equity.position.interfaces.dto.TransactionDTO;
import com.equity.position.domain.entity.Transaction;
import com.equity.position.domain.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/11.
 */

@Service("RuleComposite")
public class RuleComposite implements IRule{
    private final static Logger logger = LoggerFactory.getLogger(RuleComposite.class);
    private List<IRule> ruleList = new ArrayList<>();
    @Autowired
    private TransactionRepository repository;

    @Autowired(required = false)
    public void setRules(List<IRule> rules){
        for (IRule r:rules) {
            if(!r.getClass().equals(RuleComposite.class));
            ruleList.add(r);
        }
    }

    @Override
    public Boolean isActive() {
        return true;
    }

    @Override
    public void addRule(IRule rule) {
        ruleList.add(rule);
    }

    @Override
    public void removeRule(IRule rule) {
        ruleList.remove(rule);
    }

    @Override
    public void displayRule() {
    }

    @Override
    public TransactionDTO validate(TransactionDTO transaction) {
        logger.info("start CompositeRule...");
        for (IRule r:ruleList) {
            if(r.isActive())
                r.validate(transaction);
        }
        Transaction trnsctn = repository.save(Transaction.from(transaction));
        logger.info("transaction is saved : {}",trnsctn);
        return TransactionDTO.from(trnsctn);
    }

}
