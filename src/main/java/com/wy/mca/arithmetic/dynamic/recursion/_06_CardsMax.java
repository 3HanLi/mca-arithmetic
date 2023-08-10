package com.wy.mca.arithmetic.dynamic.recursion;

/**
 * 纸牌问题
 *
 * 1）纸牌问题描述：有一个整型数组，从左到右依次代表一张纸牌，现在有玩家A和玩家B，每个玩家只能从最左边或者最右边拿走纸牌，返回获胜者分数
 *
 * 2）案例说明
 *    2.1 假如现在纸牌为：arr[] ={50, 100, 20, 10}
 *    2.2 玩家A：作为先手，如果先拿50，那么B可以拿100，直接获胜；因而玩家A需要先拿10，那么此时不论玩家B怎么拿，玩家A都将获胜
 *
 *    玩家A和玩家B取牌的顺序以及每次选择的策略，决定了谁最终获胜，也就是A和B互相影响，这种也叫做博弈模型
 *
 * 3）暴力递归
 *    3.1 定义先手函数violentPrior：表示在arr的L到R范围上能够获取的最大分数
 *        a) base case
 *           当L=R，先手直接取走，返回arr[L]
 *        b) 其他情况分析
 *           b.1 先手取走arr[L]，然后作为后手在[L+1,R]上取牌：arr[L] + violentLater(arr,L+1,R)
 *           b.2 先手取走arr[R]，然后作为后手在[L,R-1]上取牌：arr[R] + violentLater(arr,L,R-1)
 *           b.3 取最大值 Math.max(b.1,b.2)
 *
 *    3.2 定义后手函数violentLater：
 *        a) base base
 *           当L=R时，一定会被先手拿走，返回0
 *        b) 其他情况分析
 *           b.1 先手已经取走arr[L]，站在后手的角度，自己转换为了先手，只能在[L+1,R]上取牌：violentPrior(arr, L+1,R)
 *           b.2 先手已经取走arr[R]，站在后手的角度，自己转换为了先手，只能在[L,R-1]上取牌：violentPrior(arr, L,R-1)
 *           b.3 取最小值：Math.min(b.1,b.2)
 *               原因分析：先手已经尽力取到了最大值，那么后手只能取到最小值
 *
 * 4）暴力递归 -> 缓存表（动态规划）
 *    4.1 根据暴力递归画出递归调用，查看是否有重复解
 *                   vP(0,3)
 *               /            \
 *           vL(1,3)         vL(0,2)
 *           /    \          /     \
 *       vP(2,3)  vP(1,2)  vP(1,2) vP(0,1)
 *   4.2 通过递归调用图解，发现有重复解，可以改造动态规划
 *   4.3 观察递归调用的变量，定义缓存表
 *   4.4 对每个递归做如下操作：递归调用的时候，优先从缓存表取数据，取不到的话进行递归调用，将计算得到的结果放入缓存表
 *
 * 5）暴力递归 -> 位置依赖（动态规划）
 *   5.1 根据递归变量定义缓存表，并初始化
 *   5.2 分析位置依赖关系，将递归调用改为从数组中取值
 *
 * 6) 递归套路总结
 *    6.1 根据base case写出暴力递归
 *    6.2 根据暴力递归，改出缓存表
 *    6.3 根据缓存表，分析位置依赖，写出动态规划
 */
public class _06_CardsMax {

    public static void main(String[] args) {
        int[] arr = {50, 100, 20, 10};
        int maxScore = violentPrior(arr, 0, arr.length - 1);
        System.out.println("Violent MaxScore :" + maxScore);

        cacheMaxScore(arr);

        int dpMaxScore = dp(arr, 0, arr.length - 1);
        System.out.println("Dp maxScore:" + dpMaxScore);
    }

    /**
     * 暴力递归 先手函数
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int violentPrior(int[] arr, int L, int R){
        if (L == R){
            return arr[L];
        }
        int p1 = arr[L] + violentLater(arr, L+1, R);
        int p2 = arr[R] + violentLater(arr, L, R-1);
        return Math.max(p1, p2);
    }

    /**
     * 暴力递归 后手函数
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int violentLater(int[] arr, int L, int R){
        if (L == R){
            return 0;
        }
        int p1 = violentPrior(arr, L+1, R);
        int p2 = violentPrior(arr, L, R-1);
        return Math.min(p1, p2);
    }

    /**
     * 动态规划：缓存表
     *
     * @param arr
     */
    private static void cacheMaxScore(int[] arr){
        int N = arr.length;
        int[][] priorCache = new int[N][N];
        int[][] laterCache = new int[N][N];

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                priorCache[i][j] = -1;
                laterCache[i][j] = -1;
            }
        }

        int maxScore = cachePrior(arr, 0, arr.length - 1, priorCache, laterCache);
        System.out.println("Cache MaxScore :" + maxScore);
    }

    /**
     * 暴力递归 先手函数->改缓存表
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int cachePrior(int[] arr, int L, int R, int[][] priorCache, int[][] laterCache){
        //缓存法:优先判断缓存是否有值，有的话返回，避免重复递归
        if (priorCache[L][R] != -1){
            return priorCache[L][R];
        }

        int result;
        if (L == R){
            result = arr[L];
        }else {
            int p1 = arr[L] + cacheLater(arr, L+1, R, priorCache, laterCache);
            int p2 = arr[R] + cacheLater(arr, L, R-1, priorCache, laterCache);
            result = Math.max(p1, p2);
        }
        priorCache[L][R] = result;

        return result;
    }

    /**
     * 暴力递归 后手函数 -> 缓存表
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int cacheLater(int[] arr, int L, int R, int[][] priorCache, int[][] laterCache){
        if (laterCache[L][R] != -1){
            return laterCache[L][R];
        }
        int result;
        if (L == R){
            result = 0;
        }else {
            int p1 = cachePrior(arr, L+1, R, priorCache, laterCache);
            int p2 = cachePrior(arr, L, R-1, priorCache, laterCache);
            result = Math.min(p1, p2);
        }
        laterCache[L][R] = result;

        return result;
    }

    /**
     * 动态规划 -> 基于位置依赖
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int dp(int[] arr, int L, int R){
        int N = arr.length;

        int[][] prior = new int[N][N];
        int[][] later = new int[N][N];
        for (int i=0; i<L; i++){
            //1 根据base case填充
            prior[i][i] = arr[i];
        }

        //3 杨辉三角
        //3.1 这里我们需要填充的是对角线，也就是只需要填充右上部分的三角形
        for (int col=1; col<N; col++){
            int startRow = 0;
            int startCol = col;

            //3.2 根据对角线填充
            while (startCol < N){
                prior[startRow][startCol] = Math.max(arr[startRow] + later[startRow+1][startCol], arr[startCol] + later[startRow][startCol-1]);
                later[startRow][startCol] = Math.min(prior[startRow+1][startCol], prior[startRow][startCol-1]);

                startRow ++;
                startCol ++;
            }
        }

        //2 根据入参返回动态规划结果
        return Math.max(prior[0][R],later[0][R]);
    }


}
