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

## 🤝 Katkıda Bulunma

1. Bu repoyu fork’layın
2. Yeni bir branch oluşturun:

```bash
git checkout -b ozellik-adi


## 🛠️ Kurulum

1. Bu projeyi klonlayın:

```bash
git clone https://github.com/kullaniciadi/TodoApp.git
cd TodoApp

CREATE DATABASE todo_db;



spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=veritabani_kullanici_adi
spring.datasource.password=veritabani_sifresi
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true



./mvnw clean install


./mvnw spring-boot:run
