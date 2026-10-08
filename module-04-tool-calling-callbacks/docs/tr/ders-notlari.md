---
marp: true
theme: gaia
paginate: true
header: "Spring AI Kapsamlı Kurs - Modül 04: Araç Çağırma ve Fonksiyon Geri Çağrıları"
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

# Modül 04: Araç Çağırma (Tool Calling) ve Fonksiyon Geri Çağrıları
## LLM'leri Kurumsal Veritabanlarına ve API'lere Bağlama

**Eğitmen**: Yapay Zeka Mühendisliği Eğitim Ekibi  
**Teknoloji Yığını**: Java 25/27 (LTS), Spring Boot 3.4/4.x, Spring AI 1.0+, Ollama

---

# 1. Neden Araç Çağırma (Tool Calling)?

Temel dil modelleri dış dünyadan izoledir:
- Canlı veritabanınızı doğrudan sorgulayamazlar.
- Gerçek zamanlı stok durumunu göremez veya iade işlemi tetikleyemezler.
- Kurumsal özel veriler sorulduğunda halüsinasyon üretebilirler.

**Araç Çağırma (Tool Calling)**, modelin belirli bir fonksiyonu çalıştırma niyetini yapılandırılmış olarak bildirmesini, uygulamanın bu fonksiyonu çalıştırıp sonucunu modele iletmesini ve modelin nihai yanıtı sentezlemesini sağlar.

---

# 2. Araç Yürütme Döngüsü

```
Kullanıcı İstemi ("ORD-101 nolu siparişimin durumu nedir?")
      │
      ▼
Spring AI ChatClient (İstemi ve JSON Schema araç tanımlarını LLM'e iletir)
      │
      ▼
LLM karar verir: "getOrderDetails(orderId: 'ORD-101') aracını çalıştır"
      │
      ▼
Spring AI çalıştırır: supportTools.getOrderDetails("ORD-101")
      │
      ▼
Araç Yanıtı: { orderId: "ORD-101", status: "DELIVERED" }
      │
      ▼
LLM sonucu alır ve kullanıcıya doğal dilde yanıt üretir
```

---

# 3. `@Tool` ile Araç Bildirimi

Modern Spring AI 1.x'te araçlar doğrudan Spring Bean metotlarına eklenir:

```java
@Service
public class SiparisAraclari {

    @Tool(description = "Sipariş detaylarını ID üzerinden getirir, örn: ORD-101")
    public OrderRecord getOrderDetails(String orderId) {
        return siparisDeposu.bul(orderId);
    }
}
```
Spring AI metot parametrelerini ve Java record nesnelerini otomatik olarak JSON Schema biçimine dönüştürür.

---

# 4. Akıcı ChatClient ile Araç Bağlama

Metot seviyesinde araçları bağlamak çok pratiktir:

```java
@Service
public class DestekServisi {

    private final ChatClient chatClient;
    private final SiparisAraclari siparisAraclari;

    public String cevapVer(String musteriMesaji) {
        return chatClient.prompt()
                .tools(siparisAraclari) // Model artık bu aracı çağırabilir!
                .user(musteriMesaji)
                .call()
                .content();
    }
}
```

---

# 5. Ödev 04 Yönergesi ve Uygulama

### "Otonom Müşteri Destek ve Talep Değerlendirme Ajanı"
1. `module-04-tool-calling-callbacks/homework/starter` dizinini açın.
2. `OrderRefundService.java` dosyasını inceleyin.
3. `checkEligibility` metodunu `@Tool` ile işaretleyin.
4. `processRefundRequest` içinde aracı `ChatClient`'a bağlayın.
5. `mvn test` komutunu çalıştırarak tüm testlerin geçtiğini doğrulayın.
