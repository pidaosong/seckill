package com.pi.miaosha.result;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 13:50
 **/
public class Result<T> {

	private int code;
	private String msg;
	private T data;

	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}

	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeEnums codeEnums){
		return new Result<T>(codeEnums);
	}

	private Result(T data) {
		this.data = data;
	}

	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private Result(CodeEnums codeEnums) {
		this.code = codeEnums.getCode();
		this.msg = codeEnums.getMsg();
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
