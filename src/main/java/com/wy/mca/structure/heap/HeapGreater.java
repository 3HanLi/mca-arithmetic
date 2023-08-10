package com.wy.mca.structure.heap;

import java.util.*;

/**
 * 1) 加强堆功能：
 *    1.1 满足堆的常用方法
 *    1.2 当调整对象时，动态调整堆结构
 * 2) 注意事项：
 *    2.1 这里的T不能是基础类型(包含字符串)，如果基础类型需要支持加强堆，可以再封装一层，如：Inner<T>
 *    2.2 代码中的注释：是站在小根堆的立场写该加强堆
 *    2.3 可以通过调整比较器，实现大根堆的效果
 *    2.4 T不建议重写equals和hashcode方法，这样可能会导致属相相同的对象加入小根堆时出问题
 *
 * @author wangyong01
 */
public class HeapGreater<T> {

    /**
     * 底层数组
     */
    private List<T> queueList;

    /**
     * 反向索引：对象->Index索引
     */
    private Map<T, Integer> objectIndexMap;

    /**
     * 堆大小
     */
    private int heapSize;

    /**
     * 比较器
     */
    private Comparator<T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        this.queueList = new ArrayList<>();
        this.objectIndexMap = new HashMap<>();
        this.comparator = comparator;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 添加元素
     *
     * @param obj
     */
    public void push(T obj) {
        //1.1 添加元素，并维护反向索引
        queueList.add(obj);
        objectIndexMap.put(obj, heapSize);
        //1.2 元素上浮->调整堆结构
        heapInsert(heapSize);
        heapSize++;
    }

    /**
     * 弹出堆顶元素
     *
     * @return
     */
    public T pop() {
        //1.1 获取堆顶元素
        T top = queueList.get(0);
        //1.2 删除堆顶元素
        remove(top);
        //1.3 返回堆顶元素
        return top;
    }

    public T peek() {
        return queueList.get(0);
    }

    /**
     * 删除元素
     *
     * @param obj
     */
    public void remove(T obj) {
        //1.1 获取最后一个元素，替换指定元素
        T lastEle = queueList.get(heapSize - 1);

        //1.2 删除指定元素的反向索引和最后一个元素
        Integer objIndex = objectIndexMap.get(obj);
        objectIndexMap.remove(obj);
        queueList.remove(--heapSize);

        //1.3 删除的元素不是最后一个时才调整
        if (lastEle != obj){
            //1.4 维护最后一个元素反向索引为删除元素的索引
            queueList.set(objIndex, lastEle);
            objectIndexMap.put(lastEle, objIndex);
            resign(lastEle);
        }
    }

    /**
     * 对象属性变动时，重新调整堆结构
     *
     * @param obj
     */
    public void resign(T obj) {
        heapInsert(objectIndexMap.get(obj));
        heapify(objectIndexMap.get(obj));
    }

    public List<T> getAllElements() {
        return queueList;
    }

    public boolean contains(T obj){
        return queueList.contains(obj);
    }

    /**
     * 元素上浮 ==> 参考HeapUtil.heapInsert
     *
     * @param index
     */
    public void heapInsert(int index) {
        int parentIndex = (index - 1) / 2;
        while (comparator.compare(queueList.get(index), queueList.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    /**
     * 元素下沉 ==> 参考HeapUtil.heapify
     *
     * @param index
     */
    public void heapify(int index) {
        int leftIndex = 2 * index + 1;
        while (leftIndex < heapSize){
            int largestIndex = leftIndex + 1 == heapSize ? leftIndex :
                    (comparator.compare(queueList.get(leftIndex), queueList.get(leftIndex+1)) < 0 ? leftIndex+1 : leftIndex);
            if (comparator.compare(queueList.get(index), queueList.get(largestIndex)) < 0){
                break;
            }
            swap(index, leftIndex);
            index = leftIndex;
            leftIndex = 2 * index + 1;
        }
    }

    /**
     * 元素交换并维护反向索引
     *
     * @param srcIndex
     * @param destIndex
     */
    public void swap(int srcIndex, int destIndex) {
        T src = queueList.get(srcIndex);
        T dest = queueList.get(destIndex);
        queueList.set(srcIndex, dest);
        queueList.set(destIndex, src);
        objectIndexMap.put(src, destIndex);
        objectIndexMap.put(dest, srcIndex);
    }

}
