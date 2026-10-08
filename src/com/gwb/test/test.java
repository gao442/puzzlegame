package com.gwb.test;

import java.util.Random;

public class test {
    public static void main(String[] args) {

        int[] temparr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int[][] data=new int[4][4];

        //打乱
        Random rd=new Random();
        for (int i = 0; i < temparr.length; i++) {
            int temp;
            int temp_idx=rd.nextInt(temparr.length);
            temp=temparr[temp_idx];
            temparr[temp_idx]=temparr[i];
            temparr[i]=temp;
        }

        for (int i = 0; i < temparr.length; i++) {
            data[i/4][i%4]=temparr[i];
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }

    }
}
