package com.equity.position.domain.repository;

import com.equity.position.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/5.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>,JpaSpecificationExecutor<Transaction> {


    List<Transaction> findByTradeIdAndVersion(@Param("tradeId")int tradeId,@Param("version")int version);

    List<Transaction> findByTradeId(@Param("tradeId")int tradeId);

    @Query(value = "SELECT SUM(t1.quantity) AS quantity, t1.type, t1.security_code AS securityCode FROM t_transactions t1 " +
            " WHERE t1.security_code = :securityCode  AND t1.action != :actionEnum " +
            " GROUP BY t1.type,t1.security_code", nativeQuery = true)
    List<Map<String,Object>> findAllQuantityBySecurityCode(@Param("securityCode")String SecurityCode,@Param("actionEnum")String action);

    @Query(value = "SELECT  Max(t1.version) AS maxVersion FROM t_transactions t1" +
            " WHERE t1.trade_id = :tradeId", nativeQuery = true)
    Integer findMaxVersionByTradeId(@Param("tradeId")int tradeId);

    @Query(value = "SELECT  Min(t1.version) AS minVersion FROM t_transactions t1" +
            " WHERE t1.trade_id = :tradeId", nativeQuery = true)
    Integer findMinVersionByTradeId(@Param("tradeId")int tradeId);


}
