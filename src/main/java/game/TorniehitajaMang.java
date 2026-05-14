package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static game.EntityType.KORRUS;
import static game.EntityType.MAA;

public class TorniehitajaMang extends GameApplication {
    private Entity praeguneKorrus;
    private int korrusteArv = 0;
    private double targetKaameraY = 0;
    private int skoor = 0;
    private Entity eelmineKorrus;
    private boolean mängLäbi = false;

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
        getGameScene().getViewport().setY(0);
        getGameScene().getViewport().setLazy(true);

        //getGameScene().getViewport().setX(0);
        teeMaa();
        teeKorrus();
    }

    @Override
    protected void initPhysics() {
        //Entity kukkuv = praeguneKorrus;
        //Entity paigal = eelmineKorrus;
        onCollisionBegin(KORRUS, KORRUS, (a, b) -> {
            if (!(mängLäbi)) {
                Entity kukkuv = (a == praeguneKorrus) ? a : b;
                PhysicsComponent physics = kukkuv.getComponent(PhysicsComponent.class);
                physics.setVelocityX(0);
                physics.setVelocityY(0);
                physics.setLinearVelocity(0,0);
                physics.setAngularVelocity(0);
                physics.setBodyType(BodyType.STATIC);

                //Entity paigal = (a == eelmineKorrus) ? a : b;
                //paigal.getComponent(CollidableComponent.class).setValue(false);
                teeKorrus();
                return null;
            }
            else {
                return null;
            }
        });

        if (korrusteArv == 1) {
            onCollisionBegin(KORRUS, MAA, (a, b) -> {
                teeKorrus();
                return null;
            });
        }
        else if (korrusteArv>1) {
            onCollisionBegin(KORRUS, MAA, (kukkuv, b) -> {
                mängLäbi = true;
                //getInput().setProcessInput(false);
                return null;
            });
        }
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("kukuta") {
            @Override
            protected void onActionBegin() {
                if (praeguneKorrus != null) {
                    praeguneKorrus.getComponent(KorruseKomponent.class).kukuta();
                    praeguneKorrus = null;
                    /*AtomicBoolean kokkupõrge = new AtomicBoolean(false);
                    //if collision start on, kuid pole collisionEnds(), ss tuleb uus kast
                    onCollisionBegin(KORRUS, KORRUS, (praeguneK, eelmineK) -> {
                        kokkupõrge.set(true);
                        return null;
                    });
                    onCollisionBegin(KORRUS, MAA, (praeguneK, maa) -> {
                        kokkupõrge.set(true);
                        return null;
                    });
                    if (kokkupõrge.get()) {
                        boolean kukkusMööda = false;
                        onCollisionEnd(KORRUS, KORRUS, (praeguneKorrus, eelmineKorrus) -> {
                            Text lõpusõnum = new Text("Mäng läbi!");
                            lõpusõnum.setTranslateX(300);
                            lõpusõnum.setTranslateY(200);
                            getGameScene().addUINode(lõpusõnum);
                            return null;
                        });
                        getGameTimer().runOnceAfter(() -> {
                            if (!(kukkusMööda)) {
                                teeKorrus();
                            }
                        },javafx.util.Duration.seconds(0.8));
                    };*/
                }
            }
        }, KeyCode.SPACE);
    }

    public static void main(String[] args)  {
        launch(args);
    }

    private void teeKorrus()    {
        // praeguneKorrus = spawn("KORRUS", 235, 100);
        //TODO: KAAMERA võiks liikuda kaasa alates mingist punktist


        int maaY = 750;
        int korruseKõrgus = 50;
        double korruseY = 50;
        // double targetKaameraY = 0;
        double kaameraY = 0;
        int vahe = 550;
        eelmineKorrus = praeguneKorrus;
        if (korrusteArv < 3) {
            korruseY = 50;
            praeguneKorrus = spawn("KORRUS", 235, korruseY);
            targetKaameraY = 0;
            // getGameScene().getViewport().setY(kaameraY);
        }
        // int korruseY = maaY - korruseKõrgus - (korrusteArv * korruseKõrgus);

        // praeguneKorrus = spawn("KORRUS", 235, korruseY);

        // double kaameraY = korruseY;
        // korrusteArv++;

        if (korrusteArv >= 3) {
            korruseY = maaY - korruseKõrgus - (korrusteArv * korruseKõrgus) - vahe;
            praeguneKorrus = spawn("KORRUS", 235, korruseY);
            double vaheY = kaameraY - korruseY + 50;
            targetKaameraY = korruseY - 50;
            // getGameScene().getViewport().setY(kaameraY);
            // getGameScene().getViewport().setY(kaameraY);
        }
        korrusteArv++;
    }

    @Override
    protected void onUpdate(double tpf) {

        double currentY = getGameScene().getViewport().getY();

        // only moves if needed
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
