import static java.lang.Integer.parseInt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {

  //Setup benchmarking
  static long startTime = 0;

  private static void tick() {
    startTime = System.nanoTime();
  }

  private static float tock() {
    return (System.nanoTime() - startTime) / 1000000.0f;
  }

  static final ForkJoinPool fjPool = new ForkJoinPool();

  static float[] sum(float[] input, int size) {
    return fjPool.invoke(new ProcessArray(input, 0, input.length, size, 750));
  }

  static float[] sumSeq(float[] input, int size) {
    return new ProcessArraySeq(input, 0, input.length, size).calculate();
  }

  public static void main(String[] args) throws FileNotFoundException {
    //take user input
    Scanner user = new Scanner(System.in);
    System.out.println("FileName Size OutputName:");
    String theStuff = user.nextLine();
    String name = theStuff.split(" ")[0];
    File text = new File(name);
    int size = Integer.parseInt(theStuff.split(" ")[1]);
    File outText = new File(theStuff.split(" ")[2]);
    //sampleInput100000.txt 5 out.txt

    Runtime runtime = Runtime.getRuntime();
    int numberOfProcessors = runtime.availableProcessors();
    System.out.println(
      "Number of processors available to this JVM: " + numberOfProcessors
    );

    //parse input file
    Scanner scan = new Scanner(text);
    int lineNumber = 1;
    String line = scan.nextLine();
    int high = parseInt(line);
    float[] input = new float[high];
    while (scan.hasNextLine()) {
      line = scan.nextLine();
      input[lineNumber - 1] =
        Float.parseFloat(line.split(" ")[1].replace(',', '.'));
      lineNumber++;
    }
    //Run parallel and sequential algorithm
    float[] sumArr = {};
    float[] paraTimes = new float[20];
    float[] seqTimes = new float[20];
    System.gc();
    //Test parallel algorithm
    for (int i = 0; i < 20; i++) {
      tick();
      sumArr = sum(input, size);
      float time = tock();
      //System.out.println("Run "+ (i+1) +" of the parallel took " + time + " milliseconds");
      paraTimes[i] = time;
    }
    System.gc();
    //Test sequential algorithm
    for (int i = 0; i < 20; i++) {
      tick();
      sumArr = sumSeq(input, size);
      float time = tock();
      //System.out.println("Run "+ (i+1) +" of the sequential took " + time + " milliseconds");
      seqTimes[i] = time;
    }
    //write results to output file
    try {
      if (outText.createNewFile()) {
        System.out.println("File created: " + outText.getName());
        PrintWriter myWriter = new PrintWriter(outText, StandardCharsets.UTF_8);
        myWriter.println(sumArr.length);
        for (int i = 0; i < sumArr.length; i++) {
          String outtt =
            i +
            " " +
            Float
              .toString((float) (Math.round(sumArr[i] * 100000) / 100000d))
              .replaceAll("\\.", ",");
          System.out.println(outtt);
          myWriter.println(outtt);
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    //print out performance of algorithms
    System.out.println("SequentialTresh: " + 750);
    System.out.println("   ");
    System.out.println("For parallel: ");
    timeStats(paraTimes);

    System.out.println("   ");
    System.out.println("For sequential: ");
    timeStats(seqTimes);
  }

  public static void timeStats(float[] times) {
    //helps the user to interpret performance data
    float[] sorted = times.clone();
    Arrays.sort(sorted);
    System.out.println("Fastest:" + sorted[0]);
    System.out.println("Slowest:" + sorted[sorted.length - 1]);
    float arrMean = 0;
    for (float i : sorted) {
      arrMean += i;
    }
    arrMean = (float) (Math.round((arrMean / times.length) * 100000) / 100000d);
    System.out.println("Mean:" + arrMean);
    System.out.println("Median:" + sorted[sorted.length / 2]);
  }
}
