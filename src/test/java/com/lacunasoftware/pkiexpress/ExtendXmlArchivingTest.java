package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ExtendXmlArchivingTest {

    private ExtendXmlArchivingSpy operator;
    private TimestampAuthority tsa;

    @Before
    public void setUp() throws IOException {
        // Create a spy to intercept the call to the "invoke" method.
        System.out.println("Setting up test...");
        PkiExpressConfig config = new PkiExpressConfig("D:\\Projetos\\pkiexpress\\PkiExpress\\bin\\x64\\Publish\\net462\\pkie.exe");
        operator = new ExtendXmlArchivingSpy(config);
        operator.setInputFile("C:\\Users\\danil\\Downloads\\349573a8-1480-4d4a-8918-9fa6fe3ad324.xml");
        operator.setValidationPolicy("pki-brazil");

        // Create a TimestampAuthority and set it.
        tsa = new TimestampAuthority("http://tsa.lacunasoftware.com/timestamper");
        operator.setTimestampAuthority(tsa);
    }

    @Test
    public void extendArchiving_callsInvokeWithCorrectCommand() throws IOException {
        // Call the method to be tested.
        operator.extendArchiving();

        // Assert that the `invoke` method was called with the correct command.
        assertEquals(CommandEnum.CommandExtendXmlArchiving, operator.getInvokedCommand());
    }

    @Test
    public void extendArchiving_withAllParameters_buildsCorrectArguments() throws IOException {
        // Setup TSA
        // tsa.setOAuthTokenAuthentication("test-token");
        operator.setOutputFile("output.xml");
        operator.setOverwrite(true);

        // Expected arguments
        List<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("C:\\Users\\danil\\Downloads\\349573a8-1480-4d4a-8918-9fa6fe3ad324.xml");
        expectedArgs.add("output.xml");
        expectedArgs.add("--policy");
        expectedArgs.add("pki-brazil");
        expectedArgs.add("--overwrite");
        expectedArgs.addAll(tsa.getCmdArguments());

        // Call the method
        operator.extendArchiving();

        // Assert arguments
        assertEquals(expectedArgs, operator.getInvokedArgs());
    }

    @Test
    public void extendArchiving_withoutValidationPolicy_buildsCorrectArguments() throws IOException {
        // remove validation policy
        operator.setValidationPolicy(null);

        // Expected arguments
        List<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("C:\\Users\\danil\\Downloads\\349573a8-1480-4d4a-8918-9fa6fe3ad324.xml");
        expectedArgs.addAll(tsa.getCmdArguments());

        // Call the method
        operator.extendArchiving();

        // Assert arguments
        assertEquals(expectedArgs, operator.getInvokedArgs());
    }

    @Test
    public void extendArchiving_withoutTSA_buildsCorrectArguments() throws IOException {
        // remove TSA
        operator.setTimestampAuthority(null);

        // Expected arguments
        List<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("C:\\Users\\danil\\Downloads\\349573a8-1480-4d4a-8918-9fa6fe3ad324.xml");
        expectedArgs.add("--policy");
        expectedArgs.add("pki-brazil");

        // Call the method
        operator.extendArchiving();

        // Assert arguments
        assertEquals(expectedArgs, operator.getInvokedArgs());
    }

    // A "spy" class to intercept the call to the "invoke" method for testing purposes.
    private static class ExtendXmlArchivingSpy extends ExtendXmlArchiving {
        private CommandEnum invokedCommand;
        private List<String> invokedArgs;

        public ExtendXmlArchivingSpy(PkiExpressConfig config) {
            super(config);
        }

        public CommandEnum getInvokedCommand() {
            return invokedCommand;
        }

        public List<String> getInvokedArgs() {
            return invokedArgs;
        }
    }
} 