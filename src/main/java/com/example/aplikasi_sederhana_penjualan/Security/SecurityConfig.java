package com.example.aplikasi_sederhana_penjualan.Security;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

@Configuration
public class SecurityConfig {

    @Bean
    public SecretKey secretKey() {
        return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    }

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter(SecretKey secretKey) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthFilter(secretKey));
        registrationBean.addUrlPatterns("/buku/*", "/transaksi/*"); 
        return registrationBean;
    }
}
