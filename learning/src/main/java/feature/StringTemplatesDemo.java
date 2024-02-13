package feature;

import static java.lang.StringTemplate.RAW;

import java.util.FormatProcessor;
import java.util.Locale;

public class StringTemplatesDemo {

    public static void main(String[] args) {
        stringFormatJson();
        stringEmbedded();
        Locale thaiLocale = Locale.forLanguageTag("th-TH-u-nu-thai");
        FormatProcessor THAI = FormatProcessor.create(thaiLocale);
        for (int i = 1; i <= 10000; i *= 10) {
            String s = THAI."This answer is %5d\{i}";
            System.out.println(s);
        }
    }

    private static void stringEmbedded() {
        String name = "Joan";
        String info = STR."My name is \{name}";
        System.out.println(info);

        int x = 10, y = 20;
        String s = STR."\{x} + \{y} = \{x + y}";
        System.out.println(s);

        String name1 = "cj_yin";
        StringTemplate st = RAW."My name is \{name1}";
        String info1 = STR.process(st);
        System.out.println(info1);
    }

    private static void stringFormatJson() {
        String name = "Joan Smith";
        String phone = "555-123-4567";
        String address = "1 Maple Drive, Any town";
        String json = """
            {
                "name":    "%s",
                "phone":   "%s",
                "address": "%s"
            }
            """.formatted(name, phone, address);
        System.out.println(json);
    }

}
