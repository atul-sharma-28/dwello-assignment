package com.atulsharma.dwelloassignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DwelloAssignmentQuestion4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(br.readLine());
        String[] inputs = new String[lines];
        for(int i=0; i < lines; i++){
            inputs[i] = br.readLine();
        }
        DwelloAssignmentQuestion4 application = new DwelloAssignmentQuestion4();
        int[] results = application.adorableCount(inputs);
        for(int result:results) {
            System.out.println(result);
        }
    }

    public int[] adorableCount(String[] words) {
        int[] result = new int[words.length];
        for(int i=0;i<words.length;i++) {
            int adorableSubstings = 0;
            Matcher m = Pattern.compile("(?=((?:[a-z][a-z0-1:]*\\/[a-z0-9]+\\\\)([a-z]+)))").matcher(words[i]);
            while (m.find()) {
                for (int j = 1; j <= m.groupCount(); j++) {
                    Matcher matcher = Pattern.compile("^[a-z]+$").matcher(m.group(j));
                    if(matcher.matches()){
                        adorableSubstings += m.group(j).length();
                    }
                }
            }
            result[i] = adorableSubstings;
        }
        return result;
    }
}