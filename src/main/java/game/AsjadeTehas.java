package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AsjadeTehas implements EntityFactory {

    @Spawns("KORRUS")
    @SuppressWarnings("unused")
    public Entity uusKorrus(SpawnData info) {
        PhysicsComponent füüsika = new PhysicsComponent();
        füüsika.setBodyType(BodyType.DYNAMIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(1.3f);
        fd.setFriction(0.1f);
        füüsika.setFixtureDef(fd);
        return FXGL.entityBuilder(info)
                .type(EntityType.KORRUS)
                .viewWithBBox(new Rectangle(150, 150, Color.BLUE))
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
