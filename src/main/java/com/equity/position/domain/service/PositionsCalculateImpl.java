package com.equity.position.domain.service;

import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.domain.vo.TransactionTypeEnum;
import com.equity.position.interfaces.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.equity.position.domain.vo.ActionEnum.CANCEL;

/**
 * Created by Administrator on 2020/7/12.
 */

@Service
public class PositionsCalculateImpl implements PositionsCalculate{
    private final static Logger logger = LoggerFactory.getLogger(PositionsCalculateImpl.class);
    @Autowired
    private TransactionRepository repository;

    @Override
    public Map<String, Object> calculate(TransactionDTO transaction) {
        logger.info("start transaction quantity calculate");
        List<Map<String,Object>> list = repository.findAllQuantityBySecurityCode(transaction.getSecurityCode(),CANCEL.name());
        Map<String,Object>result = new HashMap<>();
        result.put("securityCode",transaction.getSecurityCode());
        BigDecimal buy = BigDecimal.ZERO;
        BigDecimal sell = BigDecimal.ZERO;
        for(Map<String,Object>map : list){
            if (TransactionTypeEnum.BUY.name().equals(map.get("type"))){
                buy = (BigDecimal)map.get("quantity");
            }
            if (TransactionTypeEnum.SELL.name().equals(map.get("type"))){
                sell = (BigDecimal)map.get("quantity");
            }
        }
        result.put("quantity",buy.subtract(sell));
        return result;
    }
}
