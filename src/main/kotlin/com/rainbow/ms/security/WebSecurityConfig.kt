package com.rainbow.ms.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authFilter: AuthFilter

    override fun configure(http: HttpSecurity) {

        http.csrf().disable()

        http.authorizeRequests()
                .antMatchers(*WhiteList.get().map { "$it/**" }.toTypedArray()).permitAll()
                .anyRequest().authenticated()

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}