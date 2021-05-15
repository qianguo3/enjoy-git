package hello;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class test01 {
    public static void main(String[] args) {
//        test1(1L, 200_0000_0000L);//执行时间为10570毫秒  sum = -2914184820805067776
        test2(1L, 200_0000_0000L);//执行时间为202979毫秒   sum = -2935156330807653376
//        test3(1L, 200_0000_0000L);//执行时间为15894毫秒   sum = -2914184800805067776
    }

    public static void test1(long start, long end) {
        Instant instant = Instant.now();
        long sum = 0;
        for (long i = start; i < end; i++) {
            sum += i;
        }
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        System.out.println("执行时间为" + duration.toMillis() + "毫秒");
        System.out.println("sum = " + sum);

    }

    public static void test2(long start, long end) {
        Instant instant = Instant.now();
        ForkJoinPool joinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(start, end);
        ForkJoinTask<Long> result = joinPool.submit(task);//提交任务
        Long sum = null;
        try {
            sum = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        System.out.println("执行时间为" + duration.toMillis() + "毫秒");
        System.out.println("sum = " + sum);

    }

    // stream 并行流
    public static void test3(Long start, Long end) {
        Instant instant = Instant.now();
        //range() 开区间   rangeClosed() 闭区间左开右闭
        long sum = LongStream.rangeClosed(start, end).parallel().reduce(0, Long::sum);
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        System.out.println("执行时间为" + duration.toMillis() + "毫秒");
        System.out.println("sum = " + sum);

    }
}

/**
 * 求和计算的任务
 * 1\. forkjoinpool 通过它来执行
 * 2\. 计算任务forkJoinPool, execute(forkjoinTask task)
 * 3\. 计算类要继承自forkjointask
 */
class ForkJoinDemo extends RecursiveTask<Long> {

    private long start;     // 1
    private long end;       // 20_0000_0000
    private long temp = 1_0000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    //计算方法
    @Override
    protected Long compute() {
        if (end - start < temp) {

            Long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //分支合并计算
            long middle = (end + start) / 2;  //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); // 拆分任务,把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();// 拆分任务,把任务压入线程队列

            return task1.join() + task2.join();
        }
    }
}
