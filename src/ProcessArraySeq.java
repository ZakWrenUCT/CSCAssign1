import java.util.Arrays;

class ProcessArraySeq {

  int lo;
  int hi;
  float[] arr;
  int si;
  float[] out;

  ProcessArraySeq(float[] a, int l, int h, int s) {
    arr = a;
    lo = l;
    hi = h;
    si = s;
  }

  float[] calculate() {
    out = Arrays.copyOfRange(arr, lo, hi);
    float[] subset;
    for (int i = lo; i < hi; i++) {
      //logic here
      if (i <= (si / 2) - 1 || i >= arr.length - ((si / 2))) {
        out[i - lo] = arr[i];
      } else {
        subset = Arrays.copyOfRange(arr, i - si / 2, i + (si / 2) + 1);
        out[i - lo] = findMedian(subset);
      }
    }
    return out;
  }

  public static float findMedian(float[] ar) {
    //sort the array
    Arrays.sort(ar);
    //even case
    if (ar.length % 2 != 0) return ar[ar.length / 2];
    return (float) ((ar[(ar.length - 1) / 2] + ar[ar.length / 2]) / 2.0);
  }
}
