package rus.spbstu.labs2020.lab3;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {
  private AtomicInteger _labsCount;
  private String _subjectName;
  public Student(int labsCount, String subjectName)
  {
    _labsCount = new AtomicInteger(labsCount);
    _subjectName = subjectName;
  }

  public String getSubjectName()
  {
    return _subjectName;
  }

  public boolean isLabsLeft()
  {
    return _labsCount.get() > 0;
  }

  public void work()
  {
    _labsCount.addAndGet(-5);
  }

}
