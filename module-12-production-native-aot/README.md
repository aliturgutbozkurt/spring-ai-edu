# Module 12: Production Deployment and GraalVM Native AOT

Welcome to **Module 12** of the Spring AI Enterprise Master Course. In this module, you will learn how to prepare Spring AI enterprise microservices for high-efficiency production environments with GraalVM Native AOT compilation, custom runtime hints, health indicators, and distroless containerization.

---

## 🚀 Key Topics Covered

1. **GraalVM Native Image Compilation**: Ahead-Of-Time (AOT) bytecode compilation for sub-50ms cold starts and <50MB RAM footprint.
2. **Spring AI `RuntimeHintsRegistrar`**: Registering dynamic reflection hints for records and resources (`*.st`, `*.md`).
3. **Actuator AI Health Indicator**: Exposing real-time availability and latency telemetry via `/actuator/health`.
4. **Distroless Containerization**: Building minimal CVE attack surface Docker containers.
5. **Project Loom Virtual Threads**: High-throughput non-blocking orchestration of LLM requests.

---

## 📁 Project Structure

```
module-12-production-native-aot/
├── Dockerfile
├── pom.xml
├── src/main/java/com/springai/edu/module12/
│   ├── Module12Application.java
│   ├── aot/SpringAiRuntimeHintsRegistrar.java
│   ├── controller/DeploymentStatusController.java
│   ├── health/AiModelHealthIndicator.java
│   └── service/ProductionDeploymentService.java
├── src/main/resources/application.yml
├── docs/
│   ├── en/lesson-notes.md (Marp slides)
│   └── tr/ders-notlari.md (Marp slaytları)
└── homework/
    ├── starter/
    └── solution/
```

---

## 🛠️ Build and Test

```bash
mvn clean test -pl module-12-production-native-aot -am
```
