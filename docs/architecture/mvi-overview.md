# MVI Mimarisine Genel Bakış

LyraApp projesinde kullanılan MVI (Model-View-Intent) mimarisi, Tek Yönlü Veri Akışını (Unidirectional Data Flow - UDF) temel alır.

## Temel Bileşenler

*   **Model (State):** Ekranın herhangi bir andaki durumunu temsil eden tek doğruluk kaynağıdır.
*   **View (UI):** State'i izleyen ve kullanıcı etkileşimlerini Intent olarak ViewModel'e ileten katmandır.
*   **Intent:** Kullanıcının veya sistemin gerçekleştirmek istediği eylemleri temsil eder.

## Veri Akışı Döngüsü

1.  **UI**, kullanıcının bir eylemini (örneğin butona tıklama) bir **Intent** olarak yakalar.
2.  **ViewModel**, gelen Intent'i alır ve gerekli business logic'i (Repository çağrısı vb.) işletir.
3.  İşlem sonucunda ViewModel yeni bir **State** üretir veya bir yan etki (**Effect**) tetikler.
4.  **UI**, yeni State'i gözlemleyerek arayüzü günceller.

## Temel Kurallar

*   UI katmanı asla business logic içermez.
*   Veri akışı her zaman tek yönlüdür.
*   Tüm bileşenler `com.example.lyraapp` paket yapısına uygun olarak organize edilir.
*   Repository katmanı, ViewModel'den interface aracılığıyla soyutlanır.
