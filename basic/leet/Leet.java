import java.util.Objects;

public class Leet {
  public static String toLeet(String normal) {
    Objects.requireNonNull(normal);
    if (normal.isEmpty())
      throw new IllegalArgumentException("normal must be non-null");
    String[][] replacements = { { "elite", "1337" }, { "cool", "k3wl" }, { "!", "!!!11" }, { "ck", "xx" },
        { "ers", "0rz" }, { "er", "0rz" }, { "en", "n" }, { "e", "3" }, { "t", "7" }, { "o", "0" }, { "a", "@" } };
    String leet = normal;
    for (String[] replacementEntry : replacements)
      leet = leet.replaceAll(replacementEntry[0], replacementEntry[1]);
    return leet;
  }

  public static String[] allToLeet(String[] normals) {
    Objects.requireNonNull(normals, "normals must be non-null");
    String[] leets = new String[normals.length];
    for (int index = 0; index < normals.length; ++index)
      leets[index] = toLeet(normals[index]);
    return leets;
  }
}
