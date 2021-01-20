package leetCode;

public class MaxSubArray {

    static class Status{
        int l_sum;
        int r_sum;
        int m_sum;
        int a_sum;
        Status(int l,int r,int m,int a){
            this.l_sum = l;
            this.r_sum = r;
            this.m_sum = m;
            this.a_sum = a;
        }
    }

    /** 分治 */
    private Status getInfo(int[] array,int l ,int r){
        if(l==r){
            return new Status(array[l],array[l],array[l],array[l]);
        }
        int center = (l+r)>>1;
        Status l_status = getInfo(array,l,center);
        Status r_status = getInfo(array,center+1,r);
        return pushUp(l_status,r_status);
    }

    /** 回溯*/
    private Status pushUp(Status l, Status r) {
        int a_sum = l.a_sum+r.a_sum;
        int l_sum = Math.max(l.l_sum,l.a_sum+r.l_sum);
        int r_sum = Math.max(r.r_sum,r.a_sum+l.r_sum);
        int m_sum = Math.max(Math.max(l.m_sum,r.m_sum),l.r_sum+r.l_sum);
        return new Status(l_sum,r_sum,m_sum,a_sum);
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).m_sum;
    }

    public static void main(String[] args){
        MaxSubArray maxSubArray = new MaxSubArray();
        System.out.println(maxSubArray.maxSubArray(new int[]{1,2,-3,4,-5,6,-2,6,-4}));
    }
}
