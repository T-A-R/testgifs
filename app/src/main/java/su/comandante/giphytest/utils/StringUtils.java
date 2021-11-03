package su.comandante.giphytest.utils;

public class StringUtils {

    public static boolean isNotNullOrEmpty(String string) {
        return !(string == null || string.length() == 0);
    }
}
