package com.example.dagger2example.module;

import com.example.dagger2example.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

//TODO 3- Şimdi module ile inject’i birbirine bağlayacak olan Component arayüzünü yazıyoruz.

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity activity);
}

/* TODO 3 '
MainActivity sınıfı field injection metodunu kullanıyor.
Constructor injection otomatik gerçekleşirken, field injection elle tetiklenmelidir.
Bu nedenle component sınıfı içine MainActivity’i parametre olarak alan bir inject metodu yazıyoruz.
Bu metot çağrıldığında MainActivity içine inject edilen değişkenlere değerleri atanır,
bu metot çağrılana kadar değişkenler null olur.
*/
