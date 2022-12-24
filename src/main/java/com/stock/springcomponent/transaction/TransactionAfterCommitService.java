package com.stock.springcomponent.transaction;

import org.elasticsearch.common.inject.Inject;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Description 解决事务未提交读不到数据问题
 * @Author makuo
 * @Date 2022/12/24 9:32
 **/
public class TransactionAfterCommitService {

    /**
     * 方式一
     */
    public void doAfterCommit() {
        // 当前存在事务
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronizationAdapter() {
                        @Override
                        public void afterCommit() {
                            // do something while transaction commit for main thread
                        }
                    }
            );
        }
    }
}

/**
 * 方式二
 */
// 事件
class TransactionEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param msgId the object on which the event initially occurred or with
     *              which the event is associated (never {@code null})
     */
    public TransactionEvent(String msgId) {
        super(msgId);
    }
}

// 监听器
class TransactionEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessage(TransactionEvent event) {
        // 1. get info after insert MySQL
        // info = transactionService.findById(event.getSource());
        // 2. Asy send message
        //send(info);
    }
}

// 具体业务
@Service
class BizService {

    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void doSomething() {
        // ...
        // 插入MySQL数据
        // do insert
        String msgId = "id"; // insert MySQL
        // 异步发布事件，监听器执行事件
        applicationEventPublisher.publishEvent(new TransactionEvent(msgId));
        // do something
    }

}
