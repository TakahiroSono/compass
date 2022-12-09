# 学校API

## 実行手順

1. 初回実行の場合はアプリケーションのビルドを行う
   1. `cd demo`
   2. `./gradlew build`
   3. 実行できない場合以下のいずれか、または全部を実行する
      1. `bash ./gradlew build`
      2. `chmod +x gradlew`
2. docker の起動を行う
   1. `cd ..`(アプリケーションのビルドのためにdemoに移動している場合は階層をもどる)
   2. `docker compose up` バックグラウンドで実行したい場合は`-d`のオプションを付ける
3. 動作確認を終了したら docker を停止させる
   1. `docker compose down`

## アーキテクチャ

本アプリケーションはDDDを模して簡易的なアーキテクチャで実装している。

### Controller

APIにアクセスが来たときのパスの振り分けを行っている。

### Repository

DBに問い合わせを行い、アプリケーション用のオブジェクトに変換して返す。
SQLを生成するMapperとオブジェクトに変換するRepositoryが存在する。

### Entity

アプリケーションで扱うデータの雛形を定義。
ドメインから、アプリケーションで使用するデータのみを抽出している。

### DTO

データベースから返却されるデータ型を定義。

### DB

初期化用のSQLを`/compass/mysql/init.sql`に記載している。
データを追加したい場合は初期化用SQLに追記し docker 内の Volume を破棄してから再度アプリケーションを起動させる。

## 動作検証用コマンド

- `curl -XGET "localhost:8080/student?facilitator_id=1"`
  - 先生のIDで検索
- `curl -XGET "localhost:8080/student?facilitator_id=1&sort=loginId"`
  - 先生のIDと生徒のログインIDで検索
- ` curl -XGET "localhost:8080/student?facilitator_id=1&sort=loginId&order=desc"`
  - 先生のIDと生徒のログインIDを降順で検索
- ` curl -XGET "localhost:8080/student?facilitator_id=1&name_like=D"`
  - 先生のIDと名前に`D`が含まれている生徒で検索
- `curl -XGET "localhost:8080/student?facilitator_id=1&limit=3&page=1"`
  - 先生のIDと生徒の人数を3人づつに分けたときの2組目を検索
