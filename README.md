# TodoApp

Basit bir yapılacaklar listesi (To-Do List) uygulaması. Kullanıcılar görev ekleyebilir, güncelleyebilir ve silebilir.

## 🚀 Özellikler

- Görev ekleme
- Görev silme
- Görev tamamlama
- MySQL veritabanı entegrasyonu
- RESTful API

## 📦 Kullanılan Teknolojiler

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Swagger


## 🛠️ Kurulum

1. Bu projeyi klonlayın:

    ```bash
    git clone https://github.com/kullaniciadi/TodoApp.git
    cd TodoApp
    ```

2. MySQL veritabanını oluşturun:

    ```sql
    CREATE DATABASE todo_db;
    ```

3. `application.properties` dosyasını açın ve veritabanı bilgilerinizi girin:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
    spring.datasource.username=veritabani_kullanici_adi
    spring.datasource.password=veritabani_sifresi
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4. Maven bağımlılıklarını yükleyin ve projeyi başlatın:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

5. Uygulamayı başlatın:

    ```bash
    ./mvnw spring-boot:run
    ```




