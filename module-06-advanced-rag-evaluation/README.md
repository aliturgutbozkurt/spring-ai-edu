# Module 06: RAG Part 2: Advanced Retrieval, Reranking & Evaluation Metrics

Welcome to **Module 06** of the Spring AI Educational Series. This module teaches you production-grade retrieval architectures: metadata filtering, contextual reranking, and automated RAG Triad evaluations.

## Key Features
- **Metadata-Filtered Search**: SQL-like expressions to enforce jurisdictional and multi-tenant security boundaries.
- **Contextual Reranking**: Re-scoring top candidates based on cross-encoder style keyword density and clause significance.
- **RAG Triad Automated Evaluation**: Automated algorithms computing Context Relevance, Groundedness (Faithfulness), and Answer Relevance.
- **Contract Legal Analysis**: Real-world legal contract querying with audit compliance checks.
- **Bilingual Documentation & Marp Slides**: Fully compiled PDF notes in English and Turkish.

## Running Tests
```bash
mvn clean test -pl module-06-advanced-rag-evaluation -am
```

## Running Homework
```bash
# Starter (fails on unimplemented TODOs):
mvn test -f module-06-advanced-rag-evaluation/homework/starter/pom.xml

# Solution (100% passing tests):
mvn test -f module-06-advanced-rag-evaluation/homework/solution/pom.xml
```
