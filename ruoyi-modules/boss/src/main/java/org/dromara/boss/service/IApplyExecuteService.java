package org.dromara.boss.service;

/**
 * @author xiexi
 * @description
 * @date 2024/11/16 20:31
 */
public interface IApplyExecuteService {

    /**
     * 执行沟通任务
     *
     * @param applyJobId 任务id
     */
    void execute(Long applyJobId);
}
