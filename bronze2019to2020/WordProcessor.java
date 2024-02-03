package bronze2019to2020;
import java.util.*;

public class WordProcessor {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[] words = new String[n];
        int k = scan.nextInt();
        //scan and intialize 
        int charcount = 0; //running character count
        for(int i = 0; i <n; i++){
            words[i] = scan.next();
            
        }
        int idx = 0; //index count
        while(true){
            if(charcount >= k){
                charcount = 0;
                System.out.println();
                //if the character count is over the limit, new line
            }
            else{
                //else we attempt to print a new word
                if((words[idx].length() +charcount) > k){
                    //if the word plus current character count is greater than limit, reset
                    System.out.println();
                    charcount = 0;
                    continue;
                }
                else{
                    System.out.print(words[idx] + " ");
                    charcount = charcount+words[idx].length();
                    idx++;
                }
            }
            if(idx >= n){
                break;
            }
        }
        scan.close();
    }
}