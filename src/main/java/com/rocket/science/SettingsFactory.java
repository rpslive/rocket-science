package com.rocket.science;

import org.apache.commons.cli.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.apache.commons.cli.OptionBuilder.withLongOpt;

/**
 * Created by rpslive on 05/09/15.
 */
public class SettingsFactory {

    public Settings create(String[] args){
        Options options = new Options();
        options.addOption(new Option("help","Print this message"));
        options.addOption(withLongOpt("port").hasArg().
                withDescription("The port on which to start the HTTP server. Defaults to 8080.").create("p"));

        CommandLine commandLine;
        try {
            commandLine = new BasicParser().parse(options,args);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        if(commandLine.hasOption("help")){
            new HelpFormatter().printHelp("MyWebService",options);
            return null;
        }

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/service-context.xml");
        Settings settings = (Settings) context.getBean("settings");
        settings.setPort(Integer.parseInt(commandLine.getOptionValue("port","8080")));
        return settings;
    }
}
