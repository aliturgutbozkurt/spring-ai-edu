# Module 07: Multimodal AI: Vision, Audio, Speech & Document OCR

Welcome to **Module 07** of the Spring AI Educational Series. This module covers multimodal generative AI: combining text, images, and audio using Spring AI's unified `Media` abstractions.

## Key Features
- **Spring AI Media API**: Attaching images (JPEG/PNG) and documents to `ChatClient` prompts.
- **Multimodal Visual Inspection**: Automated receipt OCR and structured line-item extraction.
- **Voice Memo Transcription**: Processing audio streams and extracting actionable audit tasks.
- **Bilingual Documentation & Marp Slides**: Fully compiled PDF notes in English and Turkish.

## Running Tests
```bash
mvn clean test -pl module-07-multimodal-vision-audio -am
```

## Running Homework
```bash
# Starter (fails on unimplemented TODOs):
mvn test -f module-07-multimodal-vision-audio/homework/starter/pom.xml

# Solution (100% passing tests):
mvn test -f module-07-multimodal-vision-audio/homework/solution/pom.xml
```
