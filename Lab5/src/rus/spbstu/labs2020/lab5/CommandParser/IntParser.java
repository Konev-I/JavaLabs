package rus.spbstu.labs2020.lab5.CommandParser;

public abstract class IntParser {
  public static boolean tryParseInt(String value) {
    try {
      return Integer.parseInt(value) > 0;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
}
