import java.util.Arrays;
import java.io.PrintWriter;
import java.io.File;

public class Sorting
{
    static int[] sizes = {8, 32, 64, 128, 256, 512, 1024, 2*1024, 4*1024, 8*1024,
            16*1024, 32*1024, 64*1024, 128*1024, 256*1024, 512*1024, 1024*1024};
    public static void main(String[] args) throws Exception
    {
        int sIdx = 0;
        long startTime;
        long endTime;
        double time;
        String lastName = "CASTRO";
        PrintWriter pw = new PrintWriter(new File(lastName + "_sortTimes.csv"));
        pw.println( "Size, BubbleSort, MergeSort, QuickSort, JavaSort" );
        while( sIdx < sizes.length )
        {
            int[] A = new int[sizes[sIdx]];
            int[] B = new int[sizes[sIdx]];
            int[] C = new int[sizes[sIdx]];
            int[] D = new int[sizes[sIdx]];
            int[] E = new int[sizes[sIdx]];
            for( int i=0; i<A.length; i++ )
            {
                A[i] = (int)(Math.random()*sizes[sIdx]*2);
                B[i] = A[i];
                C[i] = A[i];
                D[i] = A[i];
                E[i] = A[i];
            }
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("Initial Values: ");
            printArray(A);
            pw.print(A.length + ", ");

            System.out.println( "Starting bubble sort ---------------- Size = " + B.length);
            startTime = System.nanoTime();
            bubbleSort(B);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.;
            System.out.println( "Bubble sort finished - time = " + time + " seconds" );
            pw.print(time + ", ");
            printArray(B);
            System.out.println();

            System.out.println( "Starting merge sort ---------------- Size = " + C.length);
            startTime = System.nanoTime();
            C = mergeSort(C);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.;
            System.out.println( "Merge sort finished - time = " + time + " seconds.");
            pw.print(time + ", ");
            printArray(C);
            System.out.println();

            System.out.println( "Starting quick sort ---------------- Size = " + D.length);
            startTime = System.nanoTime();
            D = quickSort(D);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.;
            System.out.println( "quickSort finished - time = " + time  + " seconds." );
            pw.print(time + ", ");
            printArray(D);
            System.out.println();

            System.out.println( "Starting java's Array sort ---------------- Size = " + E.length);
            startTime = System.nanoTime();
            E = javaSort(E);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.;
            System.out.println( "Java's Array sort finished - time = " + time  + " seconds." );
            pw.println(time);
            printArray(E);
            System.out.println();

            sIdx = sIdx + 1;
        }
        pw.close();
    }
    public static void printArray(int[] X)
    {
        System.out.print("[ ");
        int i=0;
        for(i = 0; i < 15 && i < X.length; i++)
        {
            System.out.print( X[i] + " " );
        }
        if( i < X.length ) System.out.print("... ");
        System.out.println("]");
    }

    public static void bubbleSort(int[] A)
    {
        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A.length - 1; j++)
            {
                if(A[j] > A[j + 1])
                {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;

                }
            }
        }
    }

    public static int[] mergeSort(int[] A)
    {
        int n = A.length;
        if(n < 2)
            return A;

        int mid = A.length / 2;
        int[] left = new int[mid];
        int[] right = new int [n - mid];

        for(int i = 0; i < mid; i++)
            left[i] = A[i];
        for(int i = mid ; i < n ; i++)
            right[i - mid] = A[i];

        mergeSort(left);
        mergeSort(right);
        return merge(left, right, A);

    }
    public static int[] merge(int[] A, int[] B, int [] C)
    {
        /* return an array 'C' containing values of A and B in sorted order. */
        /* this method needs to run in linear time - one loop equal to size of C */

        int left = A.length;
        int right = B.length;


        int i = 0, j = 0, k = 0;

        while( i < left && j < right)
        {
            if(A[i] < B[j])
                C[k++] = A[i++];
            else
                C[k++] = B[j++];
        }

        while(i < left)
            C[k++] = A[i++];
        while(j < right)
            C[k++] = B[j++];

        return C;
    }
    public static int[] javaSort(int[] X)
    {
        /* use Arrays class to sort array X */
        Arrays.sort(X);
        return X;
    }
    public static int[] quickSort(int[] X)
    {
      /*quicksort doesn't require creating a new array
        so we need additional params for our
        recursive method */
        quickSort(X, 0, X.length-1);
        return X;
    }
    public static void quickSort(int[] X, int s, int e)
    {
        if(s >= e)
            return;

        int p = partition(X, s, e);

        quickSort(X, s, p - 1);
        quickSort (X, p + 1, e);

    }
    public static int partition( int[]A, int low, int high)
    {
        /* this is the our portion of quickSort */
        int pivot = low;
        for(int i = low + 1; i <= high; i++)
        {
            if(A[i] < A[low])
            {
                pivot++;
                int temp = A[i];
                A[i] = A[pivot];
                A[pivot] = temp;
            }
        }

        int temp = A[low];
        A[low] = A[pivot];
        A[pivot] = temp;

        return pivot;
    }
}