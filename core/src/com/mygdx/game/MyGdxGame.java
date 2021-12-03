package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import helpers.GameInfo;
import objects.ShipSquare;
import player.Player;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player;
	private World world;

	private OrthographicCamera box2DCamera;
	private Box2DDebugRenderer debugRenderer;

	@Override
	public void create() {


		box2DCamera = new OrthographicCamera();
		box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);

		box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

		debugRenderer = new Box2DDebugRenderer();
		world = new World(new Vector2(0, -9.81f), true);
		batch = new SpriteBatch();
		img = new Texture("ship.png");

		player = new Player(world, "Grandpa Single.png", GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 + 250);

		ShipSquare square = new ShipSquare(world);


	}

	void update(float dt) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.getBody().applyLinearImpulse(new Vector2(-1, 0), player.getBody().getWorldCenter(), true);


		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.getBody().applyLinearImpulse(new Vector2(1, 0), player.getBody().getWorldCenter(), true);

		}
	}



	@Override
	public void render(float delta) {



		update(delta);


		player.updatePlayer();

		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(player, player.getX()- player.getWidth()/2- 628,player.getY() -player.getHeight()/2 +35);
		batch.end();

		debugRenderer.render(world, box2DCamera.combined);


		world.step(Gdx.graphics.getDeltaTime(),6,2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
