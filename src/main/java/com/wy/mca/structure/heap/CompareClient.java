package com.wy.mca.structure.heap;

import com.wy.mca.common.Student;
import com.wy.mca.util.PrintUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 比较器使用
 * @author wangyong01
 */
public class CompareClient {

    public static void main(String[] args) {
        Student stu01 = new Student(5, 1, "name01");
        Student stu02 = new Student(3, 4, "name02");
        Student stu03 = new Student(5, 7, "name03");
        Student[] students = {stu01, stu02, stu03,};
        //1.1 按照Id进行升序
        Arrays.sort(students, new IdComparator());
        PrintUtil.printObject(students);

        //1.2 按照Id升序,Age降序
        Arrays.sort(students, new IdAgeComparator());
        PrintUtil.printObject(students);

        //1.3 有序表使用的时候，需要指定比较器，如果比较器的值相等，元素不添加
        //1.4 而HashMap会覆盖
        TreeMap<Student, String> treeMap = new TreeMap<>(new IdComparator());
        treeMap.put(stu01, "stu01");
        treeMap.put(stu02, "stu02");
        treeMap.put(stu03, "stu03");
        System.out.println("有序表TreeMap添加元素");
        treeMap.keySet().forEach(System.out::println);

        //1.3.1 通过有序表使用比较器的另一种方式
        TreeMap<Student, String> treeMap2 = new TreeMap<>((a,b) -> a.getId() - b.getId());
        treeMap2.put(stu01, "stu01");
        treeMap2.put(stu02, "stu02");
        treeMap2.put(stu03, "stu03");
        System.out.println("有序表TreeMap添加元素");
        treeMap2.keySet().forEach(System.out::println);
    }

    /**
     * 按照Id升序
     *
     * 比较器Comparator.compare(o1,o2)详解
     * 1) 返回负数，表示o1在前
     * 2) 返回正数，表示o2在前
     * 3) 返回0，表示谁在前都无所谓
     *
     * 总结：
     * 正序排序：o1 - o2
     * 逆序排序：o2 - o1
     */
    private static class IdComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getId() - o2.getId();
        }

    }

    /**
     * 按照Id升序,Age降序
     */
    private static class IdAgeComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getId() == o2.getId() ? o1.getAge() - o2.getId() : o1.getId() - o2.getId();
        }
    }

}
