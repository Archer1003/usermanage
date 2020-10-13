package com.cserver.saas.system.user.util;

import java.util.Map;

public class PageUtil {
	public static int getPageSize(Map<String, Object> map) {
		int pageSize = 0;
		if(null != map && null != map.get("pageSize")) {
			pageSize = (Integer) map.get("pageSize");
		}
		return pageSize;
	}
	public static int getStartRow(Map<String, Object> map) {
		int startRow = -1;
		if(null != map && null != map.get("startRow")) {
			startRow = (Integer) map.get("startRow");
		}
		return startRow;
	}
}
