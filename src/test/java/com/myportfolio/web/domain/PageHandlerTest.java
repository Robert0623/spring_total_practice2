package com.myportfolio.web.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void pageHandlerTest() {
        PageHandler ph = new PageHandler(250, 1);
        ph.print();
        assertTrue(ph.getBeginPage() ==1);
        assertTrue(ph.getEndPage() ==10);
        assertTrue(ph.isShowPrev() ==false);
        assertTrue(ph.isShowNext() ==true);
    }
    @Test
    public void pageHandlerTest2() {
        PageHandler ph = new PageHandler(250, 11);
        ph.print();
        assertTrue(ph.getBeginPage() ==11);
        assertTrue(ph.getEndPage() ==20);
        assertTrue(ph.isShowPrev() ==true);
        assertTrue(ph.isShowNext() ==true);
    }
    @Test
    public void pageHandlerTest3() {
        PageHandler ph = new PageHandler(255, 25);
        ph.print();
        assertTrue(ph.getBeginPage() ==21);
        assertTrue(ph.getEndPage() ==26);
        assertTrue(ph.isShowPrev() ==true);
        assertTrue(ph.isShowNext() ==false);
    }
    @Test
    public void pageHandlerTest4() {
        PageHandler ph = new PageHandler(255, 10);
        ph.print();
        assertTrue(ph.getBeginPage() ==1);
        assertTrue(ph.getEndPage() ==10);
        assertTrue(ph.isShowPrev() ==false);
        assertTrue(ph.isShowNext() ==true);
    }
}