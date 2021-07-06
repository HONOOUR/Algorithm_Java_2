package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        getRemoveCount("aabcbcd", "abc");
        getStrings("abcxyqwertyxyabc");
        int[] prices = {13000, 88000, 10000};
        int[] discounts = {30, 20};
        getMinPrice(prices, discounts);
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        getMinCosts(4, costs);
        int[] people = {70, 50, 80, 50};
        getMinBoat(people, 100);
        getMaxNumber("4177252841", 4);
    }

    public static int getRemoveCount(String s, String t) {
        int result = -1;
        String[] strings = s.split("");
        ArrayList<String> tmp = new ArrayList<>();
        for (String i: strings) {
            tmp.add(i);
        }
        int count = 0;
        int i = 0;
        int j = i + t.length()-1;
        while(j <= tmp.size()) {
            String temp = "";
            for (int start = i; start <= j; start++) {
                temp += tmp.get(start);
            }
            if (temp.equals(t)) {
                for (int x = j; x >= i; x--) {
                    tmp.remove(x);
                }
                i = 0;
                j = i + t.length() -1;
                ++ count;
            } else {
                ++ i;
                j = i + t.length() -1;
            }
        }
        if (count != 0) {
            result = count;
        }
        return result;
    }


    // 시작 문자에 포인터, 끝 문자에 포인터 -> two pointer 사용
    public static String[] getStrings(String s) {
        ArrayList<String> tmp = new ArrayList<>();
        String[] strings = s.split("");
        int stringLength = strings.length -1;
        int start = 0;
        int end = 0;
        while (end < stringLength) {
            if (!strings[end].equals(strings[stringLength])) {
                ++end;
            } else {
                String temp = "";
                for (int sub = start; sub <=end; sub++) {
                    temp += strings[sub];
                    --stringLength; // 좌우 같은 문자열 확
                }
                tmp.add(temp);
                ++end;
                start = end;
            }
        }
        ArrayList<String> temp_left = new ArrayList<>();
        for (String t: tmp) {
            temp_left.add(t);
        }
        if (start != end) {
            String temp = "";
            for (int sub = start; sub <=end; sub++) {
                temp += strings[sub];
                --stringLength;
            }
            temp_left.add(temp);
        }

        String[] answer = new String[temp_left.size() + tmp.size()];
        for (int x = 0; x < temp_left.size(); x ++) {
            answer[x] = temp_left.get(x);
        }
        int i = 0;
        for (int y = tmp.size()-1; y >= 0; y--) { // 역순으로 들어와야함
            answer[temp_left.size() + i] = tmp.get(y);
            i++;
        }

        return answer;
    }

    // 가장 높 가격에 가장 큰 할인율 적용
    public static int getMinPrice(int[] prices, int[] discounts) {
        int answer = 0;
        Arrays.sort(prices);
        Arrays.sort(discounts);
        int discounts_index = discounts.length -1;
        for (int i = prices.length-1; i >=0; i--) {
            if (discounts_index >= 0) {
                answer += (prices[i] * (100-discounts[discounts_index]) * 0.01);
                --discounts_index;
            } else { // no more coupon
                answer += prices[i];
            }
        }
        return answer;
    }

    public static int getMinCosts(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        int[] visited = new int[n];
        for (int[] cost: costs) {
            int a = cost[0];
            int b = cost[1];
            if (visited[a] == 0 && visited[b] == 0) {
                visited[a] = 1;
                visited[b] = 1;
                answer += cost[2];
            } else if (visited[a] == 0) {
                visited[a] = 1;
                answer += cost[2];
            } else if (visited[b] == 0) {
                visited[b] = 1;
                answer += cost[2];
            } else {
                continue;
            }
        }
        return answer;
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
