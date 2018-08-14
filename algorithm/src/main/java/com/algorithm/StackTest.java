package com.algorithm;

import org.junit.Test;

public class StackTest {
    //分隔符匹配
//遇到左边分隔符了就push进栈，遇到右边分隔符了就pop出栈，看出栈的分隔符是否和这个有分隔符匹配
    @Test
    public void testMatch() {

        ArrayStack stack = new ArrayStack(3);
        String str = "12<a[b{c}]>";
        char[] cha = str.toCharArray();
        for (char c : cha) {
            switch (c) {
                case '{':
                case '[':
                case '<':
                    stack.push(c);
                    break;
                case '}':
                case ']':
                case '>':
                    if (!stack.isEmpty()) {
                        char ch = stack.pop().toString().toCharArray()[0];
                        if (c == '}' && ch != '{'
                                || c == ']' && ch != '['
                                || c == ')' && ch != '(') {
                            System.out.println("Error:" + ch + "-" + c);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
