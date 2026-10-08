---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Module 07
## Multimodal AI: Vision, Audio, Speech & Document OCR
### Cross-Modal Engineering with Spring AI

---

## 1. What is Multimodal AI?

- Traditional LLMs process only plain text tokens.
- **Multimodal Models** (GPT-4o, Claude 3.5 Sonnet, LLaVA, Whisper) accept images, audio clips, and documents alongside text prompts.
- Spring AI unifies this via the `org.springframework.ai.model.Media` interface.

---

## 2. Spring AI Media Abstraction

```java
// Create media object from byte array or URL
Media receiptImage = new Media(
    MimeTypeUtils.IMAGE_JPEG,
    new ByteArrayResource(imageBytes)
);

// Bind image directly into user prompt
AuditReport report = chatClient.prompt()
    .user(u -> u
        .text("Inspect this warehouse receipt for discrepancies.")
        .media(receiptImage)
    )
    .call()
    .entity(AuditReport.class);
```

---

## 3. Automated Document OCR & Structured Extraction

- Combine Multimodal Vision with `BeanOutputConverter<T>`:
  - Extract table line items directly into strongly typed Java 27 records.
  - No fragile regex or legacy OCR coordinate mapping required.

---

## 4. Audio Transcription (STT) & Speech Synthesis (TTS)

```
[Inspector Voice Memo (.wav/.mp3)]
               │
               ▼
[Spring AI Speech-to-Text / Whisper]
               │
               ▼
[Structured Text Transcript]
               │
               ▼
[Audit Action Items & Automated Inventory Adjustments]
```

---

## 5. Key Architecture Principles

1. Check file size and compress images before dispatching to prevent bandwidth bottlenecks.
2. Standardize MIME types (`image/jpeg`, `image/png`, `audio/wav`).
3. Always validate extracted quantities against database balances.
