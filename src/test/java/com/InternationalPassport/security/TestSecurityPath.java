package com.InternationalPassport.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

import static org.junit.Assert.assertTrue;


public class TestSecurityPath {

    @Test
    public void pathResourceTest() {
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        assertTrue(antPathMatcher.match("/resources/**", "/resources/bootstrapComponent"));
    }

    @Test
    public void pathMainTest() {
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        assertTrue(antPathMatcher.match("/**", "/main"));
    }

    @Test
    public void pathMainPageTest() {
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        assertTrue(antPathMatcher.match("/views/*", "/views/main"));
    }
}
