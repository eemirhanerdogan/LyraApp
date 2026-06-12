# Mimari ve Tasarım Kararları

Bu doküman, LyraApp projesinin geliştirme sürecinde alınan teknik kararları ve uygulanan kısıtlamaları listeler.

## 1. Login Ekranı Geliştirmesi
*   **Sadece UI Odaklı Geliştirme:** Login ekranı yalnızca görsel arayüz (UI) katmanı olarak tasarlanmıştır. 
*   **Mimari Kısıtlamalar:** Bu aşamada `State`, `Network`, `ViewModel`, `Repository` veya `Business Logic` kullanılmamıştır.
*   **Bağımlılık Yönetimi:** Projeye herhangi bir yeni `dependency` eklenmemiştir.
*   **Tema ve Tipografi Uyumu:** `LyraApp Theme` ve `LyraTypography` sistemi ile tam uyumlu geliştirilmiştir.
*   **Paket Yapısı:** `com.example.lyraapp` paket yapısı korunmuştur.

## 2. MVI Mimari Kararları (Planlanan)
Projenin ölçeklenebilirliği ve test edilebilirliği için aşağıdaki mimari kararlar alınmıştır:

*   **Mimarinin Seçimi:** Projede **MVI (Model-View-Intent)** mimarisi kullanılacaktır.
*   **Single Source of Truth:** Her ekranın tek bir `State` nesnesi olacaktır.
*   **Unidirectional Data Flow (UDF):** Veri akışı tek yönlü (Intent -> ViewModel -> State/Effect -> UI) olacaktır.
*   **Bağımlılık Enjeksiyonu (İleriki Adım):** Projeye ilerleyen aşamalarda **Hilt** entegrasyonu yapılması planlanmaktadır.
*   **Navigasyon (İleriki Adım):** Ekranlar arası geçişler için **Jetpack Compose Navigation** kullanımı planlanmaktadır.
*   **Veri Katmanı:** Backend servisleri hazır olana kadar **Fake Repository** (Interface + Implementation) yaklaşımı kullanılacaktır.
*   **Paketleme:** Tüm MVI bileşenleri `com.example.lyraapp` paketi altında organize edilecektir.
