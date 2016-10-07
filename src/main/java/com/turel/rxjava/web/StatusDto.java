package com.turel.rxjava.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaimturkel
 * @since Jan 11, 2016
 */
@JsonInclude(Include.NON_NULL)
public class StatusDto {

	public final static String SUCCESS = "success";
	public final static String ERROR = "error";
	
	@JsonProperty
	public int s = 0;
	
	@JsonProperty
	public String error;
	
	public StatusDto() {}
	
	public StatusDto(String errorReason) {
		this();
		s = 1;
		error = errorReason;
	}

	public StatusDto(String result, int s) {
		this();
		this.s = s;
		error = result;
	}

	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		return "StatusDto{" +
				"s=" + s +
				", error='" + error + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StatusDto statusDto = (StatusDto) o;

		if (s != statusDto.s) return false;
		return error != null ? error.equals(statusDto.error) : statusDto.error == null;

	}

	@Override
	public int hashCode() {
		int result = s;
		result = 31 * result + (error != null ? error.hashCode() : 0);
		return result;
	}
}
