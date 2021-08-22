import java.util.Arrays;
//import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ProcessArray extends RecursiveTask<float[]> {
    int SEQUENTIAL_CUTOFF;
    int lo;
    int hi;
    float[] arr;
    int si;
    float[] out;
    ProcessArray(float[] a, int l, int h,int s,int se) {
        arr=a;
        lo=l;
        hi=h;
        si=s;
        SEQUENTIAL_CUTOFF=se;
    }
    protected float[] compute() {
        out = Arrays.copyOfRange(arr, lo, hi);
        //out = new float[arr.length];
        float[] subset;
        if ((hi - lo) <= SEQUENTIAL_CUTOFF) {
            for (int i = lo; i < hi; i++)
                //logic here
                if (i <= (si/2)-1 || i >= arr.length - ((si/2))) {
                    out[i-lo] = arr[i];
                } else {
                    subset = Arrays.copyOfRange(arr, i - si/2, i + (si/2)+1);
                    out[i-lo] = findMedian(subset);
                }
            //System.out.println(Arrays.toString(out));
            return out;
        } else {
            ProcessArray left = new ProcessArray(arr, lo, (hi + lo) / 2, si , SEQUENTIAL_CUTOFF);
            ProcessArray right = new ProcessArray(arr, (hi + lo) / 2, hi, si, SEQUENTIAL_CUTOFF);
            left.fork();
            float[] rightAns = right.compute();
            float[] leftAns = left.join();
            float[] both = Arrays.copyOf(leftAns,leftAns.length + rightAns.length);
            System.arraycopy(rightAns,0,both,leftAns.length, rightAns.length);
            return both;
        }
    }
        public static float findMedian(float[] ar)
        {
            //sort the array
            Arrays.sort(ar);
            //even case
            if (ar.length % 2 != 0)
                return ar[ar.length / 2];
            return (float) ((ar[(ar.length - 1) / 2] + ar[ar.length / 2]) / 2.0);
        }
        static final ForkJoinPool fjPool = new ForkJoinPool();
    }
