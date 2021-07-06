package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        solution("aabcbcd", "abc");
        getStrings("abcxyqwertyxyabc");
        int[] prices = {13000, 88000, 10000};
        int[] discounts = {30, 20};
        minPrice(prices, discounts);
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        getMinCosts(4, costs);
        int[] people = {70, 50, 80, 50};
        getMinBoat(people, 100);
        getMaxNumber("4177252841", 4);
    }

    public static int solution(String s, String t) {
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

    public static String[] getStrings(String s) {
        ArrayList<String> tmp = new ArrayList<>();
        String[] strings = s.split("");
        int j = strings.length -1;
        int start = 0;
        int end = 0;
        while (end < j) {
            if (!strings[end].equals(strings[j])) {
                ++end;
            } else {
                String temp = "";
                for (int sub = start; sub <=end; sub++) {
                    temp += strings[sub];
                    --j;
                }
                tmp.add(temp);
                ++end;
                start = end;
            }
        }
        ArrayList<String> tmp2 = new ArrayList<>();
        for (String t: tmp) {
            tmp2.add(t);
        }
        if (start != end) {
            String temp = "";
            for (int sub = start; sub <=end; sub++) {
                temp += strings[sub];
                --j;
            }
            tmp2.add(temp);
        }

        String[] answer = new String[tmp2.size() + tmp.size()];
        for (int x = 0; x<tmp2.size(); x++) {
            answer[x] = tmp2.get(x);
        }
        int i = 0;
        for (int y = tmp.size()-1; y >= 0; y--) {
            answer[tmp2.size() + i] = tmp.get(y);
            i++;
        }

        return answer;
    }
    public static int minPrice(int[] prices, int[] discounts) {
        int answer = 0;
        Arrays.sort(prices);
        Arrays.sort(discounts);
        int index = discounts.length -1;
        for (int i = prices.length-1; i >=0; i--) {
            if (index >= 0) {
                answer += (prices[i] * (100-discounts[index]) * 0.01);
                --index;
            } else {
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
