package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class ShipSquare extends Sprite {

    private World world;
    private Body body;

    public ShipSquare(World world){
    super(new Texture("ShipSquare.png"));
    this.world = world;
    setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f - 130);
    createBody();
}

void createBody(){
    BodyDef bodyDef = new BodyDef();


    bodyDef.type = BodyDef.BodyType.StaticBody;

    bodyDef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

    body = world.createBody(bodyDef);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getWidth()/GameInfo.PPM, (getHeight()/2)/GameInfo.PPM);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 1f;


    Fixture fixture = body.createFixture(fixtureDef);

    shape.dispose();
}




}
