package com.equity.position.domain.repository;

import com.equity.position.domain.entity.Transaction;
import com.equity.position.domain.vo.ActionEnum;
import com.equity.position.interfaces.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static com.equity.position.domain.vo.ActionEnum.CANCEL;
import static com.equity.position.domain.vo.TransactionTypeEnum.BUY;
import static com.equity.position.domain.vo.TransactionTypeEnum.SELL;

/**
 * Created by Administrator on 2020/7/15.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TransactionRepositoryTest {
    private final static Logger logger = LoggerFactory.getLogger(TransactionRepositoryTest.class);

    @Autowired
    TransactionRepository repository;
    private TransactionDTO trnsctn;

    @Before
    public void prepareData() throws Exception {
        trnsctn = new TransactionDTO();
        trnsctn.setQuantity(BigDecimal.TEN);
        trnsctn.setAction(ActionEnum.INSERT);
        trnsctn.setTradeId(111);
        trnsctn.setVersion(111);
        trnsctn.setType(BUY);
        trnsctn.setSecurityCode("REL");
        Transaction result = repository.save(Transaction.from(trnsctn));

        trnsctn.setVersion(112);
        trnsctn.setAction(ActionEnum.UPDATE);
        trnsctn.setQuantity(new BigDecimal("222"));
        repository.save(Transaction.from(trnsctn));

        trnsctn.setVersion(113);
        trnsctn.setAction(ActionEnum.UPDATE);
        trnsctn.setQuantity(new BigDecimal("333"));
        repository.save(Transaction.from(trnsctn));

        trnsctn.setVersion(114);
        trnsctn.setAction(ActionEnum.UPDATE);
        trnsctn.setQuantity(new BigDecimal("444"));
        trnsctn.setType(SELL);
        repository.save(Transaction.from(trnsctn));
        logger.info("prepare data for  calculate test{}" + result);

    }

    @Test
    public void findQuantityBySecurityCodeAndType() throws Exception {
        BigDecimal buy = repository.findQuantityBySecurityCodeAndType(trnsctn.getSecurityCode(), BUY.name(), CANCEL.name());
        Assert.assertTrue(0 == buy.compareTo(new BigDecimal(333)));
    }

}