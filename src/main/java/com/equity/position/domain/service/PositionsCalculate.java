package com.equity.position.domain.service;

import com.equity.position.interfaces.dto.TransactionDTO;
import java.util.Map;

/**
 * Created by Administrator on 2020/7/12.
 */

public interface PositionsCalculate {
    Map<String, Object> calculate(TransactionDTO transaction);
}
