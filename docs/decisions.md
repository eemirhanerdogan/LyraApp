# Mimari ve Tasarım Kararları

Bu doküman, LyraApp projesinin geliştirme sürecinde alınan teknik kararları ve uygulanan kısıtlamaları listeler.

## 1. Login Ekranı Geliştirmesi
*   **Sadece UI Odaklı Geliştirme:** Login ekranı yalnızca görsel arayüz (UI) katmanı olarak tasarlanmıştır. 
*   **Mimari Kısıtlamalar:** Bu aşamada `State`, `Network`, `ViewModel`, `Repository` veya `Business Logic` kullanılmamıştır.
*   **Bağımlılık Yönetimi:** Projeye herhangi bir yeni `dependency` eklenmemiştir.
*   **Tema ve Tipografi Uyumu:** `LyraApp Theme` ve `LyraTypography` sistemi ile tam uyumlu geliştirilmiştir.
*   **Paket Yapısı:** `com.example.lyraapp` paket yapısı korunmuştur.

## 2. MVI Mimari Kararları
Projenin ölçeklenebilirliği ve test edilebilirliği için aşağıdaki mimari kararlar alınmıştır:

*   **Mimarinin Seçimi:** Projede **MVI (Model-View-Intent)** mimarisi kullanılacaktır.
*   **Single Source of Truth:** Her ekranın tek bir `State` nesnesi olacaktır.
*   **Unidirectional Data Flow (UDF):** Veri akışı tek yönlü (Intent -> ViewModel -> State/Effect -> UI) olacaktır.
*   **Bağımlılık Enjeksiyonu:** Projeye **Hilt (v2.59.2)** entegrasyonu yapılmıştır. Temel kurulum (`LyraApplication` ve `MainActivity` yapılandırması) tamamlanmıştır.
*   **Annotation Processing:** Daha hızlı derleme ve Kotlin 2.2.10 uyumu için **KSP (v2.2.10-2.0.2)** seçilmiştir.
*   **Navigasyon (Planlanan):** Ekranlar arası geçişler için **Jetpack Compose Navigation (v2.8.5)** kullanımı planlanmaktadır.
*   **Lifecycle Yönetimi:** State'lerin güvenli tüketimi için `lifecycle-runtime-compose` kütüphanesi eklenmiştir.
*   **Veri Katmanı:** Backend servisleri hazır olana kadar **Fake Repository** (Interface + Implementation) yaklaşımı kullanılacaktır.
*   **Paketleme:** Tüm MVI bileşenleri `com.example.lyraapp` paketi altında organize edilecektir.
*   **Gradle Yapılandırması:** AGP 9 ve KSP uyumu için `android.disallowKotlinSourceSets=false` ayarı `gradle.properties` dosyasına eklenmiştir.
