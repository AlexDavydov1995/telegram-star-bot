package org.alexespltd.telegram;

import org.alexespltd.telegram.bot.Bot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    public static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot starBot = new Bot("HoroscopeAlexEspLtd_bot", "1634768210:AAH-h6aPnEquaDy779NpPnK_kCevaKFWEds");
        starBot.botConnect();
    }
}
