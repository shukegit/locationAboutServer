package com.android.location.utils;

public class RegionJudgeUtil {
	
	/**
	 * 
	 * @param sLongitude �����˾���
	 * @param sLatitude  ������γ��
	 * @param radius     ǩ���뾶
	 * @param rLongitude ǩ���˾���
	 * @param rLatitude  ǩ����γ��
	 * @return           �ڷ�Χ����true
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
