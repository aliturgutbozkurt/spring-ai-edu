# Specification: Module 06 - RAG Part 2: Advanced Retrieval, Reranking & Evaluation Metrics

## 1. Overview
Module 06 builds advanced RAG pipelines. It covers multi-query expansion, metadata filtering, contextual reranking, reciprocal rank fusion, and automated evaluation metrics (the RAG Triad: Groundedness/Faithfulness, Answer Relevance, Context Relevance).

## 2. Learning Objectives
1. Implement metadata filtering expressions in Spring AI vector queries.
2. Build a contextual reranker that scores candidate documents before LLM synthesis.
3. Formulate RAG Triad evaluation formulas to benchmark retrieval quality without human annotators.
4. Implement semantic contract document analysis with legal clause verification.
5. Provide offline deterministic tests for evaluation and reranking.

## 3. Architecture & Components
- **`ContractDocument`**: Record containing clause text, contractId, jurisdiction, year, and clause type.
- **`HybridContractRetrievalService`**: Filters vector results using metadata expressions (e.g. `jurisdiction == 'NY'`).
- **`ContextualReranker`**: Reranks top-N retrieved candidate chunks by calculating contextual alignment scores.
- **`RagTriadEvaluator`**: Computes Groundedness (0.0 - 1.0), Context Relevance (0.0 - 1.0), and Answer Relevance (0.0 - 1.0).
- **`ContractRagController`**: REST endpoint returning answer + Triad audit metrics.

## 4. Quality Gates
- Metadata query filtering tests.
- Reranking order verification tests.
- RAG Triad evaluator calculation tests.
- Homework starter with failing stubs; homework solution 100% passing.
- Bilingual lesson notes and Marp compiled PDFs.
