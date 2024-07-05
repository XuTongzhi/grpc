package completeable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class CompleableFutureTest {

    public static void main(String[] args) {

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1洗水壶");
            sleep(1,TimeUnit.SECONDS); // Simulate time taken to boil water
            System.out.println("T1烧开水");
            sleep(15,TimeUnit.SECONDS);
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2洗茶壶");
            sleep(1,TimeUnit.SECONDS); // Simulate time taken to boil water
            System.out.println("T2洗茶杯");
            sleep(2,TimeUnit.SECONDS);
            System.out.println("T2拿茶叶");
            sleep(1,TimeUnit.SECONDS);
            return "红茶";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2,(ff,tf) ->{
            System.out.println("T1拿茶叶"+tf);
            System.out.println("T1泡茶"+tf);
            return "喝茶：" + tf;
        });

        System.out.println(f3.join());

    }

    static void sleep(int num, TimeUnit u){
        try {
            u.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
