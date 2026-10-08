# Specification: Module 05 - RAG Part 1: Embeddings, Vector Databases & Document ETL

## 1. Overview
Module 05 covers the fundamentals of Retrieval-Augmented Generation (RAG) in Spring AI. Students learn document reading, tokenization, text splitting, dense vector embeddings, vector store abstraction (`VectorStore`), similarity search with thresholds, and ETL (Extract, Transform, Load) pipelines.

## 2. Learning Objectives
1. Understand vector embeddings and semantic search mechanics.
2. Master Spring AI `DocumentReader`, `TokenTextSplitter`, and `DocumentWriter` abstractions.
3. Configure and utilize `SimpleVectorStore` for in-memory testing and `PgVectorStore` for production.
4. Build a deterministic ETL ingestion pipeline for corporate policy documents.
5. Implement similarity-based retrieval with top-k and similarity score thresholding.
6. Provide zero-cost offline testing using `shared-common`'s `MockEmbeddingModel` and `MockChatModel`.

## 3. Architecture & Key Components
- **`PolicyDocument`**: Strongly-typed Java record representing an HR/Compliance policy document chunk.
- **`PolicyEtlService`**: Reads raw documents, chunks them using `TokenTextSplitter`, annotates metadata, and writes to `VectorStore`.
- **`PolicySearchService`**: Executes similarity searches against the vector store with configurable top-K and similarity cutoffs.
- **`PolicyQaService`**: Synthesizes grounded answers using retrieved context injected into a RAG prompt template.
- **`PolicyQaController`**: REST endpoint exposing `/api/v1/policies/query` and `/api/v1/policies/ingest`.

## 4. Quality Gates & Test Requirements
- Unit tests for `TokenTextSplitter` chunking behavior.
- ETL pipeline tests verifying document chunking and vector store persistence.
- Semantic retrieval tests verifying threshold filtering.
- Homework starter with failing JUnit 5 tests; homework solution with 100% passing tests.
- Bilingual lesson notes (`docs/en/lesson-notes.md`, `docs/tr/ders-notlari.md`) and compiled Marp PDFs.
