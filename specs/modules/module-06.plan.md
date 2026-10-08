# Implementation Plan: Module 06 - RAG Part 2: Advanced Retrieval, Reranking & Evaluation Metrics

## 1. Directory Structure
```
module-06-rag-advanced-evaluation/
├── pom.xml
├── src/main/java/com/springai/edu/module06/
│   ├── Module06Application.java
│   ├── config/ContractRagConfig.java
│   ├── model/ContractClause.java
│   ├── model/ContractRagResponse.java
│   ├── model/RagEvaluationResult.java
│   ├── service/HybridContractRetrievalService.java
│   ├── service/ContextualRerankingService.java
│   ├── service/RagTriadEvaluatorService.java
│   └── controller/ContractRagController.java
├── src/test/java/com/springai/edu/module06/
│   ├── ContextualRerankingServiceTest.java
│   └── RagTriadEvaluatorServiceTest.java
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
1. Create `pom.xml`.
2. Implement metadata filtering in `HybridContractRetrievalService`.
3. Implement `ContextualRerankingService` prioritizing high-specificity clauses.
4. Implement `RagTriadEvaluatorService` auditing retrieval relevance and factual groundedness.
5. Create homework starter/solution projects with unit tests.
6. Generate bilingual documentation and PDFs.
