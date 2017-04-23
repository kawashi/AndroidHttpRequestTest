# HttpRequestTest

AndroidでHTTPリクエストを外部に送信するアプリケーション。  
全然関係無いけど、preferenceに値を保存する機能も付いてる。

# バージョン

Android 7.0

# システム概要

* 「リクエストを送信」を押すと、天気情報のXMLを取得する
* 名前を入力して「preferenceに保存」を押すとpreferenceに保存される
* 更新を押すと、preferenceから値を取り出して表示する

# 学べること

* 外部通信
* 非同期処理 (マルチスレッド)
* SharedPreferences
* ラムダ式
* EditText

# 学べないこと

* センサ関連

# 学んだこと

* 外部通信は非同期じゃないとエラー出る
* UIの更新はUIスレッドじゃないとエラー出る
* 外部通信はManifestで許可取らないと駄目
* ラムダ式使うにはbuild.gradleでJava8の設定とjackオプションの設定が必要
* SharedPreferenceで値の保持が出来る (Cookieみたいな感じ)