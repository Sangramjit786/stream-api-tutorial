package net.javaguides;

public class Test {
    public double getMaxAverage(int[] num, int k){
        if(num.length < k){
            return -1.0;
        } else {
            double maxAverage = 0.0;
            for(int i = 0; i < num.length - k + 1; i++){
                int sum = 0;
                for(int j = i; j < i + k; j++) {
                    sum += num[j];
                }
                if(maxAverage < (sum /(double) k)){
                    maxAverage = sum /(double) k;
                }
            }
            return maxAverage;
        }
    }

    public double getMaximumAverage(int[] num, int k){
        if(num.length < k){
            return -1.0;
        } else {
            int left = 0;
            int sum = 0;
            for(int i = 0; i < k; i++){
                sum += num[i];
            }
            double maxAverage = sum / (double) k;

            for(int right = left + k; right < num.length; right++){
                sum += num[right] - num[left];
                maxAverage = Math.max(maxAverage, sum / (double) k);
                left++;
            }
            return maxAverage;
        }
    }



    public static void main(String[] args) {
        int[] num = {5};
        int k = 1;
        System.out.println("MaximumAverage Value 1 :" + new Test().getMaxAverage(num, k));
        System.out.println("MaximumAverage Value 2 :" + new Test().getMaximumAverage(num, k));
    }
}
