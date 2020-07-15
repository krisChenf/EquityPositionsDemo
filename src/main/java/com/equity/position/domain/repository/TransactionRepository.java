package com.equity.position.domain.repository;

import com.equity.position.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2019/12/5.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>,JpaSpecificationExecutor<Transaction> {

    List<Transaction> findByTradeIdAndVersion(@Param("tradeId")int tradeId,@Param("version")int version);

    List<Transaction> findByTradeId(@Param("tradeId")int tradeId);

    @Query(value = "SELECT  Max(t1.version) AS maxVersion FROM t_transactions t1" +
            " WHERE t1.trade_id = :tradeId", nativeQuery = true)
    Integer findMaxVersionByTradeId(@Param("tradeId")int tradeId);

    @Query(value = "SELECT  Min(t1.version) AS minVersion FROM t_transactions t1" +
            " WHERE t1.trade_id = :tradeId", nativeQuery = true)
    Integer findMinVersionByTradeId(@Param("tradeId")int tradeId);

    @Query(value = "SELECT quantity FROM t_transactions t1 WHERE t1.security_code = :securityCode  " +
        "AND t1.TYPE= :transactionType AND t1.action != :actions and t1.version in " +
        "(SELECT max (version) FROM t_transactions T WHERE T.security_code = :securityCode  " +
        "AND T.TYPE= :transactionType AND T.action != :actions)", nativeQuery = true)
    BigDecimal findQuantityBySecurityCodeAndType(@Param("securityCode")String SecurityCode, @Param("transactionType")String type, @Param("actions")String action);

}
