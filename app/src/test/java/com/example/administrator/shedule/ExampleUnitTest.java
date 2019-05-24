package com.example.administrator.shedule;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String s = "高等数学AⅠ[17] 4-18周 (第1-2节) 王春雨 2号楼 2-0201";
        String[] info = s.split(" ");
        System.out.println(info[2]);
        System.out.println(info[2].substring(info[2].indexOf('-') + 1, info[2].indexOf('周')));
    }
}