package com.sapporo.library.constans;

public class EnumConstans {
	
	/**
	 * 定数クラス
	 * 
	 * 
	 */
	
	public enum Common {
		
		DEVELOP_FRONT_HOST("https://library-front-61849.web.app/"),
		PRODUCTION_FRONT_HOST("");
		
        private String value;
		
		private Common(String obj) {
			this.value = obj;
		}
		
		public Object getValue() {
			return value;
		}
		
	}
	
	public enum BookRegistMessage {
		
		SUCCESS("書籍登録機能【処理成功】", "登録に成功しました", 201),
		EMPTY("書籍登録機能【パラメーターなし】", "未入力の項目があります。", 400),
		NOTEXSITS("書籍登録機能【IDが未存在】", "該当の書籍が存在しません。", 400),
		SERVER_ERROR("書籍登録機能【サーバーエラー】", "システムエラーが発生しました。", 500);
		
		private String log;
		private String message;
		private int status;

		private BookRegistMessage(String log, String message, int status) {
			this.log = log;
			this.message = message;
			this.status = status;
		}

		public String getLog() {
			return log;
		}

		public String getMessage() {
			return message;
		}

		public int getStatus() {
			return status;
		}

	}
	
	public enum BookGetMessage {
		
		SUCCESS("書籍データ取得機能【書籍データ取得成功】", "書籍データ取得に成功しました。", 200),
		NOTEXSITS("書籍データ取得機能【IDが未存在】", "該当の書籍が存在しません。", 400),
		SERVER_ERROR("書籍データ取得機能【サーバーエラー】", "システムエラーが発生しました。", 500);

		private String log;
		private String message;
		private int status;
	
		private BookGetMessage(String log, String message, int status) {
			this.log = log;
			this.message = message;
			this.status = status;
		}
		
		public String getLog() {
			return log;
		}

		public String getMessage() {
			return message;
		}

		public int getStatus() {
			return status;
		}
	}
}


