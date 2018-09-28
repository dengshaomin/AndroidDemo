package com.code.myapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by xujintian on 2017/2/15.
 */

public class SpaceInputFilter implements InputFilter {

    protected Pattern doubleReturnPattern = Pattern.compile("[\\S\\s*]*[\n\r]{2}");

    /**
     * 匹配 任意内容+换行+不定量的空格+换行
     */
    protected Pattern singleReturnMultiSpace = Pattern.compile("[\\S\\s*]*[\n\r][\f\b\t ]*[\n\r]");

    protected CharSequence trimHeadEmpty(CharSequence source, int start, int end) {
        if (source.length() <= 0) {
            return source;
        }
        int firstNonEmpty = findFirstNonEmpty(source);
        if (firstNonEmpty == -1) {
            return "";
        }
        firstNonEmpty=Math.max(firstNonEmpty, start);
        if (firstNonEmpty == 0 && end == source.length()) {
            return source;
        }
        return source.subSequence(firstNonEmpty, end);
    }

    protected CharSequence trimHeadReturn(CharSequence source, int start, int end) {
        if (source.length() <= 0) {
            return source;
        }
        int firstNonReturn = findFirstNonReturn(source);
        if (firstNonReturn == -1) {
            return "";
        }
        firstNonReturn=Math.max(firstNonReturn, start);
        if (firstNonReturn == 0 && end == source.length()) {
            return source;
        }
        return source.subSequence(firstNonReturn, end);
    }

    protected int findFirstNonEmpty(CharSequence source) {
        int firstNonEmpty = -1;
        for (int i = 0; i < source.length(); i++) {
            if (!checkCharEmpty(source.charAt(i))) {
                firstNonEmpty = i;
                break;
            }
        }
        return firstNonEmpty;
    }

    protected int findFirstNonReturn(CharSequence source) {
        int firstNonEmpty = -1;
        for (int i = 0; i < source.length(); i++) {
            if (!checkCharReturn(source.charAt(i))) {
                firstNonEmpty = i;
                break;
            }
        }
        return firstNonEmpty;
    }

    protected boolean checkCharEmpty(char c) {
        return ' ' == c || '\t' == c || '\r' == c || '\n' == c || '\f' == c || '\b' == c;
    }

    protected boolean checkCharReturn(char c) {
        return '\r' == c || '\n' == c;
    }

    protected boolean checkCharSpace(char c) {
        return ' ' == c || '\t' == c || '\f' == c || '\b' == c;
    }

    protected boolean checkStringEmpty(String string) {
        return string.startsWith(" ")
                || string.startsWith("\t")
                || string.startsWith("\r")
                || string.startsWith("\n")
                || string.startsWith("\f")
                || string.startsWith("\b");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (dest.length() <= 0) {
            return trimHeadEmpty(source, start, end);
        }
        if (dest.length() > 2) {
            String destStr = dest.toString();
//            Matcher matcher = doubleReturnPattern.matcher(destStr);
//            if (matcher.matches()) {
//                return trimHeadEmpty(source, start, end);
//            }
            Matcher matcher = singleReturnMultiSpace.matcher(destStr);
            if (matcher.matches()) {
                return trimHeadEmpty(source, start, end);
            }
        }
        return source;
    }
}