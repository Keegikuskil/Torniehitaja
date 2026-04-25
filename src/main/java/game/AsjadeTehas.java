package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AsjadeTehas implements EntityFactory {

    @Spawns("KORRUS")
    public Entity uusKorrus(SpawnData info) {
        return FXGL.entityBuilder(info)
                .type(EntityType.KORRUS)
                .viewWithBBox(new Rectangle(40, 40, Color.BROWN))
                .collidable()
                .build();
    }

}
