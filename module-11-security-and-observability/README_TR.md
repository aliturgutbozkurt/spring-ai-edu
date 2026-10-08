# Modül 11: Üretim Güvenlik Korkulukları, Güvenlik ve Gözlemlenebilirlik

Spring AI Eğitim Serisi **Modül 11**'e hoş geldiniz. Bu modülde kurumsal yapay zeka sistemlerini korumak için giriş/çıkış güvenlik korkulukları (guardrails), kişisel veri (PII) maskeleme, prompt enjeksiyon savunması ve OpenTelemetry gözlemlenebilirliği kurmayı öğreneceksiniz.

## Ana Özellikler
- **Kişisel Veri (PII) Maskeleme**: E-posta, kredi kartı ve kimlik numaralarının otomatik temizlenmesi.
- **Prompt Enjeksiyon Koruması**: Düşmanca jailbreak ve sistem promptunu sızdırma girişimlerini engelleme.
- **Micrometer Yapay Zeka Metrikleri**: İstek hacmi, gecikme histogramları ve engellenen saldırı sayaçları.
- **Actuator & OpenTelemetry Entegrasyonu**: Dağıtık izleme ve kurumsal sistem sağlığı göstergeleri.
- **İki Dilli Dokümantasyon ve Marp Slaytları**: İngilizce ve Türkçe hazırlanmış, derlenmiş PDF notları.

## Testleri Çalıştırma
```bash
mvn clean test -pl module-11-security-and-observability -am
```

## Ödevleri Çalıştırma
```bash
# Başlangıç (Eksik TODO'lar sebebiyle başarısız olur):
mvn test -f module-11-security-and-observability/homework/starter/pom.xml

# Çözüm (%100 Başarılı testler):
mvn test -f module-11-security-and-observability/homework/solution/pom.xml
```
