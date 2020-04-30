/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo;

import java.util.Scanner;

public class Simo {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        //order quantity & reorder point
        int orderQuantity,reorderPoint;
        System.out.println("Enter order quantity");
        orderQuantity=scan.nextInt();
        System.out.println("Enter reorder point");
        reorderPoint=scan.nextInt();
        
        //First Table(demand table)
        System.out.println("How many(demand)?");
        int x = scan.nextInt();
        
        float[][] arr = new float[x][5];
        int i,j;
        
        //demand col
        System.out.println("Enter Demands");
        for(i=0;i<x;i++)
        {
            arr[i][0]=scan.nextFloat();
        }
        
        //frequency col(demand)
        float sum=0;
        float freq;
        System.out.println("Enter frequency");
        for(i=0;i<x;i++)
        {
            freq=scan.nextFloat();
            arr[i][1]=freq;
            sum+=freq;
        }
        
        //probability col(demand)
        float prob;
        for(i=0;i<x;i++)
        {
            prob=arr[i][1]/sum;
            arr[i][2]=prob;
        }
        
        //cumulative col(demand)
        float cum=0;
        for(i=0;i<x;i++)
        {
            cum+=arr[i][2];
            arr[i][3]=cum;
        }
        
        //interval col(demand)
        float intrval;
        for(i=0;i<x;i++)
        {
            intrval=arr[i][3]*100;
            arr[i][4]=intrval;
        }
        
        //print first table
        System.out.println("Demand\tFrequency\tprobability\tcumulative\tInterval");
        int s;
        for(i=0;i<x;i++)
        {
            for(j=0;j<5;j++)
            {
                if(j==2||j==3)
                System.out.print(arr[i][j]);
                else
                {
                    s=(int)arr[i][j];
                    System.out.print(s);
                }
                System.out.print("\t");
                
            }
             System.out.print("\n");
        }

        //Second Table
        System.out.println("How many(lead time)?");
        int y = scan.nextInt();
        
        float[][] arr2 = new float[y][5];
        
        //lead time col
        System.out.println("Enter LeadTime");
        for(i=0;i<y;i++)
        {
            arr2[i][0]=scan.nextFloat();
        }
        
        //frequncy col(lead time)
        float sum2=0;
        float freq2;
        System.out.println("Enter frequency");
        for(i=0;i<y;i++)
        {
            freq2=scan.nextFloat();
            arr2[i][1]=freq2;
            sum2+=freq2;
        }
        
        //probability col((lead time)
        float prob2;
        for(i=0;i<y;i++)
        {
            prob2=arr2[i][1]/sum2;
            arr2[i][2]=prob2;
        }
        
        //cumulative col(lead time)
        float cum2=0;
        for(i=0;i<y;i++)
        {
            cum2+=arr2[i][2];
            arr2[i][3]=cum2;
        }
        
        //interval col(lead time)
        float intrval2;
        for(i=0;i<y;i++)
        {
            intrval2=arr2[i][3]*100;
            arr2[i][4]=intrval2;
        }
        
        //print table 2
        System.out.println("LeadTime\tFrequency\tprobability\tcumulative\tInterval");
        
        for(i=0;i<y;i++)
        {
            for(j=0;j<5;j++)
            {
                if(j==2||j==3)
                System.out.print(arr2[i][j]);
                else
                {
                    s=(int)arr2[i][j];
                    System.out.print(s);
                }
                System.out.print("\t");
                
            }
             System.out.print("\n");
        }
        
        int count=0;
        //Table 3
        int k;
        System.out.println("How many days in table 3?");
        int days= scan.nextInt();
        
        int[][] arr3 = new int[days][10];
        
        //days col
        for(i=0;i<days;i++)
        {
            arr3[i][0]=i+1;
        }
        
        //Random num of demand(idiot code)
        arr3[0][3]=6;
        arr3[1][3]=63;
        arr3[2][3]=57;
        arr3[3][3]=94;
        arr3[4][3]=52;
        arr3[5][3]=69;
        arr3[6][3]=32;
        arr3[7][3]=30;
        arr3[8][3]=48;
        arr3[9][3]=88;
        
        //var of random num of lead time(idiot code)
        int wooow=0;
        
        //calc
        for(i=0;i<days;i++)
        {
            for(j=0;j<10;j++)
            {
                //units received col
                
                //default
                arr3[i][1]=0;
                
                //order done
                if(count!=0)
                {
                    if(count==1)
                        arr3[i][1]=orderQuantity;
                    count--;
                }
                
                //Begining col
                if(i==0)
                    arr3[i][2]=orderQuantity;
                else
                {
                    arr3[i][2]=arr3[i-1][5]+arr3[i][1];
                }
                
                
                //Demand col
                for(k=0;k<x;k++)
                {
                    if(k==0)
                    {
                        if(arr3[i][3]<= (int)arr[k][4])
                        {
                             arr3[i][4]= (int)arr[k][0];
                             break;
                        }
                           
                    }
                    else
                    {
                        if((arr3[i][3]<=(int)arr[k][4]) && (arr3[i][3]>(int)arr[k-1][4]))
                        {
                            arr3[i][4]=(int) arr[k][0];
                            break;
                        }
                            
                    }
                }
                
                //Ending col & lost col
                if(arr3[i][2]-arr3[i][4]>=0)
                {
                    arr3[i][5]=arr3[i][2]-arr3[i][4];
                    arr3[i][6]=0;
                }
                else
                {
                    arr3[i][5]=0;
                    arr3[i][6]=(arr3[i][2]-arr3[i][4])*(-1);
                }
                
                //order col
                if(arr3[i][5]<=reorderPoint && count ==0)
                    arr3[i][7]=1;
                else if(arr3[i][5]>=reorderPoint)
                    arr3[i][7]=0;
                else if(arr3[i][5]<=reorderPoint && count !=0)
                    arr3[i][7]=0;
                
                
                //Prooooooooooooooooooooooooooblem
                
                //Random num of lead time
                //if 8reba
                if(arr3[i][7]==1)
                {
                        if(wooow==0)
                            arr3[i][8]=2;
                        else if(wooow==1)
                            arr3[i][8]=33;
                        else if(wooow==2)
                            arr3[i][8]=14;
                        wooow++;
                        
                        //leadtime col
                        for(k=0;k<y;k++)
                        {
                            if(k==0)
                            {
                                if(arr3[i][8]<=arr2[k][4])
                                {
                                arr3[i][9]=(int) arr2[k][0];
                                count=arr3[i][9]+1;
                                break;
                                }

                            }
                            if(arr3[i][8]<=arr2[k][4] && arr3[i][8]>arr2[k-1][4])
                            {
                                {
                                    arr3[i][9]=(int) arr2[k][0];
                                    count=arr3[i][9]+1;
                                    break;
                                }

                            }
                        }
                }
                else
                {
                    arr3[i][8]=0;
                    arr3[i][9]=0;
                }
            }
        }
        //End Prooooooooooooooooooooooooooblem
        
        //Print table 3
        for(i=0;i<days;i++)
        {
            for(j=0;j<10;j++)
            {
                System.out.print(arr3[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
}
