package rus.spbstu.labs2020.lab3;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

  static final Object mutex = new Object();
  static int sizeOfQueue = 10;
  static int totalPeople = 100;
  static String[] subjectPool = {"Higher mathematics", "OOP", "Physics"};
  static Student currentStudent = null;

  public static void main(String[] args) {

    ArrayBlockingQueue<Student> queueOfStudents = new ArrayBlockingQueue<>(sizeOfQueue);

    Thread generator = new Thread("Generator") {
      public void run() {
        for (int i = 0; i < totalPeople; i++) {
          Random rand = new Random();
          int numOfLabs = 0;
          switch (rand.nextInt(3)) {
            case 0:
              numOfLabs = 10;
              break;
            case 1:
              numOfLabs = 20;
              break;
            case 2:
              numOfLabs = 100;
          }
          Student student = new Student(numOfLabs, subjectPool[rand.nextInt(3)]);
          try {
            queueOfStudents.put(student);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    generator.start();
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      System.out.println("Can't sleep");
    }

    class Robot extends Thread {
        private String _name;
        public Robot(int i)
        {
            _name = subjectPool[i];
        }
        @Override
        public void run() {
            this.setName("Robot-" + _name);
            while (true) {
                synchronized (mutex) {
                    if ((currentStudent != null) && (currentStudent.getSubjectName().equals(_name))) {
                        System.out.println("Студент с " + currentStudent.getSubjectName() + " подошёл к " + Thread.currentThread().getName());
                        while (currentStudent.isLabsLeft())
                        {
                            currentStudent.work();
                            try {
                                Thread.sleep(100);
                            }
                            catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Студент с " + currentStudent.getSubjectName() + " ушёл от " + Thread.currentThread().getName());
                        currentStudent = null;
                        mutex.notify();
                    }
                    else {
                        try {
                            mutex.wait();
                        }
                        catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            }
        }
    }

    Thread robotHM = new Robot(0);
    Thread robotOOP = new Robot(1);
    Thread robotP = new Robot(2);

    robotHM.start();
    robotOOP.start();
    robotP.start();

    int i = 0;
    while (i < totalPeople) {
      synchronized (mutex) {
        try {
          currentStudent = queueOfStudents.take();
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Студент с " + currentStudent.getSubjectName() + " зашел");
        i++;
        try {
          mutex.notifyAll();
          mutex.wait();
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    robotHM.interrupt();
    robotOOP.interrupt();
    robotP.interrupt();
    synchronized (mutex)
    {
        mutex.notifyAll();
    }
  }
}

