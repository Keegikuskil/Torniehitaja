package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;


import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class TorniehitajaMang extends GameApplication {
    private Entity praeguneKorrus;
    private int korrusteArv = 0;
    private double targetKaameraY = 0;
    private int skoor = 0;
    private Entity eelmineKorrus;

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
        getGameScene().getViewport().setY(-300);
        getGameScene().getViewport().setLazy(true);

        //getGameScene().getViewport().setX(0);
        teeMaa();
        teeKorrus();
    }

    @Override
    protected void initInput() {
        getInput().addAction(teeKukutamiseNupp("kukuta_space"), KeyCode.SPACE);
        getInput().addAction(teeKukutamiseNupp("kukuta_s"), KeyCode.S);
        getInput().addAction(teeKukutamiseNupp("kukuta_alla"), KeyCode.DOWN);
        getInput().addAction(teeKukutamiseNupp("kukuta_hiir"), MouseButton.PRIMARY);
    }

    private UserAction teeKukutamiseNupp(String nimi) {
        return new UserAction(nimi) {
            protected void onActionBegin() {
                if (praeguneKorrus != null) {
                    praeguneKorrus.getComponent(KorruseKomponent.class).kukuta();
                    praeguneKorrus = null;
                    getGameTimer().runOnceAfter(() -> {
                        teeKorrus();
                    },javafx.util.Duration.seconds(0.8));
                }
            }
        };
        }

    public static void main(String[] args)  {
        launch(args);
    }

    private void teeKorrus()    {

        //Random rdm = new Random();
        //int juhuarv = rdm.nextInt(0, 450);

        int maaY = 750;
        int korruseKõrgus = 150;
        double korruseY = 50;
        int vahe = 250;
        eelmineKorrus = praeguneKorrus;
        if (korrusteArv < 3) {
            praeguneKorrus = spawn("KORRUS", 470, korruseY);
            targetKaameraY = 0;
        }

        if (korrusteArv >= 3) {
            korruseY = maaY - korruseKõrgus - (korrusteArv * korruseKõrgus) - vahe;
            praeguneKorrus = spawn("KORRUS", 470, korruseY);
            targetKaameraY = korruseY - 50;
        }
        korrusteArv++;
    }

    @Override
    protected void onUpdate(double tpf) {

        double currentY = getGameScene().getViewport().getY();
        double diff = targetKaameraY - currentY;

        if (Math.abs(diff) > 1) {
            double smoothY = currentY + diff * 0.1;
            getGameScene().getViewport().setY(smoothY);
        }
    }

    private void teeMaa()   {
        spawn("MAA", 0, 800 - 50);
    }

    private void endGame() {
        System.out.println("GAME OVER");
    }
}
