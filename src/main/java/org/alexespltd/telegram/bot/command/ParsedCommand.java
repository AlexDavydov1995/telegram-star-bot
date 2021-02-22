package org.alexespltd.telegram.bot.command;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ParsedCommand {
    Command command = Command.NONE;
}
