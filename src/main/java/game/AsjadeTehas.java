package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AsjadeTehas implements EntityFactory {

    @Spawns("KORRUS")
    @SuppressWarnings("unused")
    public Entity uusKorrus(SpawnData info) {
        PhysicsComponent füüsika = new PhysicsComponent();
        füüsika.setBodyType(BodyType.DYNAMIC);
        return FXGL.entityBuilder(info)
                .type(EntityType.KORRUS)
                .viewWithBBox(new Rectangle(40, 40, Color.BLUE))
                .with(füüsika)
                .with(new KorruseKomponent())
                .collidable()
                .build();
    }

    @Spawns("MAA")
    @SuppressWarnings("unused")
    public Entity uusMaa(SpawnData info)    {
        PhysicsComponent füüsika = new PhysicsComponent();
        füüsika.setBodyType(BodyType.STATIC);
        return FXGL.entityBuilder(info)
                .type(EntityType.MAA)
                .viewWithBBox(new Rectangle(620, 50, Color.DARKRED))
                .with(füüsika)
                .collidable()
                .build();
    }

}
