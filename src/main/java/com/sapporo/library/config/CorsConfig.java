/**
 * 
 */
package com.sapporo.library.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sapporo.library.constans.EnumConstans.Common;

/**
 * CORS設定クラス
 * 
 * @author kazuma
 *
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		
		String develop = Common.DEVELOP_FRONT_HOST.getValue().toString();
		String production = Common.PRODUCTION_FRONT_HOST.getValue().toString();
		
        registry.addMapping("/**")
                .allowedOrigins(develop, production);
}
}