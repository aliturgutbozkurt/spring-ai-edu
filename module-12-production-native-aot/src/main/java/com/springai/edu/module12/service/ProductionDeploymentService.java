package com.springai.edu.module12.service;

import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Map;

@Service
public class ProductionDeploymentService {

    public Map<String, Object> getDeploymentMetrics() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long heapUsedMb = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long heapMaxMb = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        long uptimeSeconds = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;

        return Map.of(
                "runtime", "GraalVM Native / JVM 21+",
                "virtualThreadsEnabled", true,
                "uptimeSeconds", uptimeSeconds,
                "heapUsedMb", heapUsedMb,
                "heapMaxMb", heapMaxMb,
                "deploymentProfile", "PRODUCTION_READY"
        );
    }
}
