//import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
//import static java.lang.Integer.valueOf;
//import static java.lang.Float.*;

public class Main{

    public static void main(String[] args) throws FileNotFoundException {
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

        //float[] array = {1,5,10,2,3,2,9,15};
        String name = "sampleInput100";
        File text = new File(name+".txt");
        Scanner scan = new Scanner(text);


        int lineNumber = 1;
        String line = scan.nextLine();
        int high = parseInt(line);

        float[] input = new float[high];
        while(scan.hasNextLine()){
            line = scan.nextLine();
            input[lineNumber-1]= Float.parseFloat(line.split(" ")[1].replace(',','.'));
            lineNumber++;
        }

            //float[] array = {0,1,2,3};
        int size = 3;
        ProcessArray cm = new ProcessArray(input, 0, input.length, size);
        System.out.print(Arrays.toString(cm.compute()));
        //compare run times of parallel to serial
    }

}
