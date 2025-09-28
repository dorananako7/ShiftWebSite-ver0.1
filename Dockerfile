#ビルドで使用するイメージ
FROM maven:3.8.5-openjdk-17 AS builder

#コマンド実行する作業ディレクトリー
WORKDIR /app

#プロジェクトのファイルをすべてコピー
COPY . .

#jarを作成
RUN mvn clean package -DskipTests

#実行で使用するイメージ
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

#作成された.jarをビルドで使用したイメージから実行イメージに移す
COPY --from=builder /app/target/*.jar app.jar
#SpringBootが使用するポートをベースイメージに伝える
EXPOSE 8080

#ビルドされたイメージからコマンドを実行
ENTRYPOINT ["java", "-jar", "app.jar"]
#ここをRUNにしてしまうと、イメージのビルドが終わってないのに実行されてしまう。