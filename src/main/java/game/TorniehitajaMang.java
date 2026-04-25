package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class TorniehitajaMang extends GameApplication {
    private Entity praeguneKorrus;

    @Override
    protected void initSettings(GameSettings seaded) {
        seaded.setTitle("Torniehitaja");
        seaded.setHeight(800);
        seaded.setWidth(620);
        seaded.setManualResizeEnabled(true);
        seaded.setPreserveResizeRatio(true);
        seaded.setScaleAffectedOnResize(true);
        seaded.setVersion("0.1");
        seaded.setDeveloperMenuEnabled(true);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new AsjadeTehas());
        teeMaa();
        teeKorrus();
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("kukuta") {
            @Override
            protected void onActionBegin() {
                if (praeguneKorrus != null) {
                    praeguneKorrus.getComponent(KorruseKomponent.class).kukuta();
                    praeguneKorrus = null;
                    getGameTimer().runOnceAfter(() -> {
                        teeKorrus();
                    },javafx.util.Duration.seconds(0.4));
                }
            }
        }, KeyCode.SPACE);
    }

    public static void main(String[] args)  {
        launch(args);
    }

    private void teeKorrus()    {
        praeguneKorrus = spawn("KORRUS", 300, 100);
    }

    private void teeMaa()   {
        spawn("MAA", 0, 800 - 50);
    }
}
