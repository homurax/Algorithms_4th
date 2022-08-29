# 排序

## 初级排序算法

**运行时间**

排序成本模型。在研究排序算法时，需要计算**比较**和**交换**的数量。对于不交换元素的算法，会计算访问数组的次数。

**额外内存使用**

排序算法可以分为两类：除了函数调用所需的栈和固定数目的实例变量之外无需额外内存的原地排序算法，以及需要额外内存空间来存储另 一份数组副本的其他排序算法。



### 选择排序

首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。

再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。

这种方法叫做选择排序，因为它在不断地选择剩余元素之中的最小者。

```java
public class SortUtil {

    public static <T extends Comparable<T>> boolean less(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    public static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static <T extends Comparable<T>> void show(T[] a) {
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



```java
public static <T extends Comparable<T>> void sort(T[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
        int minIndex = i;
        for (int j = i + 1; j < N; j++) {
            if (SortUtil.less(a[j], a[minIndex])) {
                minIndex = j;
            }
        }
        SortUtil.swap(a, i, minIndex);
    }
}
```

对于长度为 N 的数组，选择排序需要大约 $\cfrac{N^2}{2}$ 次比较和 $N$ 次交换。

选择排序的内循环只是在比较当前元素与目前已知的最小元素。交换元素的代码写在内循环之外，每次交换都能排定一个元素，因此交换的总次数是 N。所以算法的时间效率取决于比较的次数。

- **运行时间和输入无关**

  为了找出最小的元素而扫描一遍数组并不能为下一遍扫描提供什么信息。

  这种性质在某些情况下是缺点，因为一个已经有序的数组或是用来比较的主键全部相等的数组和一个元素随机排列的数组所用的排序时间一样长。

- **数据移动是最少的**

  每次交换都会改变两个数组元素的值，因此选择排序用了 N 次交换，交换次数和数组的大小是线性关系。



### 插入排序

构建有序序列，对于未排序数据，在已排序序列中扫描，找到相应位置并插入。当前索引左边的所有元素都是有序的，当索引到达数组的右端时，数组排序就完成了。

```java
public static <T extends Comparable<T>> void sort(T[] a) {
    int N = a.length;
    for (int i = 1; i < N; i++) {
        for (int j = i; j > 0 && SortUtil.less(a[j], a[j - 1]); j--) {
            SortUtil.swap(a, j, j - 1);
        }
    }
}
```

对于随机排列的长度为 N 且主键不重复的数组，平均情况下插入排序需要 $\cfrac{N^2}{4}$ 次比较以及 $\cfrac{N^2}{4}$ 次交换。最坏情况下需要 $\cfrac{N^2}{2}$ 次比较和 $\cfrac{N^2}{2}$ 次交换，最好情况下需要 $N-1$ 次比较和 $0$ 次交换。

**插入排序所需的时间取决于输入中元素的初始顺序。**

倒置指的是数组中的两个顺序颠倒的元素。如果数组中倒置的数量小于数组大小的某个倍数，那么可以说这个数组是部分有序的。

- 数组中每个元素距离它的最终位置都不远
- 一个有序的大数组接一个小数组
- 数组中只有几个元素的位置不正确

插入排序需要的交换操作和数组中倒置的数量相同，需要的比较次数大于等于倒置的数量，小于等于倒置的数量加上数组的大小再减一。

在内层循环中将较大的元素都向右移动而不总是交换两个元素（这样访问数组的次数就能减半）可以提升插入排序的速度。

插入排序对于部分有序的数组十分高效，也很适合小规模数组。这些类型的数组在实际应用中经常出现， 而且它们也是高级排序算法的中间过程。

对于随机排序的无重复主键的数组，插入排序和选择排序的运行时间是平方级别的， 两者之比应该是一个较小的常数。



### 希尔排序（递减增量排序算法）

对于大规模乱序数组插入排序很慢，因为它只会交换相邻的元素，因此元素只能一点一点地从数组的一端移动到另一端。

希尔排序的思想是将全部元素分为几个区域来提升插入排序的性能，使数组中任意间隔为 step 的元素都是有序的。如果 step 很大，就可以让一个元素可以一次性地朝最终位置前进一大步，为实现更小的 step 有序创造方便。然后算法再取越来越小的 step 进行排序，算法的最后一步就是普通的插入排序。

一个更好理解的希尔排序实现：将数组列在一个表中并对列用插入排序。重复这过程，不过每次用更长的列来进行。最后整个表就只有一列了。将数组转换至表是为了更好地理解这算法，算法本身仅仅对原数组进行排序。

````java
public static <T extends Comparable<T>> void sort(T[] a) {
    int N = a.length;
    int step = 1;
    // 序列使用 1/2(3^k - 1)
    while (step < N / 3) { // 1, 4, 13, 40, 121, 364, 1093, ...
        step = 3 * step + 1;
    }
    while (step >= 1) {
        for (int i = step; i < N; i++) {
            for (int j = i; j >= step && SortUtil.less(a[j], a[j - step]); j -= step) {
                SortUtil.swap(a, j, j - step);
            }
        }
        step = step / 3;
    }
}
````

**步长的选择是希尔排序的重要部分。**只要最终步长为 1 的任何步长序列都可以工作。算法最开始以一定的步长进行排序，然后会继续以一定步长进行排序，最终算法以步长为1进行排序。当步长为 1 时，算法变为普通插入排序，这就保证了数据一定会被排序。

已知的较好步长序列是由 Sedgewick 提出的 **(1, 5, 19, 41, 109,...)**。

另一个在大数组中表现优异的步长序列是斐波那契数列除去 0 和 1 将剩余的数以黄金分割比的两倍的幂进行运算得到的数列：**(1, 9, 34, 182, 836, 4025, 19001, 90358, 428481, 2034035, 9651787, 45806244, 217378076, 1031612713,…)**。

[更多的序列选择](https://en.wikipedia.org/wiki/Shellsort#Gap_sequences)

与选择排序以及插入排序相比，希尔排序也可以用于大型数组。



## 归并排序

要将一个数组排序，可以先递归地将它分成两半并分别排序，然后将结果归并起来。即将两个有序的数组归并成一个更大的有序数组。



### 归并

将所有元素复制到一个辅助数组中，再把归并的结果放回原数组中。

```java
public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
    int i = lo, j = mid + 1;
    System.arraycopy(a, lo, aux, lo, hi - lo + 1);
    for (int k = lo; k <= hi; k++) {
        if (i > mid) a[k] = aux[j++]; // 左边用尽 取右边
        else if (j > hi) a[k] = aux[i++]; // 右边用尽 取左边
        else if (SortUtil.less(aux[j], aux[i])) a[k] = aux[j++]; // 取更小的一边
        else a[k] = aux[i++];
    }
}
```



### 自顶向下的归并排序

```java
public class Merge {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + ((hi - lo) >> 1);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (SortUtil.less(a[mid + 1], a[mid])) {
            merge(a, aux, lo, mid, hi);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++]; // 左边用尽 取右边
            else if (j > hi) a[k] = aux[i++]; // 右边用尽 取左边
            else if (SortUtil.less(aux[j], aux[i])) a[k] = aux[j++]; // 取更小的一边
            else a[k] = aux[i++];
        }
    }

}
```

对于长度为 N 的任意数组，自顶向下的归并排序需要 `½NlgN` 至 `NlgN` 次比较。自顶向下的归并排序最多需要访问数组 `6NlgN` 次（`2N` 次用来复制，`2N` 次用来将排好序的元素移动回去， 另外最多比较 `2N` 次）。

**归并排序所需的时间和 `NlgN` 成正比，主要缺点是辅助数组所使用的额外空间和 N 的大小成正比。**



### 自底向上的归并排序

```java
@SuppressWarnings("unchecked")
public static <T extends Comparable<T>> void sort(T[] a) {
    int len = a.length;
    T[] aux = (T[]) new Comparable[a.length];
    for (int size = 1; size < len; size *= 2) {
        for (int idx = 0; idx < len - size; idx += size * 2) {
            Merge.merge(a, aux, idx, idx + size - 1, Math.min(idx + size + size - 1, len - 1));
        }
    }
}
```



### 排序算法的复杂度

没有任何基于比较的算法能够保证使用少于 `lg(N!) ～ NlgN` 次比较将长度为 N 的数组排序。

归并排序是一种渐进最优的基于比较排序的算法。



















