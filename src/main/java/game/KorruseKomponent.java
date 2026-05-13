package game;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class KorruseKomponent extends Component {
    private double kiirus = 300;
    private int suund = 1;

    @Override
    public void onAdded() {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        entity.getComponent(PhysicsComponent.class).setOnPhysicsInitialized(() -> {
            füüsika.getBody().setGravityScale(0f);
        });
    }

    public void onUpdate(double tpf) {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        if (füüsika.getBody().getGravityScale() == 0f) {

            füüsika.setLinearVelocity(kiirus * suund, 0);
            if (entity.getX() >= 620 - 150) {
                suund = -1;
            } else if (entity.getX() <= 0){
                suund = 1;
            }

        } else {
            füüsika.setLinearVelocity(0, füüsika.getVelocityY()); //TODO: Võiks säilitada X kiiruse
        }
    }



    public void kukuta()    {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        füüsika.getBody().setGravityScale(2f);
        füüsika.getBody().setAwake(true);
    }
}
