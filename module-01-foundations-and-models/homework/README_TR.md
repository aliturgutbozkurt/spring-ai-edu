# Ödev 01: Çoklu Model Sağlık ve Karşılaştırma Servisi (Türkçe)

## Amaç
Java 25/27 Sanal İş Parçacıkları (Virtual Threads) kullanarak birden fazla Büyük Dil Modeli (LLM) sağlayıcısını aynı anda (eşzamanlı) sorgulayan, performans metriklerini (gecikme, token tüketimi) toplayan ve sağlayıcı arızalarını izole edebilen dayanıklı bir Spring AI servisi geliştirmek.

---

## Mimari Şema
```
İstemci İsteği (Client Request)
      │
      ▼
MultiModelComparisonService
      │
      ├──> [Sanal İş Parçacığı 1] ──> OllamaChatModel  (llama3.2)
      ├──> [Sanal İş Parçacığı 2] ──> OpenAiChatModel  (gpt-4o-mini)
      └──> [Sanal İş Parçacığı 3] ──> MockChatModel    (Deterministik Test)
      │
      ▼
MultiModelComparisonResult (toplu gecikme ve token istatistikleri)
```

---

## Gereksinimler

1. **Sanal İş Parçacığı (Virtual Threads) ile Çalıştırma**:
   - `Executors.newVirtualThreadPerTaskExecutor()` veya sanal thread executor'ı ile yapılandırılmış `CompletableFuture` kullanılmalıdır.
   - Çağrılar işletim sistemi iş parçacığı havuzunu tüketmeden eşzamanlı yürütülmelidir.

2. **Hata İzolasyonu (Fault Isolation)**:
   - Sağlayıcılardan biri çökerse veya zaman aşımına uğrarsa (timeout), tüm istek başarısız **olmamalıdır**.
   - Hatalı sağlayıcı için hata mesajı kaydedilmeli, diğer sağlayıcılar normal şekilde tamamlanmalıdır.

3. **Metrik Toplama**:
   - Her model çağrısının milisaniye cinsinden süresi ölçülmelidir.
   - `ChatResponse.getMetadata().getUsage()` üzerinden girdi (`promptTokens`) ve çıktı (`generationTokens`) miktarları toplanmalıdır.

---

## Dizin Yapısı
- `starter/`: Öğrenciler için `// TODO` işaretleri ve başarısız JUnit 5 testleri içerir.
- `solution/`: %100 testleri geçen referans çözüm.

## Testleri Çalıştırma
```bash
# starter/ dizininde:
mvn test

# solution/ dizininde:
mvn test
```

## Değerlendirme Kriterleri (Toplam 100 Puan)
- **30 Puan**: Virtual Threads ile eşzamanlı çalışma.
- **25 Puan**: Sağlayıcı hatasında uygulamanın çökmemesi (Graceful degradation).
- **25 Puan**: Gecikme ve token metriklerinin doğru hesaplanması.
- **20 Puan**: Temiz kod, Java record kullanımı ve Spring AI standartlarına uyum.
