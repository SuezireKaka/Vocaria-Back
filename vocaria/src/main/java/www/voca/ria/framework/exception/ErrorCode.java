package www.voca.ria.framework.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

	BUSINESS_EXCEPTION_ERROR(200, "B999", "Business Exception Error"),

	/**
	 * ******************************* Custom Error CodeList
	 * ***************************************
	 */
	WRONG_PWD(200, "9999", "Wrong password Exception"),
	// Transaction Insert Error
	INSERT_ERROR(200, "9999", "Insert Transaction Error Exception"),

	// Transaction Update Error
	UPDATE_ERROR(200, "9999", "Update Transaction Error Exception"),

	// Transaction Delete Error
	DELETE_ERROR(200, "9999", "Delete Transaction Error Exception"),
	
	STRANGE_DATE(200, "9998", "Strange Date-Format Exception"),
	
	INVALID_STRATEGY(200, "9998", "Invalid Strategy Exception"),
	
	NOT_ACCESSIBLE(200, "9998", "Not Accessible Voca Exception"),
	
	NOT_CONCERNED(200, "9999", "Not Concerned Exception"),
	
	NOT_TOGGLABLE(200, "9999", "Not Togglable Exception"),
	
	INVALID_BODY(200, "9998", "Invalid Body Exception"),

	; // End

	/**
	 * ******************************* Error Code Constructor
	 * ***************************************
	 */
	// 에러 코드의 '코드 상태'을 반환한다.
	private int status;

	// 에러 코드의 '코드간 구분 값'을 반환한다.
	private String divisionCode;

	// 에러 코드의 '코드 메시지'을 반환한다.
	private String message;

	// 생성자 구성
	ErrorCode(final int status, final String divisionCode, final String message) {
		this.status = status;
		this.divisionCode = divisionCode;
		this.message = message;
	}
}