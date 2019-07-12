import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestXParser {

    static final String TESTSUITE = "testsuite";
    static final String NAME = "name";
    static final String TESTS = "tests";
    static final String SKIPPED = "skipped";
    static final String FAILURES = "failures";
    static final String ERRORS = "errors";
    static final String TIMESTAMP = "timestamp";
    static final String HOSTNAME = "hostname";
    static final String TIME = "time";
    static final String TESTCASE = "testcase";
    static final String CLASSNAME = "classname";
    static final String SYSTEMOUT = "system-out";
    static final String SYSTEMERR = "system-err";

    public TestSuite readTestSuite(String file) {

        TestSuite testSuite = null;
        TestCase testCase = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();

        try {

            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(file));

            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()) {

                    StartElement startElement = event.asStartElement();

                    if(startElement.getName().getLocalPart().equals(TESTSUITE)) {

                          testSuite = new TestSuite();

                          Iterator<Attribute> attributes = startElement.getAttributes();

                            while (attributes.hasNext()) {

                                Attribute attribute = attributes.next();

                                if (attribute.getName().toString().equals(NAME)) {
                                    testSuite.setName(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(TESTS)) {
                                    testSuite.setTests(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(SKIPPED)) {
                                    testSuite.setSkipped(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(FAILURES)) {
                                    testSuite.setFailures(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(ERRORS)) {
                                    testSuite.setErrors(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(TIMESTAMP)) {
                                    testSuite.setTimestamp(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(HOSTNAME)) {
                                    testSuite.setHostname(attribute.getValue());
                                }

                                if (attribute.getName().toString().equals(TIME)) {
                                    testSuite.setTime(attribute.getValue());
                                }

                            }
                        }

                    if(startElement.getName().getLocalPart().equals(TESTCASE)) {

                        Iterator<Attribute> attributes = startElement.getAttributes();
                        testCase = new TestCase();

                        while (attributes.hasNext()) {

                            Attribute attribute = attributes.next();

                            if (attribute.getName().toString().equals(NAME)) {
                                //System.out.println(attribute.getValue());
                                testCase.setName(attribute.getValue());

                            }

                            if (attribute.getName().toString().equals(CLASSNAME)) {
                                //System.out.println(attribute.getValue());
                                testCase.setClassname(attribute.getValue());
                            }

                            if (attribute.getName().toString().equals(TIME)) {
                                //System.out.println(attribute.getValue());
                                testCase.setTime(attribute.getValue());
                            }

                        }

                        testSuite.getTestCases().add(testCase);

                    }

                    if(startElement.getName().getLocalPart().equals(SYSTEMOUT)) {
                        event = eventReader.nextEvent();
                        testSuite.setSystemOut(event.asCharacters().getData());
                    }

                    if(startElement.getName().getLocalPart().equals(SYSTEMERR)) {
                        event = eventReader.nextEvent();
                        testSuite.setSystemError(event.asCharacters().getData());
                    }

                    if (event.isEndElement()) {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals(TESTSUITE)) {
                            return testSuite;
                        }
                    }
                }



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return testSuite;
    }

}
