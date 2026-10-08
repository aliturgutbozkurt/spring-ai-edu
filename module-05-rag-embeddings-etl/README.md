# Module 05: RAG Part 1: Embeddings, Vector Databases & Document ETL

Welcome to **Module 05** of the Spring AI Educational Series. This module teaches you how to construct enterprise-grade Retrieval-Augmented Generation (RAG) pipelines in Java.

## Key Features
- **Document ETL Pipeline**: Ingest and split Markdown and raw documents using `TokenTextSplitter`.
- **Vector Embeddings**: Dense semantic representations using Spring AI's `EmbeddingModel`.
- **VectorStore Abstraction**: Storing and querying vector indexes with `SimpleVectorStore` and `PgVectorStore`.
- **Threshold & Metadata Filtering**: Precise retrieval using score filters and SQL-like metadata expressions.
- **Bilingual Documentation & Marp Slides**: Fully compiled PDF notes in English and Turkish.

## Running Tests
```bash
mvn clean test -pl module-05-rag-embeddings-etl -am
```

## Running Homework
```bash
# Starter (fails on unimplemented TODOs):
mvn test -f module-05-rag-embeddings-etl/homework/starter/pom.xml

# Solution (100% passing tests):
mvn test -f module-05-rag-embeddings-etl/homework/solution/pom.xml
```
