package leetCode;

public class MaxProfit {

    static class Status{
        int min;
        int max;
        int profit;

        Status(int min,int max,int profit){
            this.min = min;
            this.max = max;
            this.profit = profit;
        }
    }

    public int maxProfit(int[] prices) {
        return getInfo(prices,0,prices.length-1).profit;
    }

    private Status getInfo(int[] prices, int l, int r) {
        if(l==r){
            return new Status(prices[l],prices[l],0);
        }
        int center = (l+r)>>1;
        Status l_status = getInfo(prices,l,center);
        Status r_status = getInfo(prices,center+1,r);
        return pushUp(l_status,r_status);
    }

    private Status pushUp(Status l_status, Status r_status) {
        int min = Math.min(l_status.min,r_status.min);
        int max = Math.max(l_status.max,r_status.max);
        int profit = Math.max(Math.max(r_status.max-l_status.min,l_status.profit),r_status.profit);
        return new Status(min,max,profit);
    }

    public static void main(String[] args){
        MaxProfit maxProfit = new MaxProfit();
        System.out.println(maxProfit.maxProfit(new int[]{7,6,4,3,1}));
    }
}
