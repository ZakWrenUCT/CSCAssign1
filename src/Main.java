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
        //must try different # of threads
        //use System.nanoTime() or System.currentTimeMillis() to measure
        //System.gc() after timing block

        float[] input = {1,5,10,2,3,2,9,15,1};

        String name = "sampleInput100";
        File text = new File(name+".txt");
        Scanner scan = new Scanner(text);

//        int lineNumber = 1;
//        String line = scan.nextLine();
//        int high = parseInt(line);
//
//        float[] input = new float[high];
//        while(scan.hasNextLine()){
//            line = scan.nextLine();
//            input[lineNumber-1]= Float.parseFloat(line.split(" ")[1].replace(',','.'));
//            lineNumber++;
//        }

        int size = 5;
        //throw error if size is bigger than array or even

        ProcessArray cm = new ProcessArray(input, 0, input.length, size, 12);
        ProcessArraySeq cmseq = new ProcessArraySeq(input, 0, input.length, size);



//        System.gc();
//
//        long seqStart = System.nanoTime();
//        float[] seq = cm.compute();
//        long seqEnd = System.nanoTime();

//        System.gc();
//
//        long paraStart = System.nanoTime();
          //float[] parallel = cm.compute();
//        long paraEnd = System.nanoTime();

/*        long paraStart = System.currentTimeMillis();
        float[] parallel = cm.compute();
        long paraEnd = System.currentTimeMillis();

        long seqStart = System.currentTimeMillis();
        float[] seq = cm.compute();
        long seqEnd = System.currentTimeMillis();*/


//        System.out.println(paraEnd-paraStart);
//        System.out.println(seqEnd-seqStart);

        //System.out.println(Arrays.toString(cm.compute()));
        //System.out.println(Arrays.toString(cmseq.calculate()));
//        System.out.print(Arrays.equals(cm.compute(),cmseq.calculate()));
        //compare run times of parallel to serial
    }

}
