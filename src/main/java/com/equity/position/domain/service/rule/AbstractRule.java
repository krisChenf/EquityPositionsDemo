package com.equity.position.domain.service.rule;

import com.equity.position.interfaces.dto.TransactionDTO;

/**
 * Created by Administrator on 2020/7/11.
 */
public class AbstractRule implements IRule {
    @Override
    public Boolean isActive() {
        return true;
    }

    @Override
    public void addRule(IRule rule) {

    }

    @Override
    public void removeRule(IRule rule) {

    }

    @Override
    public void displayRule() {

    }

    @Override
    public  void validate(TransactionDTO transaction) {

    }
}
