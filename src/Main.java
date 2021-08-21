public class Main{

    public static void main(String[] args) {
        //take user input of :file name, filter size (odd int), output name
	    //get data set
        //one-dimensional where all data sets are odd with between 3 to 21 items.

        //parse data (space between pos and val)
        //data is float between 0 and 5

        //implement median filter of size x and output array
        //algorithm in size = 3: pass first element and last, and for all other values use median of the data point below and above
        //so border = size/2
        //output to same format as input and correct up to 5 dec places

        //use fork/join framework to do this in parallel (divide and concur)
        //must try different # of threads
        //use System.nanoTime() or System.currentTImeMillis() to measure
        //System.gc() after timing block
        float[] array = {1,5,10,1,0};
        int low = 0;
        int high = array.length;
        int size = 3;
        ProcessArray cm = new ProcessArray(array, low, high, size);
        cm.compute();
        //System.out.println("a");
        //compare run times of parallel to serial
        //System.out.println(5/2);
    }

}
