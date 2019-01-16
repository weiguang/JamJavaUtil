package main.com.okayjam.job.other.hypereal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/5 15:02.
 */
public class Main {



    /**
     *
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on
     * @param
     * @return void
     */
    public void setZero(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for(int j=0;j<data[i].length;j++){
              if(data[i][j] == 0) {
                  for (int k = i; k < data.length; k++) {
                      data[k][j] = 0;
                  }
                  for (int k = j; k < data[i].length; k++) {
                      data[i][k] = 0;
                  }
              }
            }
        }
                }

    public int search(int[][] M1, int[][]M2) {
            int re = 0;
        for (int i = 0; i < M2.length - M1.length; i++) {
            for (int j = 0; j < M2[i].length - M1[i].length; j++) {
                if(belong(M1,M2,i,j) ) re++;
            }
        }
            return re;
        }

    public boolean belong(int[][] M1, int [][] M2,int i, int j) {
        for (int k = 0; k < M1.length; k++) {
            for (int l = 0; l < M1[k].length; l++) {
                if(M1[k][l] != M2[i+k][l+j]) return false;
            }
        }
        return true;
    }
}


class Test {
    private Test() {}
    private static Test test = new Test();
    public Test getInstance() {return test;}
}

class User {

    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}


interface Observer {
    void update(String state);
}

class impObserver implements Observer {
    @Override
    public void update(String state) {
        System.out.println("update: "+state);
    }
}

abstract class Subject {
    private List<Observer> list = new ArrayList<>();

    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    public void notifyObserver(String newState) {
        for (Observer observer : list) {
            observer.update(newState);
        }
    }
}

class impSubject extends Subject {
    public void change(String data) {
        this.notifyObserver(data);
    }
}

class Node {
    private int Data;// 数据
    private Node Next;// 指针

    public Node(int Data) {
        // super();
        this.Data = Data;
    }

    public int getData() {
        return Data;
    }

    public void setData(int Data) {
        this.Data = Data;
    }

    public Node getNext() {
        return Next;
    }

    public void setNext(Node Next) {
        this.Next = Next;
    }

    public static Node Reverse(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node reHead = Reverse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reHead;
    }
}

