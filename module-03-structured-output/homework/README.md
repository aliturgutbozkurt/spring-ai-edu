# Homework 03: Resume & CV Parsing Service to Java Records (English)

## Objective
Build a strongly-typed resume parsing microservice using Spring AI's `BeanOutputConverter<CandidateProfile>`. Transform unstructured resume summaries into immutable Java records with full schema adherence.

## Requirements
1. Use `BeanOutputConverter<CandidateProfile>` to instruct the model to produce valid JSON.
2. Convert and validate the model's output into the target record.
3. Handle empty inputs gracefully.
4. Pass 100% of unit tests.
