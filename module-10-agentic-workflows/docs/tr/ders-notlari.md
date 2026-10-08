---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Modül 10
## Ajan Yapay Zeka (Agentic AI) ve Otonom Çoklu Ajan İş Akışları
### Java ile Akıl Yürütme (ReAct) ve Ajan Takımları

---

## 1. Bir Modeli "Ajan" (Agentic) Yapan Nedir?

- Standart LLM'ler tek bir isteme tek bir yanıt üretir.
- **Ajan Yapay Zeka** ise otonom olarak:
  1. Karmaşık bir hedefi alt görevlere böler.
  2. Geri bildirim döngüsünde (Düşünce -> Eylem -> Gözlem) araçları çalıştırır.
  3. Araçlardan gelen yanıtlara göre planını günceller ve hedefe ulaşana kadar devam eder.

---

## 2. Spring AI'da ReAct (Reason + Act) Deseni

```
[Kullanıcı Hedefi]
       │
 ┌─────▼──────────────────────────────┐
 │ Düşünce: Sıradaki adımım ne olmalı?│
 │ Eylem: Araç X'i çalıştır           │◄──┐
 │ Gözlem: Aracın döndürdüğü veri     │   │ İteratif Döngü
 └─────┬──────────────────────────────┘   │ (Maksimum Adımla Sınırlı)
       │ Hedefe Ulaşıldı mı?              │
       ├─── HAYIR ────────────────────────┘
       └─── EVET ──► Nihai Raporu Sentezle
```

---

## 3. Çoklu Ajan Takımları: Süpervizör-İşçi Mimarisi

```
                 [Süpervizör Ajan]
                 (Planlar ve Yönetir)
                 /        |        \
                /         |         \
               v          v          v
        [Arama Ajanı] [Veri Ajanı] [Yazar Ajan]
```

- Her alt ajanın kendine ait özel sistem talimatları, araçları ve sınırlandırılmış bir görev alanı vardır.

---

## 4. Operasyonel Güvenlik ve İnsan Onay Kapıları (Human-in-the-Loop)

- Güvenlik sınırları olmayan ajanlar sonsuz döngülere girebilir ve yüksek maliyet üretebilir.
- **Zorunlu Kontroller**:
  - Maksimum adım sayısı (örn. en fazla 5 iterasyon).
  - Döngü algılama (aynı eylemin tekrarını engelleme).
  - Kritik işlemler (veritabanı silme, para transferi) öncesinde insan onay adımı.

---

## 5. Çıkarımlar ve İlkeler

1. Ajan döngülerine mutlaka maksimum adım ve zaman aşımı limitleri koyun.
2. Tek bir devasa ajan yerine özelleşmiş alt ajan takımları kurun.
3. Kritik harici sistem değişikliklerini insan denetiminden geçirin.
