# 排序

## 初级排序算法

**运行时间**

排序成本模型。在研究排序算法时，需要计算*比较*和*交换*的数量。对于不交换元素的算法， 会计算访问数组的次数。

**额外内存使用**

排序算法可以分为两类：除了函数调用所需的栈和固定数目的实例变量之外无需额外内存的*原地排序算法*，以及需要额外内存空间来存储另 一份数组副本的*其他排序算法*。



### 选择排序

首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。

再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。

这种方法叫做选择排序，因为它在不断地选择剩余元素之中的最小者。

```java
public class Selection {
    
    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[i])) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    private static <T extends Comparable<T>> boolean less(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static <T extends Comparable<T>> void show(T[] a) {
        System.out.println(Arrays.toString(a));
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
```

选择排序的内循环只是在比较当前元素与目前已知的最小元素。交换元素的代码写在内循环之外，每次交换都能排定一个元素，因此交换的总次数是 N。所以算法的时间效率取决于比较的次数。

**对于长度为 N 的数组，选择排序需要大约 `N^2/2` 次比较和 `N` 次交换。**

> 0 到 `N-1` 的任意 `i` 都会进行一次交换和 `N-1-i` 次比较，因此总共有 `N` 次交换以及 `(N-1)+(N-2)+...+2+1=N(N-1)/2 ～ N^2/2` 次比较。



- **运行时间和输入无关**

  为了找出最小的元素而扫描一遍数组并不能为下一遍扫描提供什么信息。

  这种性质在某些情况下是缺点，因为一个已经有序的数组或是用来比较的主键全部相等的数组和一个元素随机排列的数组所用的排序时间一样长。

- **数据移动是最少的**

  每次交换都会改变两个数组元素的值，因此选择排序用了 N 次交换，交换次数和数组的大小是线性关系。



