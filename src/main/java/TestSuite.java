import java.util.ArrayList;
import java.util.List;

public class TestSuite {

    private String name;
    private String tests;
    private String skipped;
    private String failures;
    private String errors;
    private String timestamp;
    private String hostname;
    private String time;
    private String positive;

    private List<TestCase> testCases = new ArrayList<>();

    private String systemOut;
    private String systemError;

    public TestSuite() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTests() {
        return tests;
    }

    public int getTestsInt() {
        return Integer.parseInt(getTests());
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getPositive() {
        positive = String.valueOf(getTestsInt() - getFailuresInt() - getErrorsInt() - getSkippedInt());
        return positive;
    }

    public String getSkipped() {
        return skipped;
    }

    public int getSkippedInt() {
        return Integer.parseInt(getSkipped());
    }

    public void setSkipped(String skipped) {
        this.skipped = skipped;
    }

    public String getFailures() {
        return failures;
    }

    public int getFailuresInt() {
        return Integer.parseInt(getFailures());
    }

    public void setFailures(String failures) {
        this.failures = failures;
    }

    public String getErrors() {
        return errors;
    }

    public int getErrorsInt() {
        return Integer.parseInt(getErrors());
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }


    public String getSystemOut() {
        return systemOut;
    }

    public void setSystemOut(String systemout) {
        this.systemOut = systemout;
    }

    public String getSystemError() {
        return systemError;
    }

    public void setSystemError(String systemError) {
        this.systemError = systemError;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "name='" + name + '\'' +
                ", tests='" + tests + '\'' +
                ", skipped='" + skipped + '\'' +
                ", failures='" + failures + '\'' +
                ", errors='" + errors + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", hostname='" + hostname + '\'' +
                ", time='" + time + '\'' +
                ", testCases=" + testCases +
                ", systemOut='" + systemOut + '\'' +
                ", systemError='" + systemError + '\'' +
                '}';
    }
}
