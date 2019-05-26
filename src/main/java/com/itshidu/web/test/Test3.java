package com.itshidu.web.test;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

public class Test3 {

	public static void main(String[] args) {
		String a = "<script>alert('Hello世界')</script>";
		a=StringEscapeUtils.escapeHtml4(a);
		System.out.println(a);
		a= StringEscapeUtils.unescapeHtml4(a);
		System.out.println(a);
		
	}
}
