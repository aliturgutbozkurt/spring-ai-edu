---
marp: true
theme: gaia
paginate: true
header: "Spring AI Kapsamlı Kurs - Modül 03: Yapılandırılmış Çıktı ve Nesne Dönüşümü"
footer: "© 2026 aliturgutbozkurt/spring-ai-edu"
style: |
  section {
    font-family: 'Helvetica Neue', Arial, sans-serif;
    font-size: 26px;
    padding: 40px;
  }
  h1 { color: #0b5c00; font-size: 42px; }
  h2 { color: #2c3e50; font-size: 34px; }
  pre { font-size: 19px; background: #f8f9fa; border-radius: 8px; }
  code { color: #d63384; }
---

# Modül 03: Yapılandırılmış Çıktı, BeanOutputConverter ve Tip Güvenli Çıkarım
## Olasılıksal Yapay Zeka ile Deterministik Kurumsal Şemaların Köprüsü

**Eğitmen**: Yapay Zeka Mühendisliği Eğitim Ekibi  
**Teknoloji Yığını**: Java 25/27 (LTS), Spring Boot 3.4/4.x, Spring AI 1.0+, Ollama

---

# 1. Deterministik Olmayan Çıktı Sorunu

Büyük Dil Modelleri yapılandırılmış veritabanı şemaları yerine metin token olasılıklarıyla çalışır. JSON talep edildiğinde yaşanan tipik hatalar:
- JSON metnini markdown etiketleri (````json ... ````) içine hapsetmek.
- Zorunlu alanları unutmak veya şemada olmayan alanlar uydurmak.
- Sayısal beklenen alanlara metin yazmak (`5` yerine `"beş"`).

Kurumsal sistemler (PostgreSQL, Kafka, REST API) parse edilemeyen JSON hatalarını tolere edemez.

---

# 2. Spring AI Çıktı Dönüştürücüleri (Output Converters)

Spring AI bu dönüşümü otomatikleştirir:

```
                  ┌──────────────────────────────────────────────┐
                  │          StructuredOutputConverter<T>        │
                  └──────────────────────┬───────────────────────┘
                                         │
         ┌───────────────────────────────┼───────────────────────────────┐
         ▼                               ▼                               ▼
┌──────────────────┐           ┌──────────────────┐           ┌──────────────────┐
│BeanOutputConverter│           │ MapOutputConverter│           │ListOutputConverter│
│   (Java Record)  │           │   (Key-Value)    │           │  (Dize Listesi)  │
└──────────────────┘           └──────────────────┘           └──────────────────┘
```

Dönüştürücü hedef sınıfın JSON şemasını otomatik hesaplar ve format kurallarını prompt'a enjekte eder.

---

# 3. Java Record Sınıflarıyla Şema Tasarımı

Jackson etiketleriyle zenginleştirilmiş immutable record nesneleri:

```java
public record CandidateSkill(
    @JsonPropertyDescription("Teknik yeteneğin adı")
    String name,

    @JsonPropertyDescription("Uzmanlık seviyesi: JUNIOR, MID, SENIOR, EXPERT")
    String proficiencyLevel,

    @JsonPropertyDescription("Yıl cinsinden deneyim süresi")
    int yearsOfExperience
) {}
```
Spring AI bu sınıfı reflection ile tarar ve eksiksiz bir JSON Schema talimatı üretir.

---

# 4. Akıcı ChatClient `.entity()` ile Doğrudan Çıkarım

Manuel string parse işlemlerine son:

```java
@Service
public class ResumeExtractionService {

    private final ChatClient chatClient;

    public CandidateProfile cikar(String hamMetin) {
        return chatClient.prompt()
                .user(u -> u.text("Aşağıdaki metinden aday profilini çıkar:\n{metin}")
                            .param("metin", hamMetin))
                .call()
                .entity(CandidateProfile.class); // Doğrudan hedef nesneye dönüşür!
    }
}
```

---

# 5. Ödev 03 Yönergesi ve Uygulama

### "Özgeçmiş (CV) Yapılandırılmış Veri Çıkarma Servisi"
1. `module-03-structured-output/homework/starter` dizinini açın.
2. `ResumeParserService.java` dosyasını inceleyin.
3. `BeanOutputConverter<ExtractedCandidate>` kullanarak extraction mantığını tamamlayın.
4. `mvn test` komutunu çalıştırarak tüm testlerin geçtiğini doğrulayın.
