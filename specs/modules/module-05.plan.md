# Implementation Plan: Module 05 - RAG Part 1: Embeddings, Vector Databases & Document ETL

## 1. Directory Structure
```
module-05-rag-embeddings-etl/
├── pom.xml
├── src/main/java/com/springai/edu/module05/
│   ├── Module05Application.java
│   ├── config/VectorStoreConfig.java
│   ├── model/PolicyQueryRequest.java
│   ├── model/PolicyAnswerResponse.java
│   ├── service/PolicyEtlService.java
│   ├── service/PolicySearchService.java
│   ├── service/PolicyQaService.java
│   └── controller/PolicyQaController.java
├── src/main/resources/
│   ├── application.yml
│   └── documents/security-policy.md
├── src/test/java/com/springai/edu/module05/
│   ├── PolicyEtlServiceTest.java
│   └── PolicyQaServiceTest.java
├── homework/
│   ├── starter/
│   └── solution/
├── docs/
│   ├── en/lesson-notes.md
│   └── tr/ders-notlari.md
├── README.md
└── README_TR.md
```

## 2. Step-by-Step Execution
1. Create `pom.xml` with `shared-common`, Spring Boot Starter Web, and Spring AI Vector Store.
2. Implement `VectorStoreConfig` providing `SimpleVectorStore` backed by `MockEmbeddingModel`.
3. Implement `PolicyEtlService` chunking policy text and storing documents.
4. Implement `PolicyQaService` retrieving relevant chunks and synthesizing answers with `ChatClient`.
5. Implement homework starter & solution with failing/passing test suites.
6. Write bilingual notes and compile PDFs.
