import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Raport {

    private String pathRead;
    private String pathWrite;
    private List<String> files = new ArrayList<>();
    private static List<TestSuite> testSuites = new ArrayList<>();
    private static String EMAIL_FILE_RAPORT = "email.html";

    private int testsSum;
    private int skippedSum;
    private int failuresSum;
    private int errorsSum;
    private int positiveTestSum;

    public Raport() {
    }

    private void initArguments(String[] args){
        try {
            pathRead = args[0];
            pathWrite = this.pathRead.substring(0, this.pathRead.lastIndexOf("\\")) + "\\" + "raport";
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {

        }
    }

    private void listAllFiles(final String pathRead, final String fileExtension) {

        try (Stream<Path> paths = Files.walk(Paths.get(pathRead), 1)) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath) && filePath.toString().endsWith(fileExtension)) {
                    try {
                        files.add(filePath.getFileName().toString());
                    } catch (Exception e) {
                        System.out.println("Brak plików do raportu.\n\n" + e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Brak plików do raportu.");
        }
        Collections.sort(files);
    }

    private void prepareFiles(final String pathRead) {
        TestXParser parser = new TestXParser();
        listAllFiles(pathRead, ".xml");
        getFiles().forEach(file -> {
            testSuites.add(parser.readTestSuite(getPathRead() + "\\" + file));
        });
    }

    private void agregateData(List<TestSuite> testSuites) {

        testsSum = testSuites.stream().mapToInt(test -> Integer.parseInt(test.getTests())).sum();
        skippedSum = testSuites.stream().mapToInt(skip -> Integer.parseInt(skip.getSkipped())).sum();
        failuresSum = testSuites.stream().mapToInt(fail -> Integer.parseInt(fail.getFailures())).sum();
        errorsSum = testSuites.stream().mapToInt(err -> Integer.parseInt(err.getErrors())).sum();
        positiveTestSum = testsSum-failuresSum-errorsSum-skippedSum;
    }

    private String createHtmlRaport() {

        StringBuffer stringHtmlRaport = new StringBuffer();
        stringHtmlRaport.append(Template.HEADER_HTML);

        stringHtmlRaport.append(String.format(Template.RAPORT_HEADER, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        stringHtmlRaport.append(String.format(Template.RAPORT_AGREGATES, testsSum, positiveTestSum, failuresSum, skippedSum, errorsSum));

        for(int i=0; i < testSuites.size(); i++) {

            if(Integer.parseInt(testSuites.get(i).getFailures()) > 0) {


                stringHtmlRaport.append(String.format(Template.RAPORT_CLASS_SUITE, testSuites.get(i).getName(), testSuites.get(i).getTimestamp(), testSuites.get(i).getHostname(), testSuites.get(i).getTime()));
                stringHtmlRaport.append(String.format(Template.RAPORT_CLASS_AGREGATES, testSuites.get(i).getTests(), testSuites.get(i).getPositive(), testSuites.get(i).getFailures(), testSuites.get(i).getSkipped(), testSuites.get(i).getErrors() ));

                stringHtmlRaport.append(Template.RAPORT_TEST_HEADER);

                for(int j = 0; j < testSuites.get(i).getTestCases().size(); j++) {

                    if(testSuites.get(i).getTestCases().get(j).getMessage() != null) {

                        stringHtmlRaport.append(String.format(Template.RAPORT_TESTS,testSuites.get(i).getTestCases().get(j).getName(),
                                testSuites.get(i).getTestCases().get(j).getTime(),
                                testSuites.get(i).getTestCases().get(j).getType(),
                                testSuites.get(i).getTestCases().get(j).getMessage()));
                    }

                }

                stringHtmlRaport.append(Template.RAPORT_TEST_FOOTER);

            }

        }

        stringHtmlRaport.append(Template.FOOTER_HTML);

        return stringHtmlRaport.toString();
    }

    private void writeHtmlRaport(String raport) throws IOException {

        if (raport != null && !raport.isEmpty()) {
            Path path = Paths.get(pathWrite);
            Files.createDirectories(path);
            path = Paths.get(this.getPathWrite() + "\\" + EMAIL_FILE_RAPORT);
            Files.deleteIfExists(path);
            //Files.write(path, raport.getBytes(StandardCharsets.UTF_8));
            Files.write(path, raport.getBytes());
        } else {
            throw new IOException("Brak danych do raportu");
        }
    }

    public String getPathRead() {
        return pathRead;
    }

    public String getPathWrite() {
        return pathWrite;
    }

    public List<String> getFiles() {
        return files;
    }

    public static void main(String[] args)  {

        Raport raport = new Raport();
        try {

            raport.initArguments(args);
            raport.prepareFiles(raport.getPathRead());
            raport.agregateData(testSuites);
            raport.writeHtmlRaport(raport.createHtmlRaport());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
