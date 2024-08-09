package com.core.backend.point.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PointConversionResponse {

	private Long balance;
	private Long point;

	public static PointConversionResponse of(Long balance, Long point) {
		return new PointConversionResponse(balance, point);
	}
}
