package com.equity.position.domain.vo;

/**
 * Created by Administrator on 2020/7/12.
 */
public enum TransactionTypeEnum {
   BUY("BUY"),SELL("SELL");
   private String name;
   TransactionTypeEnum(String name) {
      this.name = name;
   }
}
