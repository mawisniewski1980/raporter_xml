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

    public String getPathRead() {
        return pathRead;
    }

    public String getPathWrite() {
        return pathWrite;
    }

    public List<String> getFiles() {
        return files;
    }

    public static void main(String[] args) throws JAXBException  {

        Raport raport = new Raport();
        TestXParser parser = new TestXParser();

        raport.initArguments(args);
        raport.listAllFiles(raport.getPathRead(),".xml" );
        raport.getFiles().forEach(file -> {
             testSuites.add(parser.readTestSuite(raport.getPathRead() + "\\" + file));
        });

        System.out.println(testSuites);
    }
}
