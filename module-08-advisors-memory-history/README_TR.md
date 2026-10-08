# Modül 08: Sohbet Danışmanları, Bellek Sistemleri ve Konuşma Durumu

Spring AI Eğitim Serisi **Modül 08**'e hoş geldiniz. Bu modülde durumsuz (stateless) LLM dünyasında Spring AI Danışman (Advisor) zincirini ve `ChatMemory` mimarisini kullanarak çok turlu konuşma durumunu yönetmeyi öğreneceksiniz.

## Ana Özellikler
- **Konuşma Belleği**: `ChatMemory` soyutlaması ve `MessageChatMemoryAdvisor`.
- **Oturum İzolasyonu**: `conversationId` bazında dinamik oturum yönetimi.
- **Kayan Pencere (Sliding Window) Danışmanı**: Konuşma geçmişini bütçe limitlerine göre budayarak bağlam patlamasını önleme.
- **Kişisel Yapay Zeka Eğitmeni**: Öğrenci oturum geçmişini hatırlayan adaptif eğitmen servisi.
- **İki Dilli Dokümantasyon ve Marp Slaytları**: İngilizce ve Türkçe hazırlanmış, derlenmiş PDF notları.

## Testleri Çalıştırma
```bash
mvn clean test -pl module-08-advisors-memory-history -am
```

## Ödevleri Çalıştırma
```bash
# Başlangıç (Eksik TODO'lar sebebiyle başarısız olur):
mvn test -f module-08-advisors-memory-history/homework/starter/pom.xml

# Çözüm (%100 Başarılı testler):
mvn test -f module-08-advisors-memory-history/homework/solution/pom.xml
```
