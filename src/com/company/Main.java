package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        getMinBoat(people, 100);
        getMaxNumber("4177252841", 4);
    }

    public static int getMinBoat(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int i = 0;
        for (int j = people.length -1; j >= i; j--) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            answer++;
        }
        return answer;
    }

//  주어진 숫자의 순서는 바꾸지 않는다
//  앞에서 부터 숫자를 확인해서 새로 만들 숫자 배열에 넣는다.
//  우선 넣고 뒤에 오는 수와 비교한
//  단, 제거할 개수를 초과하면 안된다.
    public static String getMaxNumber(String number, int k) {
        String answer = "";
        String[] numbers = number.split("");
        int[] temp = new int[number.length()-k];
        int index = -1;
        int remove = 0;
        if (k < 1) {
            for (String s: numbers) {
                answer += s;
            }
            return answer;
        }
        for (int i = 0; i < numbers.length; i ++) {
            int n = Integer.parseInt(numbers[i]);
            while (remove < k && index >= 0) {
                if (temp[index] < n) {
                    temp[index] = 0;
                    ++ remove;
                    -- index;
                } else {
                    break;
                }
            }
            ++ index;
            temp[index] = n;
        }
        for (int i: temp) {
            answer += String.valueOf(i);
        }

        System.out.println(answer);
        return answer;
    }
}
