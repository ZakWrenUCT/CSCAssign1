import java.util.concurrent.*;
//import java.lang.Thread;
import java.util.*;

class ComputeMedian extends RecursiveTask<Integer> {
   // int SEQ_THRESH ;
    int seqThresh = 3;
    int lo; int hi; float[] arr; int si; double[] out;
    ComputeMedian(float[] a, int l, int h) {
        arr=a;
        lo=l;
        hi=h-1;
        //seqThresh=a.length-2;
    }
    @Override
    protected Float compute(){
        System.out.print("compute");
        float[] subset;
        out = new double[arr.length];
        if((hi-lo)<=seqThresh){
            //for(int i = 0;i <arr.length;i++) {
            for(int i = lo;i <hi;i++) {
                if (i == 0 || i == arr.length-1) {
                    out[i] = arr[i];
                } else {
                    subset = Arrays.copyOfRange(arr, i-1, i+2);
                    //System.out.println(subset.toString());
                    out[i] = findMedian(subset);
                }
            }
            System.out.println(Arrays.toString(out));
            return 1;}

        else {
            //System.out.println("split");
            ComputeMedian left = new ComputeMedian(arr,lo,(hi+lo)/2);
            ComputeMedian right= new ComputeMedian(arr,(hi+lo)/2,hi);
            left.fork();
            float[] rightAns = right.compute();
            float[] leftAns = left.join();
            System.out.println("merge");
            float[] join = Arrays.copyOf(leftAns,leftAns.length+rightAns.length);
            return leftAns + rightAns;
        }
    }
    public static double findMedian(float[] ar)
    {
        //sort the array
        Arrays.sort(ar);
        //even case
        if (ar.length % 2 != 0)
            return ar[ar.length / 2];
        return (double)(ar[(ar.length - 1) / 2] + ar[ar.length / 2]) / 2.0;
    }

    static final ForkJoinPool fjPool = new ForkJoinPool();

    int[] add(int[] arr1, int[] arr2){
        assert (arr1.length == arr2.length);
        int[] ans = new int[arr1.length];
        fjPool.invoke(new ComputeMedian(arr,lo,hi));
    return ans;}
}

