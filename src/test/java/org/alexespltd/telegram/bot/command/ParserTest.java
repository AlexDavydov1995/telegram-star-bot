package org.alexespltd.telegram.bot.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private Parser parser;

    @Before
    public void setParser() {
        parser = new Parser();
    }

    @Test
    public void getParsedCommand_None() {
        String text = "just text";
        ParsedCommand parsedCommandAndText = parser.getParsedCommand(text);
        assertEquals(Command.NONE, parsedCommandAndText.command);
    }

    @Test
    public void getParsedCommand_Fate(){
        String text = "/fate";
        ParsedCommand parsedCommand = parser.getParsedCommand(text);
        assertEquals(Command.FATE, parsedCommand.getCommand());
    }

    @Test
    public void getParsedCommand_Id(){
        String text = "/id";
        ParsedCommand parsedCommand = parser.getParsedCommand(text);
        assertEquals(Command.ID, parsedCommand.getCommand());
    }
}
