
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    static long startTime = 0;
    private static void tick(){
        startTime = System.nanoTime();
    }
    private static float tock(){
        return (System.nanoTime() - startTime) / 1000000.0f;
    }
    static final ForkJoinPool fjPool = new ForkJoinPool();

    static float[] sum(float[] input,int size){
        return fjPool.invoke(new ProcessArray(input, 0, input.length, size, 1000));
    }
    static float[] sumSeq(float[] input,int size){
        return new ProcessArraySeq(input, 0, input.length, size).calculate();
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.gc();
        String name = "sampleInput10000";
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
        int size = 5;
        //float[] input = {1,5,10,2,3,2,9,15,1};
        System.gc();
        for(int i =0; i<20; i++) {
            tick();
            float[] sumArr = sum(input, size);
            float time = tock();
            System.out.println("Run "+ (i+1) +" of the parallel took " + time + " milliseconds");
        }
        System.gc();

        for(int i =0; i<20; i++) {
            tick();
            float[] sumArr = sumSeq(input, size);
            float time = tock();
            System.out.println("Run "+ (i+1) +" of the sequential took " + time + " milliseconds");
        }

        System.out.println(Arrays.equals(sumSeq(input,size),sum(input,size)));
        //System.out.println(Arrays.toString(sumSeq(input,size)));
        System.out.println(Arrays.toString(sum(input,size)));

    }

}
