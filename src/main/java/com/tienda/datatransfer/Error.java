package com.tienda.datatransfer;

public class Error {

	private final String code;
	private final String description;

	public Error(String code, String description) {

		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
