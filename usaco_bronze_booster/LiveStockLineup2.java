package usaco_bronze_booster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LiveStockLineup2 {
    public static void main(String[] args) {
        String[] cows = "Bessie,Buttercup,Belinda,Beatrice,Bella,Blue,Betsy,Sue".split(",");

        Arrays.sort(cows);
        HashMap<String, Integer> cowsDict = new HashMap<>();
        for (int i = 0; i < cows.length; i++) {
            cowsDict.put(cows[i], i);
        }
        // create an array to record whether a cow has been placed
        boolean[] placedcows = new boolean[cows.length];
        for (int i = 0; i < placedcows.length; i++)
            placedcows[i] = false;
        HashMap<Integer, List<Integer>> rulesDictionary = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scan.nextLine();
            String[] p = s.split("must be milked beside");
            String p1 = p[0].trim();
            String p2 = p[1].trim();
            int c1 = cowsDict.get(p1);
            int c2 = cowsDict.get(p2);
            if (!rulesDictionary.keySet().contains(c1)) {
                rulesDictionary.put(c1, new ArrayList<Integer>());
            }
            if (!rulesDictionary.keySet().contains(c2)) {
                rulesDictionary.put(c2, new ArrayList<Integer>());
            }
            rulesDictionary.get(c1).add(c2);
            rulesDictionary.get(c2).add(c1);
        }
        scan.close();
        int[] answer = new int[cows.length];
        for (int i = 0; i < answer.length; i++) {
            // block 1 is to check rules
            // to decide whether a rule should be enforced, we need to know the cow has been
            // placed before the current i position
            int cowplacedbefore = i == 0 ? -1 : answer[i - 1];
            boolean cowplacedbyrule = false;
            if (rulesDictionary.keySet().contains(cowplacedbefore)) {
                List<Integer> rule = rulesDictionary.get(cowplacedbefore);
                int[] ruleIntArray = new int[rule.size()];
                for (int k = 0; k < ruleIntArray.length; k++) {
                    ruleIntArray[k] = rule.get(k);
                }
                Arrays.sort(ruleIntArray); // force alphebetically sorting
                for (int k = 0; k < ruleIntArray.length; k++) {
                    if (placedcows[ruleIntArray[k]])
                        continue;
                    else {
                        answer[i] = ruleIntArray[k];
                        placedcows[answer[i]] = true;
                        cowplacedbyrule = true;
                        break;
                    }
                }
            }
            if (cowplacedbyrule)
                continue; // rule engine placed a cow, skip the regular search
            // block 2 is to check whether a cow can be placed
            for (int j = 0; j < cows.length; j++) {
                if (placedcows[j] == true)
                    continue; // skip since the cow j has been placed before
                if (rulesDictionary.keySet().contains(j) && rulesDictionary.get(j).size() == 2)
                    continue; // here is a cow with two neighbors rule, 
                answer[i] = j;
                placedcows[j] = true;
                break;
            }
        }
        for (int i = 0; i < answer.length; i++) {
            System.out.println(cows[answer[i]]);
        }
    }

}
