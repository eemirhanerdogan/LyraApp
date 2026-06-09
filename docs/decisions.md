# Mimari ve Tasarım Kararları

Bu doküman, LyraApp projesinin geliştirme sürecinde alınan teknik kararları ve uygulanan kısıtlamaları listeler.

## 1. Login Ekranı Geliştirmesi

Login ekranı için aşağıdaki kararlar alınmış ve uygulanmıştır:

*   **Sadece UI Odaklı Geliştirme:** Login ekranı yalnızca görsel arayüz (UI) katmanı olarak tasarlanmıştır. Bu aşamada herhangi bir fonksiyonel iş mantığı eklenmemiştir.
*   **Mimari Kısıtlamalar:** Gereksiz karmaşıklığı önlemek adına bu ekranda `State`, `Network`, `ViewModel`, `Repository` veya `Business Logic` kullanılmamıştır. Alanlar stateless (statik) olarak bırakılmıştır.
*   **Bağımlılık Yönetimi:** Proje dosyalarına herhangi bir yeni `dependency` (kütüphane) eklenmemiştir. Mevcut Jetpack Compose Material 3 kütüphanesi yeterli görülmüştür.
*   **Tema ve Tipografi Uyumu:** Ekranın tasarımı, projenin temelini oluşturan `LyraApp Theme` ve `LyraTypography` (Roboto tabanlı M3 sistemi) ile tam uyumlu şekilde geliştirilmiştir.
*   **Paket Yapısı:** Proje standartlarına uygun olarak `com.example.lyraapp` paket yapısı korunmuştur.
*   **Teknoloji Seçimi:** Bu aşamada projeye `Hilt`, `Navigation` veya henüz ihtiyaç duyulmayan diğer ek teknolojiler dahil edilmemiştir.

---
*Son Güncelleme: Ocak 2024*
