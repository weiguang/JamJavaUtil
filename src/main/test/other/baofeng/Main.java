package other.baofeng;


/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/19 19:46.
 */
public class Main {
    double base_fixed = 100;
    double base_2 = 100;
    int base_hours = 50;
    int over_cost = 5;
    int cost_per_hour = 10;
    public static void main (String[] args) {
       // System.out.println("Hello world!");
        new Main().test();
    }

    void test () {
        System.out.println("----------使用第一个--------------");
        Context u1 = new Context(new User1());
        System.out.println( u1.operate());

    }

     interface Strategy {
        public double operate();
    }

     class User1 implements Strategy {
        @Override
        public double operate() {
           return base_fixed;
        }
    }

     class User2 implements Strategy {
        int hours;
        public User2 (int hours) {
            this.hours = hours;
        }
        @Override
        public double operate() {
           return base_2 + (hours - base_hours) * over_cost;
        }
    }

     class User3 implements Strategy {
        int hours;
        public User3 (int hours) {
            this.hours = hours;
        }
        @Override
        public double operate() {
            return hours * cost_per_hour;
        }
    }


     class Context {
        private Strategy strategy;
        //构造函数，要你使用哪个策略
        public Context(Strategy strategy){
            this.strategy = strategy;
        }
        public void setStrategy(Strategy strategy){
            this.strategy = strategy;
        }
        public double operate(){
            return this.strategy.operate();
        }
    }

}

