package com.equity.position.domain.vo;

/**
 * Created by Administrator on 2020/7/12.
 */
public enum ActionEnum {
   INSERT("INSERT"),UPDATE("UPDATE"),CANCEL("CANCEL");
   private String name;
   ActionEnum(String name) {
      this.name = name;
   }

}
