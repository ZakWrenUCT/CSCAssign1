
import java.io.*;
//import java.util.Arrays;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ForkJoinPool;
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
        return fjPool.invoke(new ProcessArray(input, 0, input.length, size, 750));
    }
    static float[] sumSeq(float[] input,int size){
        return new ProcessArraySeq(input, 0, input.length, size).calculate();
    }
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner user = new Scanner(System.in);
//        System.out.println("File Name: ");
//        String name = user.nextLine();
//        File text = new File(name);
//        System.out.println("Size: ");
//        int size = Integer.parseInt(user.nextLine());
//        System.out.println("Output: ");
//        String outLine = user.nextLine();
//        File outText = new File(outLine);

        File outText = new File("out.txt");
        int size = 5;
        File text = new File("sampleInput100.txt");

        Runtime runtime = Runtime.getRuntime();
        int numberOfProcessors = runtime.availableProcessors();
        System.out.println("Number of processors available to this JVM: " + numberOfProcessors);

        System.gc();

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

        //throw error for even size or larger than set size
        //float[] input = {1,5,10,2,3,2,9,15,1};
        System.gc();
        float[] sumArr = {};
        for(int i =0; i<20; i++) {
            tick();
            sumArr = sum(input, size);
            float time = tock();
            System.out.println("Run "+ (i+1) +" of the parallel took " + time + " milliseconds");
        }
        System.gc();
        for(int i =0; i<20; i++) {
            tick();
            sumArr = sumSeq(input, size);
            float time = tock();
            System.out.println("Run "+ (i+1) +" of the sequential took " + time + " milliseconds");
        }
        try {
            if (outText.createNewFile()) {
                System.out.println("File created: " + outText.getName());
                PrintWriter myWriter = new PrintWriter(outText, StandardCharsets.UTF_8);
                    //FileWriter myWriter = new FileWriter("filename.txt");
                    myWriter.println(sumArr.length);
                    for (int i = 0; i<sumArr.length;i++) {
                        String outtt = i + " "+  Float.toString((float) (Math.round(sumArr[i]*100000)/100000d)).replaceAll("\\.",",");
                        System.out.println(outtt);
                        myWriter.println(outtt);
                    }
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                }
            else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(Arrays.equals(sumSeq(input,size),sum(input,size)));
        //System.out.println(Arrays.toString(sumSeq(input,size)));
        //System.out.println(Arrays.toString(sum(input,size)));

    }

}
