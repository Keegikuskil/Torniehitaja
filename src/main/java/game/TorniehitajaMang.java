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
        seaded.setWidth(620);
        seaded.setManualResizeEnabled(true);
        seaded.setPreserveResizeRatio(true);
        seaded.setScaleAffectedOnResize(true);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new AsjadeTehas());
        teeMaa();
        teeKorrus();
    }

    public static void main(String[] args)  {
        launch(args);
    }

    private void teeKorrus()    {
        spawn("KORRUS", 300, 100);
    }

    private void teeMaa()   {
        spawn("MAA", 0, 800 - 50);
    }
}
