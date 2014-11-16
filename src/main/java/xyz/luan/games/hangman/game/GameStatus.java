package xyz.luan.games.hangman.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.game.scenes.DefaultScene;

public interface GameStatus {

    public DefaultScene getNewScene(Main mainRef);
    
    public static DefaultScene getNewScene(Class<? extends DefaultScene> sceneClass, Main mainRef) {
        try {
            DefaultScene scene = sceneClass.newInstance();
            scene.setMain(mainRef);
            return scene;
        } catch (IllegalAccessException | InstantiationException ex) {
            final Logger logger = LoggerFactory.getLogger(GameStatus.class);
            logger.error("Scene subclass must have public default constructor.", ex);
            System.exit(1);
            return null;
        }
    }
}
