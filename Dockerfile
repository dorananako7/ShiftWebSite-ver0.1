#ベースイメージ(iso?)の指定
FROM eclipse-temurin:17-jdk

#コマンド実行する作業ディレクトリー
WORKDIR /app

#mvn packageでjar化したファイルをベースイメージのカレントディレクトリにコピー
COPY target/shift-management-0.0.1-SNAPSHOT.jar /app/app.jar

#SpringBootが使用するポートをベースイメージに伝える
EXPOSE 8080

#Dockerが起動したときに実行されるコマンド
RUN java -jar /app/app.jar
