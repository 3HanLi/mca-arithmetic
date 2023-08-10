package com.wy.mca.arithmetic.dynamic.recursion;


/**
 * 1) 机器人问题描述：假如一共有N位置，机器人一开始在start位置，需要走K步后刚好落在aim上面，请问有多少种走法
 *
 * 2) 案例说明
 *    2.1 假如现在：N=7, start=2, aim=4, K=4，转化如下：
 *        1 2 3 4 5 6 7，机器人一开始在start=2位置，需要走4步到目标4
 *    2.2 那么可能的方案有
 *        2->3->4->5->4
 *        2->3->2->3->4
 *        2->1->2->3->4
 *        2->3->4->3->4
 *
 * 3) 暴力递归解决思路
 *    3.1 base case
 *        当剩余要走的步数step=0时，如果当前位置cur=aim，说明找到了一种解决方案，否则说明没找到
 *    3.2 其他情况分析
 *        a) 当cur=1，那么只能向右走，需要向rest-1步
 *        b) 当cur=N，那么只能向左走，需要走rest-1步
 *        c) 当1<cur<N，那么既可以向左走rest-1，也可以向右走rest-1
 *
 * 4) 暴力递归->缓存法 动态规划
 *    4.1 根据暴力递归画出递归调用，查看是否有重复解，假如：N=20,start=7,aim=13,K=10
 *                  (7,10)  表示从当前位置到目标值的步数，这里是：从7出发，到目标需要10步
 *                /        \
 *            (8,9)       (6,9)
 *          /     \      /     \
 *       (7,8)  (9,8)  (7,8)   (5,8)
 *    4.2 通过案例分析得出有重复解(7,8)，由此得出可以进行动态规划改造
 *    4.3 观察递归调用的变量，根据变量的变化范围定义缓存表
 *    4.4 递归调用的时候，优先从缓存表取数据，取不到的话进行递归调用，将计算得到的结果放入缓存表
 *    4.5 重复上面步骤
 *
 * 5) 暴力递归->位置依赖 动态规划
 *    5.1 观察递归调用的变量，根据变量的变化范围定义缓存表
 *    5.2 分析位置依赖关系，填表格，然后拷贝暴力递归代码改造即可
 *    5.3 将递归调用改为从数组中取值即可
 */
public class _05_RobotWalk {

    public static void main(String[] args) {
        int way1 = violentWalk(2, 4, 4, 5);
        System.out.println("ways1->" + way1);
        int ways2 = walkCacheDynamic(2, 4, 4, 5);
        System.out.println("ways2->" + ways2);
        int ways3 = walkDynamic(2, 4, 4, 5);
        System.out.println("ways3->" + ways3);
    }

    /**
     * 暴力破解
     *
     * @param start 机器人一开始在那个位置
     * @param step  机器人需要走step步到达aim位置
     * @param aim   目标位置
     * @param n     范围约束
     * @return
     */
    public static int violentWalk(int start, int step, int aim, int n){
        return processViolentWalk(start, step, aim, n);
    }

    private static int processViolentWalk(int cur, int rest, int aim, int n){
        //1.1 当rest=0时，如果当前位置cur刚好在aim上，那么说明找到了一种解决方案
        if (rest == 0){
            return cur == aim ? 1 : 0;
        }else {
            //2.1 当cur=1时，当前位置cur在最左侧，那么来到2位置，然后走rest-1步
            if (cur == 1){
                return processViolentWalk(2, rest-1, aim, n);
            }
            //2.2 当cur=n时，当前位置cur在最右侧，那么只能来到n-1位置，然后走rest-1步
            else if (cur == n){
                return processViolentWalk(n-1, rest-1, aim, n);
            }
            //2.3 如果1<cur<n，当前位置cur在中间，那么结果是：来到左侧位置，然后走rest-1步  +  来到右侧位置，然后走rest-1步
            else {
                return processViolentWalk(cur-1, rest-1, aim, n) + processViolentWalk(cur+1, rest-1, aim, n);
            }
        }
    }

    /**
     * 暴力递归->缓存法动态规划
     *
     * @param start
     * @param step
     * @param aim
     * @param n
     * @return
     */
    public static int walkCacheDynamic(int start, int step, int aim ,int n){
        //1.1 根据暴力递归processViolentWalk 发现变量为cur和rest，分析cur和rest的范围
        int[][] dp = new int[n+1][step+1];
        for (int i=0; i<dp.length; i++){
            for (int j=0; j<dp[i].length; j++){
                dp[i][j] = -1;
            }
        }
        return processCacheWalk(start, step, aim, n, dp);
    }

    private static int processCacheWalk(int cur, int rest, int aim, int n, int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        int answer = 0;
        if (rest == 0){
            answer = cur == aim ? 1 : 0;
        }else {
            if (cur == 1){
                answer = processCacheWalk(2, rest-1, aim, n, dp);
            } else if (cur == n){
                answer = processCacheWalk(n-1, rest-1, aim, n, dp);
            } else {
                answer = processCacheWalk(cur-1, rest-1, aim, n, dp) + processCacheWalk(cur+1, rest-1, aim, n, dp);
            }
        }
        dp[cur][rest] = answer;
        return answer;
    }

    /**
     * 暴力递归->位置依赖的动态规划
     *
     * @param cur
     * @param rest
     * @param aim
     * @param n
     * @return
     */
    public static int walkDynamic(int cur, int rest, int aim, int n){
        //1.1 根据变量范围定义缓存表：行表示当前位置cur(范围1-n)，列表示还剩下几步rest(范围1-rest)
        int[][] dp = new int[n+1][rest+1];

        //2 拷贝暴力递归代码
        //2.1 根据base case填表格，填充第0列
        dp[aim][0] = 1;
        //2.2 根据其他情况从第一列自左向右填表格
        for (int i=1; i<=rest; i++){
            //填充第一行
            dp[1][i] = dp[2][i-1];
            //填充2~N-1行
            for (int j=2; j<n; j++){
                dp[j][i] = dp[j-1][i-1] + dp[j+1][i-1];
            }
            //填充最后一行
            dp[n][i] = dp[n-1][i-1];
        }
        return dp[cur][rest];
    }

}
