package com.sapporo.library.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SapporoLibraryLoginData {
	public static String getLogindEmail() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// JWTAuthenticationFilter#successfulAuthenticationで設定したusernameを取り出す
		String email = (String) (authentication.getPrincipal());

		return email;
	}
}
