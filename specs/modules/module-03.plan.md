# Module 03 Technical Plan: Structured Output, BeanOutputConverter & Type-Safe Extraction

**Module ID**: `module-03-structured-output`  
**Specification Ref**: [module-03.spec.md](file:///Users/aliturgutbozkurt/Desktop/spring-ai-edu/specs/modules/module-03.spec.md)  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#4](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/4)  
**Status**: APPROVED (Phase 2: Plan)  
**Last Updated**: 2026-10-08  

---

## 1. Directory Structure

```
module-03-structured-output/
├── pom.xml
├── README.md
├── docs/
│   ├── en/
│   │   ├── lesson-notes.md
│   │   └── lesson-notes.pdf
│   └── tr/
│       ├── ders-notlari.md
│       └── ders-notlari.pdf
├── src/
│   ├── main/
│   │   ├── java/com/springai/edu/module03/
│   │   │   ├── Module03Application.java
│   │   │   ├── config/
│   │   │   │   └── StructuredOutputConfig.java
│   │   │   ├── controller/
│   │   │   │   └── ResumeParserController.java
│   │   │   ├── model/
│   │   │   │   ├── CandidateExperience.java
│   │   │   │   ├── CandidateProfile.java
│   │   │   │   └── CandidateSkill.java
│   │   │   └── service/
│   │   │       └── ResumeParserService.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/springai/edu/module03/
│           └── ResumeParserServiceTest.java
└── homework/
    ├── README.md
    ├── README_TR.md
    ├── starter/
    │   ├── pom.xml
    │   └── src/
    │       ├── main/java/.../ResumeParserService.java
    │       └── test/java/.../ResumeParserServiceTest.java
    └── solution/
        ├── pom.xml
        └── src/
            ├── main/java/.../ResumeParserService.java
            └── test/java/.../ResumeParserServiceTest.java
```

---

## 2. Implementation Steps
1. Create `module-03-structured-output/pom.xml` and register in root reactor `pom.xml`.
2. Define strongly typed domain records (`CandidateProfile`, `CandidateSkill`, `CandidateExperience`).
3. Implement `ResumeParserService` using `BeanOutputConverter<CandidateProfile>` and `chatClient.prompt().entity(...)`.
4. Implement `ResumeParserController` exposing `POST /api/v1/resumes/parse`.
5. Write unit tests with `MockChatModel` providing structured JSON output.
6. Create homework starter (with stubs and failing test) and solution (100% passing tests).
7. Create bilingual lesson notes and export PDFs.
