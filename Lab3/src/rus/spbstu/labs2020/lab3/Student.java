package rus.spbstu.labs2020.lab3;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {
  private AtomicInteger _labsCount;
  private String _subjectName;
  private String _studentName;
  public Student(int labsCount, String subjectName, String studentName)
  {
    _labsCount = new AtomicInteger(labsCount);
    _subjectName = subjectName;
    _studentName = studentName;
  }

  public String getSubjectName()
  {
    return _subjectName;
  }

  public String getStudentName()
  {
    return _studentName;
  }

  public int getLabsCount()
  {
    return _labsCount.get();
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
