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

    public int getTestsSum() {
        return testsSum;
    }

    public int getSkippedSum() {
        return skippedSum;
    }

    public int getFailuresSum() {
        return failuresSum;
    }

    public int getErrorsSum() {
        return errorsSum;
    }

    public int getPositiveTestSum() {
        return positiveTestSum;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public static void main(String[] args)  {

        Raport raport = new Raport();
        try {
            raport.initArguments(args);
            raport.prepareFiles(raport.getPathRead());
            raport.agregateData(testSuites);
            raport.writeHtmlRaport(raport.createHtmlRaport());

            System.out.println(raport.getTestsSum());
            System.out.println(raport.getPositiveTestSum());
            System.out.println(raport.getSkippedSum());
            System.out.println(raport.getFailuresSum());
            System.out.println(raport.getErrorsSum());

            System.out.println(raport.getTestSuites());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
