package hashmaps;

public class CheckArrayPairsDivisible {

    public boolean canArrange(int[] arr, int k) {
        int[] remainders = new int[k];
        for (int i: arr) {
            int rem = i % k;
            if (rem < 0)
                rem += k;
            remainders[rem] ++;
        }
        if (remainders[0] % 2 != 0)
            return false;
        for (int i = 0; i<remainders.length; i++) {
            if (remainders[i] != remainders[k-i-1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CheckArrayPairsDivisible d = new CheckArrayPairsDivisible();
        System.out.println(d.canArrange(new int[]{-1,-1,-1,-1,2,2,-2,-2}, 3));
    }

}
