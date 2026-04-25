package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class TorniehitajaMang extends GameApplication {

    @Override
    protected void initSettings(GameSettings seaded) {
        seaded.setTitle("Torniehitaja");
        seaded.setHeight(800);
        seaded.setWidth(600);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new AsjadeTehas());

        spawn("KORRUS", 100, 100);
    }

    public static void main(String[] args)  {
        launch(args);
    }
}
