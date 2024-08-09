package com.core.backend.point.ui.dto;

import com.core.backend.point.domain.Point;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PointInfoResponse {

	private Long pointId;
	private String name;
	private Long point;

	public static PointInfoResponse of(String name, Point point) {
		return new PointInfoResponse(point.getId(), name, point.getPoint());
	}
}
