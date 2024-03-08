/****************************************************************************
 * FILE: TxFileParserEntryPoint.java
 * DSCRPT: 
 ****************************************************************************/





package com.devaltus.clients.envpublisher;





import com.devaltus.clients.envpublisher.cfg.AppMainComponent;
import com.devaltus.clients.envpublisher.cfg.AppProps;
import com.devaltus.clients.envpublisher.cfg.DaggerAppMainComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.inject.Inject;
import java.io.FileWriter;
import java.io.IOException;





@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class EntryPoint
{

    //
    // dagger
    //
    final static AppMainComponent _comp;

    static
    {
        _comp = DaggerAppMainComponent.create();
    }

    //
    // my objects of interest
    //
    private final AppProps _props;
    private final String _envKey = "TEST_ENV_KEY";

    //
    // actual entry point
    //
    public static void main(String[] args_)
    {
        try
        {
            EntryPoint ep = _comp.entryPoint();
            Role role = ep.parseArgs(args_, ep);
            if (role == Role.READER)
            {
                ep.doReader();
            }
            else if (role == Role.WRITER)
            {
                ep.doWriter();
            }
            else if (role == Role.CONSOLE)
            {
                ep.doConsole();
            }
        }
        catch (ConfigurationException ex_)
        {
            _logger.error(ex_.toString(), ex_);
        }
        catch (IOException ex_)
        {
            _logger.error(ex_.toString(), ex_);
        }
    }

    private void doConsole()
    {
        System.out.println("export " + _envKey + "=mememem");
    }

    private void doWriter() throws IOException
    {
        _logger.trace("doWriter");
        FileWriter fw = new FileWriter("/tmp/envpublisher.txt");
        fw.write("export " + _envKey + "=\"testvalue\"\n");
        fw.flush();
        fw.close();
    }

    private void doReader()
    {
        _logger.trace("doReader");
        var out = System.getenv().get(_envKey);
        System.out.println(out);
    }


    private Role parseArgs(final String[] args_, final EntryPoint ep_) throws ConfigurationException
    {
        // create Options object
        Options options = new Options();

        // add options
        options.addOption("r", "reader", false, "run a java process that reads from env varaibles");
        options.addOption("w", "writer", false, "run a java process that writes value to the env varaibles");
        options.addOption("c", "console", false, "run a java process that writes value to the console");
        options.addOption("h", "help", false, "print this message");

        CommandLineParser parser = new DefaultParser();
        try
        {
            // parse the command line arguments
            CommandLine cmd = parser.parse(options, args_);

            // get values from the command line
            String option = "r";
            if (cmd.hasOption(option))
            {
                _logger.trace("option: {}", Role.READER);
                return Role.READER;
            }

            option = "w";
            if (cmd.hasOption(option))
            {
                _logger.trace("option: {}", Role.WRITER);
                return Role.WRITER;
            }

            option = "c";
            if (cmd.hasOption(option))
            {
                _logger.trace("option: {}", Role.CONSOLE);
                return Role.CONSOLE;
            }


            if (cmd.hasOption("h"))
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(args_[0], options);
                System.exit(0);
            }

            throw new ConfigurationException("No option specified");
        }
        catch (ParseException e)
        {
            throw new ConfigurationException(e);
        }
    }
}
