---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Bitirme Projesi (Capstone)
## Kurumsal Çoklu Ajan Bilgi ve Operasyon Asistanı
### Modern Java ile Üretim Seviyesinde Üretken Yapay Zeka Zirvesi

---

## 1. Bitirme Projesine Genel Bakış ve Mimari

- Bitirme Projesi, 12 modülde edinilen tüm yetkinlikleri birleştiren eksiksiz bir kurumsal platformdur:
  - **Yönetici Ajan (Supervisor Agent) Mimarisi**: İstek niyetini analiz etme ve uzman alt ajanlara yönlendirme.
  - **Kurumsal Bilgi RAG Alt Ajanı**: Şirket içi politika ve sözleşme dokümanlarından yüksek doğrulukla getirme.
  - **Operasyon ve Araç Alt Ajanı**: BT servis yönetimi, otomatik biletleme ve sunucu denetimi.
  - **Sıfır Güven Korkulukları (Guardrails)**: Girişte hassas veri (PII) maskeleme ve güvenlik kuralları.
  - **Üretim Gözlemlenebilirliği**: Dağıtık izleme, gecikme metrikleri ve denetim kayıtları (audit trail).

---

## 2. Çoklu Ajan Koordinasyon Akışı

- **Adım 1: İstek Alma ve Maskeleme**:
  - Çalışan sorgusu `/api/v1/assistant/chat` uç noktasına ulaşır.
  - Korkuluk katmanı kredi kartı ve hassas verileri LLM'e iletilmeden önce temizler.
- **Adım 2: Yönetici Karar Motoru**:
  - Bilgi ve mevzuat odaklı sorular `KnowledgeRagSubagent`'a delege edilir.
  - Operasyonel eylemler `OperationsToolSubagent`'a yönlendirilir.
- **Adım 3: Çalıştırma ve Denetim Günlüğü**:
  - Alt ajan çıktısı `CapstoneResponse` içinde tam denetim iziyle birlikte kullanıcıya sunulur.

---

## 3. Yüksek Performanslı Spring Boot 3.4 Altyapısı

- **Project Loom Sanal İş Parçacıkları (Virtual Threads)**:
  - Bloklanma olmadan yüksek eşzamanlı istek işleme kapasitesi.
- **GraalVM Native AOT Uyumlu**:
  - Önceden tanımlanmış yansıma ve şablon çalışma zamanı ipuçları.
- **Actuator Sağlık ve Prometheus Metrikleri**:
  - Yapay zeka alt sistemlerinin gerçek zamanlı canlılık ve gecikme izlemesi.

---

## 4. Mezuniyet ve Kurumsal Yol Haritası

- Spring AI Enterprise Master Kursunu başarıyla tamamladınız:
  - Temeller, Prompt Mühendisliği, Yapısal Çıktılar
  - Araç Çağırma, Geri Çağırımlar, İleri Seviye RAG, Çok Modlu Yapay Zeka
  - Advisor & Bellek Sistemleri, Model Context Protocol (MCP)
  - Otonom Çoklu Ajanlar, Güvenlik Korkulukları ve Native AOT
- Artık Java ekosisteminde kurumsal seviyede yapay zeka sistemleri tasarlamaya ve ölçeklemeye tam olarak hazırsınız!
