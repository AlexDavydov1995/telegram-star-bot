package org.alexespltd.telegram.bot;

import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Bot  extends TelegramLongPollingBot {

    private static final Logger log = LogManager.getLogger(Bot.class);

    final int pause = 10000;

    @Setter
    @Getter
    String userName;

    @Setter
    @Getter
    String token;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Receive new Update. updateID: " + update.getUpdateId());

        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();

        if (inputText.startsWith("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Hello. I am a horoscope bot.\nWould you like to know your fate?");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void botConnect() {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(this);
            log.info("registered");
        } catch (TelegramApiRequestException e) {
            log.error("Cant Connect. Pause " + pause / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e1){
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}
