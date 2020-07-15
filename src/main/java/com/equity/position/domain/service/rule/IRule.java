package com.equity.position.domain.service.rule;

import com.equity.position.interfaces.dto.TransactionDTO;

/**
 * Created by Administrator on 2020/7/11.
 */
public interface IRule {
    Boolean isActive();
    void addRule(IRule rule);
    void removeRule(IRule rule);
    void displayRule();
    TransactionDTO validate(TransactionDTO transaction);
}
