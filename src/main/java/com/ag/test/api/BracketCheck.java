package com.ag.test.api;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Component
public final class BracketCheck {

    private static final Map<Character, Character> allowedBrackets = new HashMap<Character, Character>();
    static {
        allowedBrackets.put('(', ')');
        allowedBrackets.put('{', '}');
        allowedBrackets.put('[', ']');
    }

    private BracketCheck() {
    }

    /**
     * Checks to see if the input string has balanced brackets.  Brackets allowed are:
     *  1) Round bracket e.g. ( )
     *  2) Curly bracket e.g. { }
     *  3) Square bracket e.g. [ ]
     *
     * @param input any string to be checked
     * @return true if brackets are balanced and false otherwise
     *
     */
    static boolean isBalanced(String input) {
        final Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < input.length(); i++) {
            char charToCheck = input.charAt(i);
            if (allowedBrackets.containsKey(charToCheck)) {
                stack.push(charToCheck);
            } else if (allowedBrackets.containsValue(charToCheck)) {
                if (stack.empty() || (charToCheck != allowedBrackets.get(stack.pop()))) {
                    return false;
                }
            }
        }
        return stack.empty();
    }


}
