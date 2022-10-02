package USACO_bronze_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HexToOct {

    public static void main(String[] args) {
        Map<String, String> hexToBinLookup = Stream.of(new String[][] {
                { "0", "0000" },
                { "1", "0001" },
                { "2", "0010" },
                { "3", "0011" },
                { "4", "0100" },
                { "5", "0101" },
                { "6", "0110" },
                { "7", "0111" },
                { "8", "1000" },
                { "9", "1001" },
                { "A", "1010" },
                { "B", "1011" },
                { "C", "1100" },
                { "D", "1101" },
                { "E", "1110" },
                { "F", "1111" }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
        Map<String, String> binToOctLookup = new HashMap<String, String>();
        // use a for look to constrct three digit binary to oct lookup
        for (String num : hexToBinLookup.keySet()) {
            String binVal = hexToBinLookup.get(num);
            if (binVal.charAt(0) == '1')
                continue; // ignore anything above 7
            binToOctLookup.put(binVal.substring(1), num);
        }

        String hexinput = "75BCD15";
        String binary = "";
        String octoutput = "";
        for (int i = 0; i < hexinput.length(); i++) {
            String hexchar = String.valueOf(hexinput.charAt(i));
            String binstr = hexToBinLookup.get(hexchar);
            binary = binary + binstr;
        }
        System.out.println(binary);
        System.out.println(binary.length());
        for (int i = binary.length(); i >= 0; i = i - 3) {
            int beginIndex = i - 3 > 0 ? i - 3 : 0;

            String binstr = binary.substring(beginIndex, i);
            if (binstr.length() == 1) {
                binstr = "00" + binstr;
            } else if (binstr.length() == 2) {
                binstr = "0" + binstr;
            }
            String octstr = binToOctLookup.get(binstr);
            if (octstr == null) {
                System.out.println("break");
            }
            octoutput = octstr + octoutput;
        }
        System.out.println(octoutput);
    }
}
