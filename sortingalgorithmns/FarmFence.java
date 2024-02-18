package SortingAlgorithmns;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class FarmFence {
    static List<Long> results = new ArrayList<Long>();
    static int findMinValueIndex(int[] x){
        int minValue = Integer.MAX_VALUE;
        int idx = -1;
        for(int i = 0; i < x.length; i++){
            if(x[i] < minValue){
                idx = i;
                minValue = x[i];
            }
        }
        return idx;
    }
    static int findMaxValueIndex(int[] x){
        int maxValue = Integer.MIN_VALUE;
        int idx = -1;
        for(int i = 0; i < x.length; i++){
            if(x[i] > maxValue){
                idx = i;
                maxValue = x[i];
            }
        }
        return idx;
    }
    static int[] removeElement(int[] x, int idx){
        int[] newArray = new int[x.length-1];
        int counter = 0;
        for(int i = 0; i < x.length; i++){
            if(i == idx){
                continue;
            }
            newArray[counter] = x[i];
            counter++; 
        }
        return newArray;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        //after reading data, you should have x,y
        int n = scan.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int smallx = 0;
        for(int i = 0; i <n ;i++){
            x[i] = scan.nextInt();
            if(x[i] < x[smallx]){
                smallx = i;
            }
            y[i] = scan.nextInt();
        }
        
        reduceTrees(x,y,3);
        long min = Integer.MAX_VALUE;
        for(int i = 0; i < results.size(); i++){
            if(results.get(i) < min){
                min = results.get(i);
            }
        }
        System.out.println(min);
        scan.close();
       //sort the list and get the smallest area from results

    }

    static void reduceTrees (int[] x, int[] y , int numOfTreesToReduce){
        if (numOfTreesToReduce == 0 ){
            long calc = (findMaxValueIndex(x) - findMinValueIndex(x))* (findMaxValueIndex(y) - findMinValueIndex(y)); 
            results.add(calc);
            return;
        }
        //finding the smallest x in the x array, and identify its index, so that the corresponding y can be removed
        int num = moveTopFenceDown(x ,y, findMinValueIndex(x));
        int idx = (findMinValueIndex(x));
        if (num <= numOfTreesToReduce){
            x = removeElement(x, idx);
            y = removeElement(y, idx);
            reduceTrees(x, y, numOfTreesToReduce - num);
            
        }
        num = moveDownFenceUp(x, y, findMaxValueIndex(x));
        idx= findMaxValueIndex(x);
        if (num <= numOfTreesToReduce){
            x = removeElement(x, idx);
            y = removeElement(y, idx);
            reduceTrees(x, y, numOfTreesToReduce - num);
            
        }
        num = moveLeftFenceRight(x,y, findMinValueIndex(y));
        idx = findMinValueIndex(y);
        if (num <= numOfTreesToReduce){
            x = removeElement(x, idx);
            y = removeElement(y, idx);
            reduceTrees(x, y, numOfTreesToReduce - num);
        }
        num = moveRightFenceLeft(x,y, findMaxValueIndex(y));
        idx = findMaxValueIndex(y);
        if (num <= numOfTreesToReduce){
            x = removeElement(x, idx);
            y = removeElement(y, idx);
            reduceTrees(x, y, numOfTreesToReduce - num);
        }
    }
    static int moveTopFenceDown(int[] x, int[] y, int smallx){  
        int treesCutDown = 0;   
        for(int i = 0; i < x.length; i++){
            if(x[smallx] == x[i]){
                treesCutDown++;
            }
        }
        return treesCutDown;
    }
    static int moveDownFenceUp(int[] x, int[] y, int largex){  
        int treesCutDown = 0;   
        for(int i = 0; i < x.length; i++){
            if(x[largex] == x[i]){
                treesCutDown++;
            }
        }
        return treesCutDown;
    }
    static int moveLeftFenceRight(int[] x, int[] y, int smally){  
        int treesCutDown = 0;   
        for(int i = 0; i < y.length; i++){
            if(y[smally] == y[i]){
                treesCutDown++;
            }
        }
        return treesCutDown;
    }
    static int moveRightFenceLeft(int[] x, int[] y, int largey){  
        int treesCutDown = 0;   
        for(int i = 0; i < y.length; i++){
            if(y[largey] == y[i]){
                treesCutDown++;
            }
        }
        return treesCutDown;
    }
}


