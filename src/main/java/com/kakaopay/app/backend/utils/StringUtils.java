package com.kakaopay.app.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	public static List<Long> toDoListRefId(String str) {
		List<Long> list = new ArrayList<Long>();
		String[] arr = str.split("@");
		for(int i=1; i < arr.length; i++) {
			list.add(Long.parseLong(arr[i]));
		}
		return list;
	}
}
