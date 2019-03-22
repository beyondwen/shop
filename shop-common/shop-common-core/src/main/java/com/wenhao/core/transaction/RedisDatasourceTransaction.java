package com.wenhao.core.transaction;

import com.wenhao.core.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class RedisDatasourceTransaction {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开始事务
     *
     * @throws Exception
     */
    public TransactionStatus begin() throws Exception {
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        redisUtil.begin();
        return transaction;
    }

    /**
     * 提交事务
     *
     * @param transactionStatus
     * @throws Exception
     */
    public void commit(TransactionStatus transactionStatus) throws Exception {
        if (transactionStatus == null) {
            throw new Exception("transactionStatus is null");
        }
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 提交事务
     *
     * @param transactionStatus
     * @throws Exception
     */
    public void rollback(TransactionStatus transactionStatus) throws Exception {
        if (transactionStatus == null) {
            throw new Exception("transactionStatus is null");
        }
        dataSourceTransactionManager.rollback(transactionStatus);
        //redisUtil.discard();
    }
}
