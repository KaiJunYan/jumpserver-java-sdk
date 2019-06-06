package com.jumpserver.sdk.v2.common;

import com.google.common.base.MoreObjects;

import java.io.Serializable;


public class ActionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private int code;

	private ActionResponse(int code) {
	    this.code = code;
	}

	private ActionResponse(String message, int code) {
	    this(code);
		this.message = message;
	}

    public static ActionResponse actionSuccess(int code)  {
        return new ActionResponse(code);
    }

    public static ActionResponse actionSuccess() {
		return new ActionResponse(200);
	}

	public static ActionResponse actionFailed(String message, int code) {
		return new ActionResponse(message, code);
	}


	public int getCode() {
	    return code;
	}


	public boolean isSuccess() {
		return message == null;
	}


	public String getFault() {
		return message;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).
                omitNullValues().
                add("success", message == null).
                add("fault", message).
                add("code", code).
                toString();
	}
}
