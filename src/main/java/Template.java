public class Template {

    public static String HEADER_HTML = "<!DOCTYPE html>\n" +
            "<!--[if lt IE 7]>      <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\"> <![endif]-->\n" +
            "<!--[if IE 7]>         <html class=\"no-js lt-ie9 lt-ie8\"> <![endif]-->\n" +
            "<!--[if IE 8]>         <html class=\"no-js lt-ie9\"> <![endif]-->\n" +
            "<!--[if gt IE 8]><!--> <html class=\"no-js\"> <!--<![endif]-->\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <title>Raport z wykonania testów automatycznych</title>\n" +
            "    <meta name=\"description\" content=\"\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "</head>\n" +
            "<body bgcolor=\"#f0f0f0\">\n" +
            "<!--[if lt IE 7]>\n" +
            "<p class=\"browsehappy\">You are using an <strong>outdated</strong> browser. Please <a href=\"#\">upgrade your browser</a> to improve your experience.</p>\n" +
            "<![endif]-->\n" +
            "\n" +
            "<table cellspacing=\"1\" cellpadding=\"2\" border=\"0\" width=\"100%\">\n";

    public static String RAPORT_HEADER = "    <tr align=\"left\"><td align=\"center\">Raport z wykonania testów automatycznych: <b>%s</b><hr></td></tr>\n";

    public static String RAPORT_AGREGATES = "    <tr align=\"left\"><td>Ilość testów: <b>%s</b>, <font color=\"green\">Testy pozytywne: <b>%s</b></font>, <font color=\"red\">Testy negatywne: <b>%s</b></font>, Testy pominięte: <b>%s</b>, Testy błędne: <b>%s</b><hr></td></tr>\n";

    public static String RAPORT_CLASS_SUITE = "   <tr align=\"left\"><td>Klasa: <b>%s</b>, Godzina: <b>%s</b>, Host: <b>%s</b>, Czas wykonania: <b>%s</b></td></tr>\n";

    public static String RAPORT_TEST_HEADER = "    <tr align=\"left\">\n" +
            "        <td>\n" +
            "            <table cellspacing=\"1\" cellpadding=\"2\" border=\"0\" width=\"100%\" >\n" +
            "                <tr bgcolor=\"#A9A9A9\">\n" +
            "                    <td>Test</td>\n" +
            "                    <td>Czas</td>\n" +
            "                    <td>Typ</td>\n" +
            "                    <td>Błąd</td>\n" +
            "                </tr>\n" +
            "                <tr bgcolor=\"#cecccc\">\n";

    public static String RAPORT_TESTS = "                  <td>%s</td>\n" +
            "                    <td>%s</td>\n" +
            "                    <td>%s</td>\n" +
            "                    <td>%s</td>\n";

    public static String FOOTER_HTML = "               </tr>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "\n" +
            "<!--<script src=\"\" async defer></script>-->\n" +
            "</body>\n" +
            "</html>\n";

}
