package dev.xarcher.flightbooking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupStrings {
    public static void main(String[] args) {
         var group = solution1(new String[]{"awa", "eat", "tea", "tan", "ate", "nat", "bat", "aw", "abv", "vab", "atb"});
        System.out.println(Arrays.toString(group.toArray()));
        // output: [[awa], [eat, tea, ate], [tan, nat], [bat, atb], [aw], [abv, vab]]
    }

    public static List<List<String>> solution1(final String[] input) {
        // Idea: Thực hiện Hash String thành một số và so sánh các giá trị hash.
        // Khi đó, 2 String được coi là bằng nhau khi có cùng giá trị hash
        // Cách thực hiện Hash String: tính tích của các kí tự
        var result = new ArrayList<List<String>>();
        var index = 0;
        var groupIdxs = new HashMap<Integer, Integer>();
        for (String str : input) {
            var valueStr = str.chars().reduce(1, (x, y) -> x * y);
            var currentStrIndex = groupIdxs.getOrDefault(valueStr, index);
            // group exist in result
            if (result.size() > currentStrIndex) {
                result.get(currentStrIndex).add(str);
                continue;
            }

            // group not exist in result
            var currentList = new ArrayList<>(List.of(str));
            result.add(currentList);
            groupIdxs.put(valueStr, index);
            index++;
        }

        return result;
    }
}
