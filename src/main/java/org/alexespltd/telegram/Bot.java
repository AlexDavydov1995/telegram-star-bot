package org.alexespltd.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Bot  extends TelegramLongPollingBot {

    private static final Logger log = Logger.getLogger(Bot.class);

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
        log.debug("Update received");
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
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
