package game;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class KorruseKomponent extends Component {
    private double kiirus = 2;
    private double nurk = 0;
    private double raadiusX = 235;
    private double raadiusY = 175;

    @Override
    public void onAdded() {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        entity.getComponent(PhysicsComponent.class).setOnPhysicsInitialized(() -> {
            füüsika.getBody().setGravityScale(0f);
        });
    }

    public void onUpdate(double tpf) {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        double xkiirus = -raadiusX * kiirus * Math.sin(nurk);
        if (füüsika.getBody().getGravityScale() == 0f) {

            nurk += tpf * kiirus;

            xkiirus = -raadiusX * kiirus * Math.sin(nurk);
            double ykiirus = raadiusY * kiirus * Math.cos(nurk);

            füüsika.setLinearVelocity(xkiirus, ykiirus);

        } else {
            füüsika.setLinearVelocity(0, füüsika.getVelocityY());
        }
    }



    public void kukuta()    {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        füüsika.getBody().setGravityScale(1f);
        füüsika.getBody().setAwake(true);
    }
}
