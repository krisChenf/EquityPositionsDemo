package com.equity.position.domain.service.rule;

import com.equity.position.domain.entity.Transaction;
import com.equity.position.domain.exception.RuleException;
import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.domain.vo.ActionEnum;
import com.equity.position.domain.vo.TransactionTypeEnum;
import com.equity.position.interfaces.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

/**
 * Created by chenfei on 2020/7/11.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UpdateRuleTest {
    private final static Logger logger = LoggerFactory.getLogger(UpdateRuleTest.class);

    @Autowired
    private TransactionRepository repository;
    @Autowired
    @Qualifier("UpdateRule")
    private IRule updateRule;
    private TransactionDTO t1;
    private TransactionDTO t2;
    @Before
    public void prepareData() throws Exception {
        t1 = new TransactionDTO();
        t1.setQuantity(BigDecimal.TEN);
        t1.setAction(ActionEnum.INSERT);
        t1.setTradeId(111);
        t1.setVersion(0);
        t1.setType(TransactionTypeEnum.BUY);
        t1.setSecurityCode("REL");
        Transaction result = repository.save(Transaction.from(t1));
        logger.info("prepare data for position calculate test{}" + result);

    }


    @Test(expected  = RuleException.class)
    public void testValidate() throws Exception {
        t1.setAction(ActionEnum.UPDATE);
        updateRule.validate(t1);
    }

}