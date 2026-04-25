package game;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class KorruseKomponent extends Component {

    @Override
    public void onAdded() {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        entity.getComponent(PhysicsComponent.class).setOnPhysicsInitialized(() -> {
            füüsika.getBody().setGravityScale(0f);
        });
    }

    public void kukuta()    {
        PhysicsComponent füüsika = entity.getComponent(PhysicsComponent.class);
        füüsika.getBody().setGravityScale(1f);
        füüsika.getBody().setAwake(true);
    }
}
