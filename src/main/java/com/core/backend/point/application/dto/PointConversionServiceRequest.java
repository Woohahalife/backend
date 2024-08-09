package com.core.backend.point.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PointConversionServiceRequest {

	private Long amount;

	public static PointConversionServiceRequest from(Long amount) {
		return new PointConversionServiceRequest(amount);
	}
}
