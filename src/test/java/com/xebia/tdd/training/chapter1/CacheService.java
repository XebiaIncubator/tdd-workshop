package com.xebia.tdd.training.chapter1;

import java.util.HashMap;
import java.util.Map;

public class CacheService {

	private boolean returnBlankForNull = false;
	private Map<String, String> keyValueMap = new HashMap<String, String>();

	public String get(String key) {
		String value = keyValueMap.get(key);
		if (null == value) {
			if (!returnBlankForNull) {
				value = null;
			} else {
				value = "";
			}
		}

		return value;
	}

	public void setReturnBlankForNull(boolean returnNullForBlank) {
		this.returnBlankForNull = returnNullForBlank;
	}
}
