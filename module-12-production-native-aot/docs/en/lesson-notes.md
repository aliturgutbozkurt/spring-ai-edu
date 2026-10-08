---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Module 12
## Production Deployment & GraalVM Native AOT
### Sub-Second Startup and High-Efficiency Cloud Native AI

---

## 1. Cloud Native AI Deployment Challenges

- Traditional JVM workloads face several pain points in cloud environments:
  - **Slow Cold Starts**: JIT compilation and classpath scanning take seconds to initialize.
  - **High Memory Footprint**: JVM runtime heap overhead makes scaling costly.
  - **Serverless & Autoscaling Lag**: New instances cannot immediately serve burst AI traffic.
- GraalVM Native Image compiles Spring Boot 3.4+ and Spring AI applications ahead-of-time (AOT) into standalone platform-native binaries.

---

## 2. GraalVM Native AOT Fundamentals

- **Ahead-Of-Time (AOT) Compilation**:
  - Closed-world assumption: All classes, methods, and reflection must be reachable and declared during build time.
  - Unused bytecode and runtime reflection metadata are stripped out.
  - Generates an instant-starting binary with instant peak performance.
- Key benefits:
  - Startup time drops from ~3-5 seconds to **< 50 milliseconds**.
  - Base memory consumption drops from ~400MB to **< 50MB**.

---

## 3. Spring AI Runtime Hints (`RuntimeHintsRegistrar`)

- Spring Boot 3.4 provides the `RuntimeHintsRegistrar` contract to register dynamic reflection and resources needed by Spring AI:
  - **Domain Model Reflection**: Prompt POJOs, JSON schemas, records, and structured output beans.
  - **Classpath Resources**: Prompt templates (`.st`), vector ETL markdown documents, and tokenization dictionaries.
```java
public class SpringAiRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(CourseMetadata.class,
            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
            MemberCategory.INVOKE_DECLARED_METHODS,
            MemberCategory.DECLARED_FIELDS);
        hints.resources().registerPattern("prompts/*.st");
    }
}
```

---

## 4. Multi-Stage Distroless Docker Architecture

- Production container images must be minimal and secure:
  - Stage 1: Build native image using `native-image-maven-plugin` or Paketo buildpacks.
  - Stage 2: Deploy inside Google Distroless or `gcr.io/distroless/cc-debian12`.
  - Zero shell, zero package manager, minimal CVE attack surface.

---

## 5. Actuator AI Health Indicators & Virtual Threads

- **Spring Boot Actuator Integration**:
  - Custom `AiModelHealthIndicator` performs liveness checks on vector databases and AI inference endpoints.
  - Exposes latency telemetry and provider status over `/actuator/health`.
- **Project Loom Virtual Threads**:
  - `spring.threads.virtual.enabled=true` enables massive concurrent I/O throughput when streaming LLM responses and performing MCP tool orchestration.

---

## 6. Summary & Production Checklist

1. Always implement `RuntimeHintsRegistrar` for dynamic POJOs and prompt templates.
2. Verify native images in CI/CD before staging deployments.
3. Secure your production runtime with Distroless scratch containers.
4. Monitor model availability and token latency via Actuator Health Indicators.
