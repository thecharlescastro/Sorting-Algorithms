public class QS {
    public static void main(String[] args){
        int[] A = {8 , 3, 7, 4, 6};
        int[] B = {1, 2, 3, 4, 10};
        int[] C = {4, 6, 7, 2, 1};

        System.out.println("QuickSort Not Sorted");

        for(int i = 0; i < A.length; i++)
            System.out.print(A[i] + " ");

        quickSort(A, 0, A.length -1);
        System.out.println("\nQuickSort Sorted");

        for(int i = 0; i < A.length; i++)
            System.out.print(A[i] + " ");

        System.out.println();
        System.out.println("Binary Search: ");
        System.out.println(BinarySearch(0 ,B.length - 1, 10, B));

        System.out.println("Unsorted MergeSort Search: ");
        for(int i = 0; i < C.length; i++){
            System.out.print(C[i] + " ");
        }
        System.out.println();
        System.out.println("Sorted MergeSort Search: ");
        mergeSort(C);

        for(int i = 0; i < C.length; i++) {
            System.out.print(C[i] + " ");
        }
    }
    public static int[] mergeSort(int[] A){
        int n = A.length;

        if(n < 2)
            return A;

        int[] left = new int[n / 2];
        int[] right = new int[n - (n / 2)];
        int j = 0;
        for(int i = 0; i < left.length; i++)
            left[i] = A[i];
        for(int i = A.length / 2; i < A.length; i++)
            right[j++] = A[i];

        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right, A);




    }
    public static int[] merge(int[] A, int[] B, int[] C){
        int nl = A.length;
        int nr = B.length;

        int i = 0, j = 0, k = 0;

        while(i < nl && j < nr){
            if(A[i] < B[j])
                C[k++] = A[i++];
            else
                C[k++] = B[j++];
        }
        while(i < nl)
            C[k++] = A[i++];
        while(j < nr)
            C[k++] = B[j++];

        return C;

    }
    public static void quickSort(int[] A,int s, int e){
        if(s>=e)
            return;
        else{

            int p = pivot(A, s, e);
            quickSort(A, s, p - 1);
            quickSort(A, p+1, e);
        }

    }
    public static int pivot(int[] A, int low, int high){
        int p = 0;
        for(int i = low + 1; i <= high; i++){
            if(A[i] < A[low]){
                p++;

                int temp = A[i];
                A[i] = A[p];
                A[p] = temp;
            }
        }
        int temp = A[low];
        A[low] = A[p];
        A[p] = temp;

        return p;

    }
    public static int BinarySearch(int low, int high, int n, int[] A){
        int mid = (low + high) / 2;

        if(low > high)
            return -1;
        else{
            if(n == A[mid])
                return A[mid];
            else if(A[mid] > n)
                return BinarySearch(low, mid - 1, n, A);
            else
                return BinarySearch(mid + 1, high, n, A);
        }

    }
}
