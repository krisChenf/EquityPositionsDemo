package com.equity.position.domain.service;

import com.equity.position.interfaces.dto.TransactionDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.equity.position.domain.entity.Transaction;
import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.domain.vo.ActionEnum;
import com.equity.position.domain.vo.TransactionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PositionsCalculateImplTest {
    private final static Logger logger = LoggerFactory.getLogger(PositionsCalculateImplTest.class);

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private PositionsCalculate positionsCalculate;
    private Transaction t1;
    private Transaction t2;
    @Before
    public void prepareData() throws Exception {
        t1 = new Transaction();
        t1.setQuantity(BigDecimal.TEN);
        t1.setAction(ActionEnum.INSERT);
        t1.setTradeId(21);
        t1.setVersion(1);
        t1.setType(TransactionTypeEnum.BUY);
        t1.setSecurityCode("RELL");
        Transaction result = repository.save(t1);
        logger.info("prepare data for position calculate test{}" + result);
        t2 = new Transaction();
        t2.setQuantity(BigDecimal.ONE);
        t2.setAction(ActionEnum.UPDATE);
        t2.setTradeId(21);
        t2.setVersion(19);
        t2.setType(TransactionTypeEnum.SELL);
        t2.setSecurityCode("RELL");
        Transaction result2 = repository.save(t2);
        logger.info("prepare data for position calculate test{}" + result2);

    }

    @After
    public void cleanTestData() throws Exception {
        repository.delete(t1);
        repository.delete(t2);
    }

    @Test
    public void calculate() throws Exception {
        TransactionDTO dto = new TransactionDTO();
        BeanUtils.copyProperties(t2,dto);
        Map<String, Object>result = positionsCalculate.calculate(dto);
        int quantity = (int)result.get("quantity");
        Assert.assertTrue(quantity == 9 );
        Assert.assertTrue("RELL".equals(result.get("securityCode")));
    }
}