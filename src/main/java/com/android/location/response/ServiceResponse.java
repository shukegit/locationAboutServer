package com.android.location.response;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServiceResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -281135549093535703L;
	private int status;
	private String message;
	private T data;
	
	private ServiceResponse(int status) {
		this.status = status;
	}
	private ServiceResponse(String message) {
		this.message = message;
	}
	private ServiceResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
	private ServiceResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	private ServiceResponse(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}
	
	/**
	 * 鍙紶鎴愬姛鐘舵�佹爣蹇�
	 * @return
	 */
	public static <T> ServiceResponse<T> createBySuccess() {
		return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	/**
	 * 浼犳垚鍔熺姸鎬佹爣蹇椾互鍙婅嚜瀹氫箟淇℃伅
	 * @param message
	 * @return
	 */
	public static <T> ServiceResponse<T> createBySuccessMessage(String message) {
		return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(), message);
	}
	/**
	 * 浼犳垚鍔熺姸鎬佹爣蹇椾互鍙婃暟鎹�
	 * @param data
	 * @return
	 */
	public static <T> ServiceResponse<T> createBySuccessData(T data) {
		return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}
	/**
	 * 浼犳垚鍔熺姸鎬佹爣蹇椼�佽嚜瀹氫箟淇℃伅銆佹暟鎹�
	 * @param message
	 * @param data
	 * @return
	 */
	public static <T> ServiceResponse<T> createBySuccessMessageAndData(String message, T data) {
		return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(), message, data);
	}	
	public static <T> ServiceResponse<T> createByError() {
		return new ServiceResponse<T>(ResponseCode.ERROR.getCode());
	}
	public static <T> ServiceResponse<T> createByErrorMessage(String message) {
		return new ServiceResponse<T>(ResponseCode.ERROR.getCode(), message);
	}
	public static <T> ServiceResponse<T> createByErrorCodeAndMessage(Integer code, String message) {
		return new ServiceResponse<T>(code, message);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public T getData() {
		return data;
	}
	
	
}
