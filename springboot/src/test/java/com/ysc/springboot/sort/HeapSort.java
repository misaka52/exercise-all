package com.ysc.springboot.sort;

/**
 * @author yuanshancheng
 * @date 2021/4/28
 */
public class HeapSort implements MySort {
    @Override
    public void sort(int[] data) {
        int len = data.length;
        for (int i = len / 2; i >= 0; --i) {
            maxHeap(data, i, len);
        }
        for (int i = len - 1; i >= 0; --i) {
            swap(data, 0, i);
            maxHeap(data, 0, i);
        }
    }

    private void maxHeap(int[] data, int start, int end) {
        int parent = start;
        int son = parent * 2 + 1;
        while (son < end) {
            int tmp = son;
            int sonBrother = parent * 2 + 2;
            if (sonBrother < end && data[sonBrother] > data[tmp]) {
                tmp = sonBrother;
            }
            if (data[parent] < data[tmp]) {
                swap(data, parent, tmp);
                parent = tmp;
                son = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
