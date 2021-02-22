package org.alexespltd.telegram.bot.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {
    private static final Logger log = LogManager.getLogger(Parser.class);
    private static final String PREFIX = "/";

    public ParsedCommand getParsedCommand(String text){
        if(isCommand(text)) {
            String upperCaseText = text.toUpperCase().substring(1);
            Command command = Command.NONE;
            try {
                command = Command.valueOf(upperCaseText);
            } catch (IllegalArgumentException e) {
                log.debug("Can't parse command: " + text);
            }
            return new ParsedCommand(command);
        } else {
            return new ParsedCommand(Command.NONE);
        }
    }

    private boolean isCommand(String text) {
        return text.startsWith(PREFIX);
    }
}
