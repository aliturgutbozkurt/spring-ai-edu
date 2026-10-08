# Bitirme Projesi (Capstone): Kurumsal Çoklu Ajan Bilgi ve Operasyon Asistanı

Spring AI Enterprise Master Kursu **Bitirme Projesine (Capstone)** hoş geldiniz. Bu proje, Modül 01'den Modül 12'ye kadar öğrenilen tüm mimari ve pratik bileşenleri üretim seviyesinde kurumsal bir asistan uygulamasında birleştirmektedir.

---

## 🌟 Mimari ve Yetenekler

1. **Kurumsal Yönetici Ajan (Supervisor Agent)**: Gelen kullanıcı isteklerini analiz eden, hassas veri (PII) temizleme korkuluklarını uygulayan ve görevleri uzman alt ajanlara yönlendiren merkezi orkestratör.
2. **Kurumsal Bilgi RAG Alt Ajanı**: Şirket içi İK politikalarını, SLA sözleşmelerini ve kılavuzları temel alan RAG çözümü.
3. **Operasyon ve Araç Alt Ajanı**: Destek biletleri açma, sunucu sağlığını denetleme ve hesap işlemlerini gerçekleştiren araç çağırıcı.
4. **Üretim Kalitesi ve Gözlemlenebilirlik**: Kapsamlı denetim izi (audit trail), Project Loom sanal iş parçacıkları, Actuator sağlık göstergeleri ve GraalVM Native AOT uyumluluğu.

---

## 🛠️ Derleme ve Test

```bash
mvn clean test -pl capstone-enterprise-ai-assistant -am
```
