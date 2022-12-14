В application.properties внести значения:

spring.datasource.url = jdbc:postgresql://localhost:5432/shop_db_release
spring.datasource.username = postgres

spring.datasource.password = INSERT_YOUR_PASSWORD

spring.jpa.show-sql = true
spring.jpa.properties.hibernate.format_sql = true
spring.jpa.generate-ddl = true
spring.main.allow-bean-definition-overriding = true
spring.main.allow-circular-references = true

upload.path = INSERT_PATH_TO_UPLOADS_DIR

Обратить внимание на значение INSERT_......
