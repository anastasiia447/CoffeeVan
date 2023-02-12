package main.helpers;

import main.model.Coffee;

import java.util.ArrayList;

public final class Sorter {
    /* Heap Sort */

    public static void sortByCoffeeQuality(ArrayList<Coffee> arrayToSort)
    {
        int N = arrayToSort.size();

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapifyByCoffeeQuality(arrayToSort, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            Coffee temp = arrayToSort.get(0);
            arrayToSort.set(0, arrayToSort.get(i));
            arrayToSort.set(i, temp);

            // call max heapify on the reduced heap
            heapifyByCoffeeQuality(arrayToSort, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private static void heapifyByCoffeeQuality(ArrayList<Coffee> arr, int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr.get(l).getQualityMark() > arr.get(largest).getQualityMark())
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr.get(r).getQualityMark() > arr.get(largest).getQualityMark())
            largest = r;

        // If largest is not root
        if (largest != i) {
            Coffee swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            // Recursively heapify the affected subtree
            heapifyByCoffeeQuality(arr, N, largest);
        }
    }

    public static void sortByCoffeeCorrelation(ArrayList<Coffee> arrayToSort)
    {
        int N = arrayToSort.size();

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapifyByCoffeeCorrelation(arrayToSort, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            Coffee temp = arrayToSort.get(0);
            arrayToSort.set(0, arrayToSort.get(i));
            arrayToSort.set(i, temp);

            // call max heapify on the reduced heap
            heapifyByCoffeeCorrelation(arrayToSort, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private static void heapifyByCoffeeCorrelation(ArrayList<Coffee> arr, int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && (arr.get(l).getPrice() / arr.get(l).getNettoWeight()) > (arr.get(largest).getPrice() / arr.get(largest).getNettoWeight()))
            largest = l;


        // If right child is larger than largest so far
        if (r < N && (arr.get(r).getPrice() / arr.get(r).getNettoWeight())  > (arr.get(largest).getPrice() / arr.get(largest).getNettoWeight()))
            largest = r;

        // If largest is not root
        if (largest != i) {
            Coffee swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            // Recursively heapify the affected subtree
            heapifyByCoffeeCorrelation(arr, N, largest);
        }
    }
}
