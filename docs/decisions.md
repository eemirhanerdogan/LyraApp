# Mimari ve Tasarım Kararları

Bu doküman, LyraApp projesinin geliştirme sürecinde alınan teknik kararları ve uygulanan kısıtlamaları listeler.

## 1. Ekran Geliştirmeleri
*   **MVI Geçişi:** Login, Register ve Home ekranları Jetpack Compose Material 3 kullanılarak MVI (Model-View-Intent) mimarisine uygun şekilde yapılandırılmıştır.
*   **State Yönetimi:** Her ekranın durumu (LoginUiState, RegisterUiState, HomeUiState) kendi contract dosyalarında tanımlanmış olup tek bir noktadan yönetilmektedir.
*   **Navigasyon:** **Jetpack Compose Navigation** aktif hale getirilmiştir. `LyraNavHost` üzerinden Login, Register ve Home ekranları birbirine bağlanmıştır.
*   **Back Stack Yönetimi:** Kimlik doğrulama akışı (Login/Register) tamamlandığında veya çıkış yapıldığında back stack uygun şekilde temizlenerek (`popUpTo`, `inclusive = true`) kullanıcı deneyimi ve uygulama güvenliği korunmuştur.
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
*   **Navigasyon:** **Jetpack Compose Navigation (v2.8.5)** kullanılarak ekranlar arası geçişler merkezi olarak yönetilmektedir.
*   **Lifecycle Yönetimi:** State'lerin güvenli tüketimi için `lifecycle-runtime-compose` kütüphanesi eklenmiştir.
*   **Veri Katmanı:**
    *   **Fake Repository:** Backend servisleri henüz hazır olmadığı için `AuthRepository` için `FakeAuthRepository` implementasyonu oluşturulmuştur.
    *   **Soyutlama:** ViewModel katmanı doğrudan `AuthRepository` arayüzüne bağımlıdır. Gerçek API entegrasyonu yapıldığında, ViewModel ve UI katmanına dokunulmadan sadece repository implementasyonu değiştirilecektir.
*   **Paketleme:** Tüm MVI bileşenleri `com.example.lyraapp` paketi altında organize edilecektir.
*   **Gradle Yapılandırması:** AGP 9 ve KSP uyumu için `android.disallowKotlinSourceSets=false` ayarı `gradle.properties` dosyasına eklenmiştir.
