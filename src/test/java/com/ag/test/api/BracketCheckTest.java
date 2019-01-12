package com.ag.test.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BracketCheckTest {


    @Test
    public void testBalance() {

        assertThat( BracketCheck.isBalanced(""), is(true));
        assertThat( BracketCheck.isBalanced("(abc)12345"), is(true));
        assertThat( BracketCheck.isBalanced("{(testing)basdfsdf}"), is(true));
        assertThat( BracketCheck.isBalanced("{(testing]])basdfsdf}"), is(false));
        assertThat( BracketCheck.isBalanced("()[]{}[][]"), is(true));
        assertThat( BracketCheck.isBalanced("{}([])"), is(true));
        assertThat( BracketCheck.isBalanced("([}])"), is(false));
        assertThat( BracketCheck.isBalanced("{(testing){}{}{}{P}{P}{fsdf}"), is(false));
        assertThat( BracketCheck.isBalanced("{(([[["), is(false));
    }
}