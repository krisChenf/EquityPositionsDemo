package com.equity.position.interfaces.controller;

import com.equity.position.domain.service.PositionsCalculate;
import com.equity.position.domain.service.rule.IRule;
import com.equity.position.domain.vo.TransactionVO;
import com.equity.position.interfaces.dto.TransactionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * Equity Positions maintain controller
 * Created by chenfei on 2020/7/12.
 */

@RequestMapping("/equity/transaction")
@RestController
public class PositionCalcuteController {
    private final static Logger logger = LoggerFactory.getLogger(PositionCalcuteController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired()
    @Qualifier("RuleComposite")
    private IRule rules;
    @Autowired
    PositionsCalculate calculator;

    @RequestMapping("/index")
    public ModelAndView toSearchPage() {
        logger.info("Equity Positions maintain index...");
        return new ModelAndView("index");
    }
    @RequestMapping("/input")
    public String toSearchPage(@Validated TransactionVO transactionVO) throws JsonProcessingException {
        logger.info("Equity Positions input...{}",transactionVO.toString());
        rules.validate(TransactionDTO.from(transactionVO));
        Map<String,Object> map = calculator.calculate(TransactionDTO.from(transactionVO));
        map.put("errorCode", HttpStatus.OK.value());
        return objectMapper.writeValueAsString(map);
    }



}