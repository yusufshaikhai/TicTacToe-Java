package factories;

import models.DifficultyLevel;
import strategy.BotPlayingStrategy;
import strategy.EasyBotPlayingStrategy;
import strategy.HardBotPlayingStrategy;
import strategy.MediumPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(DifficultyLevel difficultyLevel) {
        switch (difficultyLevel){
            case HARD -> {
                return new HardBotPlayingStrategy();
            }
            case MEDIUM -> {
                return new MediumPlayingStrategy();
            } default -> {
                return new EasyBotPlayingStrategy();
            }
        }
    }
}
