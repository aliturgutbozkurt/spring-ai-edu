# Modül 12: Canlı Ortama Dağıtım ve GraalVM Native AOT

Spring AI Enterprise Master Kursu **Modül 12**'ye hoş geldiniz. Bu modülde, Spring AI tabanlı kurumsal mikroservislerin GraalVM Native AOT derlemesi, çalışma zamanı ipuçları (RuntimeHints), sağlık izleyicileri ve distroless konteynerler ile üretim ortamına nasıl taşınacağını öğreneceksiniz.

---

## 🚀 Kapsanan Temel Konular

1. **GraalVM Native Image Derlemesi**: 50 milisaniyenin altında başlatma ve 50MB altı bellek tüketimi için AOT derleme.
2. **Spring AI `RuntimeHintsRegistrar`**: Dinamik alan sınıfları ve şablon kaynakları (`*.st`, `*.md`) için yansıma ipuçları.
3. **Actuator AI Sağlık Göstergesi**: `/actuator/health` üzerinden gerçek zamanlı erişilebilirlik ve gecikme takibi.
4. **Distroless Konteyner Mimarisi**: Minimum CVE saldırı yüzeyine sahip güvenli Docker imajları.
5. **Project Loom Sanal İş Parçacıkları**: Eşzamanlı LLM istekleri için yüksek verimli I/O.

---

## 🛠️ Derleme ve Test

```bash
mvn clean test -pl module-12-production-native-aot -am
```
