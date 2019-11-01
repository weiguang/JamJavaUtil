package com.okayjam.other;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/10/18 12:37
 **/
public class GraphTest {
    Point end = new Point(0,0);

    List<Point> conList = new ArrayList();
    @Test
    public void test() {
        conList.add(new Point(0,2));
        conList.add(new Point(3,0));
        conList.add(new Point(5,2));
        conList.add(new Point(2,4));

        for (Point point : conList) {
            Point start = point;
            List<Point> list= new ArrayList<>(20);
            go(start, list);
        }

    }


    public void go(Point point, List<Point> list) {
        list.add(point);

        if (list.size() > 16) {
            list.remove(list.size() -1);
            return;
        }
        if (point.equals(end))  {
            if ( list.containsAll(conList)) {
                list.stream().forEach(p1 -> {System.out.print(p1+"-"); });
                System.out.println();
            }
            list.remove(list.size() -1);
            return;
        }

        if(point.x > 0) {
            go(new Point(point.x -1, point.y), list);
        }
        if (point.y > 0) {
            go(new Point(point.x, point.y -1), list);
        }

        if(point.x < 5) {
            go(new Point(point.x +1, point.y), list);
        }

        if (point.y < 5) {
            go(new Point(point.x, point.y+1), list);
        }

        list.remove(list.size() -1);
    }

    class Point  {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point o2 = (Point)obj;
            return  this.x == o2.x && this.y == o2.y;
        }

        @Override
        public String toString() {
            return "[ "+ x + "," + y +"]";
        }
    }
}
