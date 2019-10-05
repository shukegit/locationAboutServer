package com.android.location.utils;

public class RegionJudgeUtil {
	
	/**
	 * 
	 * @param sLongitude 发起人经度
	 * @param sLatitude  发起人纬度
	 * @param radius     签到半径
	 * @param rLongitude 签到人经度
	 * @param rLatitude  签到人纬度
	 * @return           在范围返回true
	 */
	public static boolean isInRegion(Double sLongitude, Double sLatitude, Double radius, Double rLongitude, Double rLatitude) {
		
		Double a = 0.0;
		Double b = 0.0;
		Double c = 0.0;
		a = Math.abs(rLongitude - sLongitude);
		b = Math.abs(rLatitude - sLatitude);
		c = radius;
		if(a*a + b*b > c*c) {
			return false;
		} else {
			return true;
		}
	}

}
