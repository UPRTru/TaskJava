import GroupId.activity.StartActivity;
import GroupId.exception.ErrorFoundFile;
import GroupId.exception.ErrorInPut;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private String testOk = null;
    private final String pathCSV = "src/test/resources/testFile/address.csv"; //путь до файла address.csv
    private final String pathXML = "src/test/resources/testFile/address.xml"; //путь до файла address.xml

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void start() {
        StartActivity.activity.scanner = new Scanner(System.in);
        StartActivity.start();
        String consoleLine = outputStreamCaptor.toString();
        assertEquals(EqualsText.up, consoleLine, "Test Activity (start) - error");
        testOk = "Test Activity (start) - OK";
    }

    @Test
    void errorInput() {
        assertThrows(ErrorInPut.class, () -> inPutSystem("error"), "Test Activity (errorInput String) - error");
        assertThrows(ErrorInPut.class, () -> inPutSystem("9"), "Test Activity (errorInput Int) - error");
        testOk = "Test Activity (errorInput) - OK";
    }

    @Test
    void errorFoundFile() {
        assertThrows(ErrorFoundFile.class, () -> inPutSystem("C:\\ErrorRead\\address.csv"),
                "Test Activity (errorFoundFile .csv) - error");
        assertThrows(ErrorFoundFile.class, () -> inPutSystem("C:\\ErrorRead\\address.xml"),
                "Test Activity (errorFoundFile .xml) - error");
        testOk = "Test Activity (errorFoundFile) - OK";
    }

    @Test
    void outPutCSV() {
        assertDoesNotThrow(() -> inPutSystem(pathCSV), "Test Activity (outPutCSV) - problem with the file");
        String consoleLineCSV = outputStreamCaptor.toString();
        assertEquals(EqualsText.txt("csv"), consoleLineCSV, "Test Activity (outPutCSV) - error");
        testOk = "Test Activity (outPutCSV) - OK";
    }

    @Test
    void outPutXML() {
        assertDoesNotThrow(() -> inPutSystem(pathXML), "Test Activity (outPutXML) - problem with the file");
        String consoleLineCSV = outputStreamCaptor.toString();
        assertEquals(EqualsText.txt("xml"), consoleLineCSV, "Test Activity (outPutXML) - error");
        testOk = "Test Activity (outPutXML) - OK";
    }

    public void inPutSystem(String in) {
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        StartActivity.activity.scanner = new Scanner(System.in);
        System.setIn(System.in);
        StartActivity.start();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        if (testOk != null) {
            System.out.println(testOk);
            testOk = null;
        }
    }
}