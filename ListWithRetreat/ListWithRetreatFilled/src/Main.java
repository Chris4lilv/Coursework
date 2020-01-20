import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;

public class Main {

    public static void main(String[] args) {
        System.out.println("Execution times estimation");
        System.out.println(
                "All execution times listed below are the average after executing 10000 times.");
        Stopwatch timer = new Stopwatch1();
        List3 list = new List3();

        //Estimate addRightFront()
        System.out.println("Estimating addRightFront()......");
        timer.start();
        for (int i = 0; i < 10000; i++) {
            list.addRightFront(i);
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 10000.00);
        timer.clear();

        //Estimate removeRightFront()
        System.out.println("Estimating removeRightFront()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.removeRightFront();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();

        //Estimate advance()
        System.out.println("Estimating advance()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.advance();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();

        //Estimate moveToStart()
        System.out.println("Estimating moveToStart()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.moveToStart();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();

        //Estimate moveToFinish()
        System.out.println("Estimating moveToFinish()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.moveToFinish();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();

        //Estimate retreat()
        System.out.println("Estimating retreat()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.retreat();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();

        //Estimate rightLength()
        System.out.println("Estimating rightLength()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.rightLength();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();

        //Estimate leftLength()
        System.out.println("Estimating leftLength()......");
        timer.start();
        for (int i = 0; i < 5000; i++) {
            list.leftLength();
        }
        timer.stop();
        System.out.println(
                "Average execution time: " + timer.elapsed() / 5000.00);
        timer.clear();
    }

}
