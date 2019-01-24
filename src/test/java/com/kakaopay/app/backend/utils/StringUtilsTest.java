package com.kakaopay.app.backend.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class StringUtilsTest {
	
	@Test
	public void toDoListRefKeyTest() {
		String s = "할일@1@3@4";
		List<Long> list = StringUtils.toDoListRefId(s);
		
		assertThat(list.get(0), is(1L));
		assertThat(list.get(1), is(3L));
		assertThat(list.get(2), is(4L));
	}
}
