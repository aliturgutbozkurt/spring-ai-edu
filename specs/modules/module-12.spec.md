# Specification: Module 12 - Production Deployment, Performance & GraalVM Native AOT

## 1. Overview
Module 12 prepares Spring AI applications for mission-critical enterprise production. Students master GraalVM Native Image compilation (Native AOT), sub-100ms startup times, memory footprint optimization from 800MB to 60MB, Docker containerization, and production health probes.

## 2. Learning Objectives
1. Understand Ahead-of-Time (AOT) compilation mechanics and reflection hints in Spring AI.
2. Register runtime hints (`RuntimeHintsRegistrar`) for dynamic LLM records and converters.
3. Build lightweight multi-stage Docker container images.
4. Configure Kubernetes liveness and readiness probes integrated with Spring Boot Actuator and model health checks.
5. Provide offline deterministic tests validating AOT hints and production configuration.

## 3. Architecture & Components
- **`SpringAiRuntimeHints`**: Implements `RuntimeHintsRegistrar` to ensure serialization and reflection work seamlessly under GraalVM native binary execution.
- **`ProductionHealthIndicator`**: Custom Actuator health check validating AI provider connectivity and latency thresholds.
- **`NativeOptimizationService`**: Demonstrates zero-reflection high-throughput inference patterns.
- **`DeploymentStatusController`**: REST endpoint exposing system health, heap usage, and container uptime.

## 4. Quality Gates
- RuntimeHints registration tests.
- Actuator AI health indicator unit tests.
- Production readiness check verification tests.
- Homework starter with failing stubs; solution with 100% passing tests.
- Bilingual lesson notes and Marp compiled PDFs.
