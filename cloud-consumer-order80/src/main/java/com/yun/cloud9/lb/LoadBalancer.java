package com.yun.cloud9.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author yun
 * @version 1.0
 * @date 2020-9-23 13:51
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
