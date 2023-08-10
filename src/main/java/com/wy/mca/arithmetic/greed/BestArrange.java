package com.wy.mca.arithmetic.greed;

import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Lists;
import com.wy.mca.util.PrintUtil;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @Description 会议室预订问题
 * @Author wangyong01
 * @Date 2022/7/13 8:59 下午
 * @Version 1.0
 */
public class BestArrange {

    public static void main(String[] args) {
        Meeting[] meetings = generateRandomMeetings(10, 100);

        PrintUtil.printObject(meetings);

        int greed = greed(meetings);
        System.out.println("greed:" + greed);

        int violentRecursiveInvoke = violentRecursiveInvoke(meetings);
        System.out.println("violentRecursiveInvoke:" + violentRecursiveInvoke);
    }

    /**
     * 1 问题描述
     *   1.1 有一些项目要定会议室进行宣讲，需要选择会议室的开始和结束时间，要求会议室的宣讲次数最多；
     *
     * 2 举例说明
     *   2.1 会议室预约时间段如下
     *       [1,5],[1,2],
     *       [2,8],
     *       [3,6],[3,5]
     *   2.2 那么可选的时间段为[1,2] [3,5]，此时一共可宣讲两次
     *
     * 3 贪心策略解题思路
     *   3.1 快速尝试
     *
     *   3.2 尝试策略
     *       a) 开始时间最小的进行贪心【贪心失败】
     *          反例：[1,100],[2,3][3,4][4,5]  的  最优解是：[2,3]	[3,4] [4,5]
     *       b) 持续时间较短的会议【贪心失败】
     *          反例：[1,27] [26,31] [29,100]，如果选择持续时间短的会议[26,31]，那么就不能选择[1,27] [29,100]，此时贪心失效
     *       c) 每次都选择结束时间最早的
     *          具体参考2.1
     *
     * 4 验证：暴力递归
     *
     * @param meetings
     * @return
     */
    public static int greed(Meeting[] meetings){
        if (ArrayUtil.isEmpty(meetings)){
            return 0;
        }
        Arrays.sort(meetings, Comparator.comparing(Meeting::getEnd));

        int result = 0;
        int curPos = 0;
        for (int i=0; i<meetings.length; i++){
            if (curPos <= meetings[i].start){
                result ++;
                curPos = meetings[i].end;
            }
        }

        return result;
    }

    public static int violentRecursiveInvoke(Meeting[] meetings){
        return violentRecursive(meetings, 0, 0);
    }

    /**
     * 暴力递归
     *
     * @param meetings          还剩下多少个会议室
     * @param result            已经安排了多少个会议室
     * @param currTimeLine      当前来到的时间点
     * @return
     */
    private static int violentRecursive(Meeting[] meetings, int result, int currTimeLine){
        if (ArrayUtil.isEmpty(meetings)){
            return result;
        }

        int max = result;
        for (int i=0; i<meetings.length; i++){
            if (currTimeLine <= meetings[i].start){
                Meeting[] newMeetings = getNewMeetingsExcludeIndex(meetings, i);
                max = Math.max(max,violentRecursive(newMeetings, result + 1, meetings[i].end));
            }
        }
        return max;
    }

    private static Meeting[] getNewMeetingsExcludeIndex(Meeting[] meetings, int index){
        List<Meeting> meetingList = Lists.newArrayList();
        for (int i=0; i<meetings.length; i++){
            if (i != index){
                meetingList.add(meetings[i]);
            }
        }
        return meetingList.toArray(new Meeting[0]);
    }

    private static Meeting[] generateRandomMeetings(int size, int range){
        Meeting[] meetings = new Meeting[size];
        Random random = new Random();
        for (int i=0; i<size; i++){
            int rangeLeft = random.nextInt(range);
            int rangeRight = random.nextInt(range);
            rangeLeft = rangeLeft < rangeRight ? rangeLeft : rangeRight;
            rangeRight = rangeLeft < rangeRight ? rangeRight : rangeLeft;
            meetings[i] = new Meeting(rangeLeft, rangeRight);
        }
        return meetings;
    }
}

@Data
class Meeting{

    /**
     * 开始时间
     */
    int start;

    /**
     * 结束时间
     */
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "-" + end +"]";
    }
}
