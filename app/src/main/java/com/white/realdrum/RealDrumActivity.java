package com.white.realdrum;

import android.content.Intent;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.realdrum.R;

import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.audio.sound.SoundManager;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.LayoutGameActivity;

import java.io.IOException;

public class RealDrumActivity extends LayoutGameActivity implements Base {

	// Khai bao lop chua ten cac hinh se hien thi
	protected TextureRegions textureRegions;
	// Khai bao lop tao Sprite de load hinh
	private Objetos objetosSolo;
	private DisplayMetrics metrics;
	private Camera camera;
	private Vibrator vibrator;
	private Sons sons;
	private Scene scene;
	private RecordManager recordManager;
	protected boolean isPrimeiraVezCarregado = true;
	private ProgressBar loadingProgressBar;
	private Scene splashScene;
	private TextureRegion splashTextureRegion;
	private BitmapTextureAtlas splashBitmapTextureAtlas;

	// Get layout main.xml
	@Override
	protected int getLayoutID() {
		return R.layout.main;
	}

	// Lay id trong main.xml (org.andengine.opengl.view.RenderSurfaceView)
	@Override
	protected int getRenderSurfaceViewID() {
		return R.id.xmllayoutRenderSurfaceView;
	}

	@Override
	public Engine onLoadEngine() {
		this.metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
		this.camera = new Camera(0.0F, 0.0F, this.metrics.widthPixels,
				this.metrics.heightPixels);
		EngineOptions localEngineOptions = new EngineOptions(true,
				EngineOptions.ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(this.camera.getWidth(),
						this.camera.getHeight()), this.camera)
				.setNeedsSound(true);
		localEngineOptions.setUpdateThreadPriority(-19);
		Engine localEngine = new Engine(localEngineOptions);
		try {
			if (MultiTouch.isSupported(this))
				localEngine.setTouchController(new MultiTouchController());
			return localEngine;
		} catch (MultiTouchException localMultiTouchException) {
		}
		return localEngine;
	}

	/*
	 * @Override public EngineOptions onCreateEngineOptions() {
	 * 
	 * metrics = new DisplayMetrics();
	 * getWindowManager().getDefaultDisplay().getMetrics(metrics); camera = new
	 * Camera(0, 0, metrics.widthPixels, metrics.heightPixels);
	 * 
	 * final EngineOptions engineOptions = new EngineOptions(true,
	 * ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
	 * camera.getWidth(), camera.getHeight()), camera);
	 * engineOptions.getAudioOptions().setNeedsSound(true);
	 * engineOptions.setUpdateThreadPriority(-19);
	 * engineOptions.getTouchOptions().setNeedsMultiTouch(true); return
	 * engineOptions; }
	 */

	/*
	 * @Override public void onCreateResources( OnCreateResourcesCallback
	 * pOnCreateResourcesCallback) throws IOException { // Goi ham load hinh anh
	 * loadResources(); pOnCreateResourcesCallback.onCreateResourcesFinished();
	 * }
	 *
	 * @Override public void onCreateScene(OnCreateSceneCallback
	 * pOnCreateSceneCallback) throws IOException { // TODO Auto-generated
	 * method stub
	 *
	 * this.mEngine.registerUpdateHandler(new FPSLogger()); scene = new Scene();
	 * scene.setTouchAreaBindingOnActionDownEnabled(true); // Goi ham gan cac
	 * hình vào Spire attachChilds(); // Load cac file am thanh khi click Play
	 * recordManager = new RecordManager(this, scene,
	 * objetosSolo.getBotaoPlay(), objetosSolo.getBotaoStop(),
	 * objetosSolo.getBotaoRecordAtivo(), this, getAssets());
	 *
	 * pOnCreateSceneCallback.onCreateSceneFinished(scene);
	 *
	 * }
	 */
	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.splashBitmapTextureAtlas = new BitmapTextureAtlas(1024, 512,
				TextureOptions.NEAREST);
		this.splashTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.splashBitmapTextureAtlas, this,
						"splash_screen.jpg", 0, 0);
		TextureManager localTextureManager = this.mEngine.getTextureManager();
		ITexture[] arrayOfITexture = new ITexture[1];
		arrayOfITexture[0] = this.splashBitmapTextureAtlas;
		localTextureManager.loadTextures(arrayOfITexture);
	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		this.splashScene = new Scene();
		this.splashScene.setBackgroundEnabled(false);
		this.splashScene.attachChild(new Sprite(0.0F, 0.0F,
				this.metrics.widthPixels, this.metrics.heightPixels,
				this.splashTextureRegion));
		return this.splashScene;
	}

	public void loadScene() {
		this.scene = new Scene();
		this.scene.setTouchAreaBindingEnabled(true);
		montaTela();
		this.recordManager = new RecordManager(this, this.scene,
				this.objetosSolo.getBotaoPlay(),
				this.objetosSolo.getBotaoStop(),
				this.objetosSolo.getBotaoRecordAtivo(), this, getAssets());
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		this.mEngine.registerUpdateHandler(new TimerHandler(0.01F,
				new ITimerCallback() {
					public void onTimePassed(TimerHandler paramTimerHandler) {
						RealDrumActivity.this.mEngine
								.unregisterUpdateHandler(paramTimerHandler);
						RealDrumActivity.this.loadResources();
						RealDrumActivity.this.loadScene();
						RealDrumActivity.this.mEngine
								.setScene(RealDrumActivity.this.scene);
						/*
						 * RealDrumActivity.access$402(RealDrumActivity.this,
						 * null);
						 * RealDrumActivity.access$502(RealDrumActivity.this,
						 * null);
						 * RealDrumActivity.access$602(RealDrumActivity.this,
						 * null);
						 */
						RealDrumActivity.this.runOnUiThread(new Runnable() {
							public void run() {
								((RelativeLayout) RealDrumActivity.this.loadingProgressBar
										.getParent())
										.removeView(RealDrumActivity.this.loadingProgressBar);
							}
						});
					}
				}));
	}

	// phong to thu nho image
	@Override
	public void animateSprite(final Sprite sprite) {
		boolean flag;
		if (Preferences.isAnimado()) {
			mEngine.registerUpdateHandler(new TimerHandler(0.01F,
					new ITimerCallback() {

						@Override
						public void onTimePassed(TimerHandler pTimerHandler) {
							// TODO Auto-generated method stub
							sprite.setScale(0.025F + sprite.getScaleX());
							if (sprite.getScaleX() <= 1.1F) {
								pTimerHandler.reset();
								return;
							}
							sprite.setScale(1.0F);
							mEngine.unregisterUpdateHandler(pTimerHandler);

						}
					}));
		}
		flag = Preferences.isVibrate();
		if (flag) {
			try {
				vibrator.vibrate(100L);
			} catch (Exception exception1) {
			} finally {

			}
		}
		return;

	}


	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_preferences:
			Intent intent = new Intent(getBaseContext(),
					PreferencesActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_about:

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void flip() {
		boolean flag;
		if (!Preferences.isFloorLeft()) {
			flag = true;
		} else {
			flag = false;
		}
		Preferences.setFloorLeft(flag);
		attachChilds();
	}

	@Override
	public void gravarNota(int paramInt) {
		recordManager.gravarNota(paramInt);
	}

	@Override
	public void montaTela() {
		attachChilds();
	}

	@Override
	public void play() {
		recordManager.play();
	}

	@Override
	public void playNota(int paramInt) {
		sons.play(paramInt);
		switch (paramInt) {
		default:
			return;
		case 1:
			animateSprite(objetosSolo.getKick1());
			return;
		case 14:
			switch (Preferences.getKick2To()) {
			default:
				return;
			case 0:
				animateSprite(objetosSolo.getKick2());
				return;
			case 1:
				animateSprite(objetosSolo.getBlock());
				return;
			case 2:
				animateSprite(objetosSolo.getCowbell());
				return;
			case 3:
			}
			animateSprite(objetosSolo.getTambourine());
			return;
		case 2:
			animateSprite(objetosSolo.getSnare());
			return;
		case 15:
			animateSprite(objetosSolo.getSnare());
			return;
		case 3:
			animateSprite(objetosSolo.getTom1());
			return;
		case 4:
			animateSprite(objetosSolo.getTom2());
			return;
		case 5:
			animateSprite(objetosSolo.getTom3());
			return;
		case 6:
			if (Preferences.isFloorLeft()) {
				animateSprite(objetosSolo.getFloorl());
				return;
			}
			animateSprite(objetosSolo.getFloorr());
			return;
		case 7:
			animateSprite(objetosSolo.getCrash1());
			return;
		case 8:
			if (Preferences.isCrash2ToChina()) {
				animateSprite(objetosSolo.getChina());
				return;
			}
			animateSprite(objetosSolo.getCrash2());
			return;
		case 9:
			animateSprite(objetosSolo.getSplash());
			return;
		case 10:
			animateSprite(objetosSolo.getSine());
			return;
		case 13:
			animateSprite(objetosSolo.getSine());
			return;
		case 11:
			if (Preferences.isFloorLeft()) {
				animateSprite(objetosSolo.getOpenhhr());
				return;
			}
			animateSprite(objetosSolo.getOpenhhl());
			return;
		case 12:
		}
		if (Preferences.isFloorLeft()) {
			animateSprite(objetosSolo.getClosehhr());
			return;
		}
		animateSprite(objetosSolo.getClosehhl());
	}

	@Override
	public void rec() {
		recordManager.rec();
	}

	@Override
	public void stop() {
		recordManager.stop();
	}

	// Gan cac bien hinh anh vao Scene
	public void attachChilds() {
		runOnUpdateThread(new Runnable() {
			public void run() {

				if (!isPrimeiraVezCarregado) {

					scene.detachChild(objetosSolo.getFundo());
					scene.detachChild(objetosSolo.getCabecalho());
					scene.detachChild(objetosSolo.getLogo());
					scene.detachChild(objetosSolo.getBotaoConfig());
					scene.detachChild(objetosSolo.getBotaoFlip());
					scene.detachChild(objetosSolo.getBotaoPlay());
					scene.detachChild(objetosSolo.getBotaoStop());
					scene.detachChild(objetosSolo.getBotaoRecordAtivo());
					scene.detachChild(objetosSolo.getBotaoRecordInativo());

					scene.detachChild(objetosSolo.getKick1());
					scene.detachChild(objetosSolo.getSnare());
					scene.detachChild(objetosSolo.getTom1());
					scene.detachChild(objetosSolo.getTom2());
					scene.detachChild(objetosSolo.getTom3());
					scene.detachChild(objetosSolo.getCrash1());
					scene.detachChild(objetosSolo.getSplash());
					scene.detachChild(objetosSolo.getRide());
					scene.detachChild(objetosSolo.getSine());

					if (scene.getChildIndex(objetosSolo.getRimshot()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getRimshot());
						scene.detachChild(objetosSolo.getRimshot());
					}

					if (scene.getChildIndex(objetosSolo.getOpenhhr()) > -1) {// detachChild
																				// :
																				// Openhhr,Closehhr,Floorl
						scene.unregisterTouchArea(objetosSolo.getOpenhhr());
						scene.unregisterTouchArea(objetosSolo.getClosehhr());
						scene.unregisterTouchArea(objetosSolo.getFloorl());
						scene.detachChild(objetosSolo.getOpenhhr());
						scene.detachChild(objetosSolo.getClosehhr());
						scene.detachChild(objetosSolo.getFloorl());
					}

					if (scene.getChildIndex(objetosSolo.getOpenhhl()) > -1) {// detachChild
																				// :
																				// Openhhl,Closehhl,Floorr
						scene.unregisterTouchArea(objetosSolo.getOpenhhl());
						scene.unregisterTouchArea(objetosSolo.getClosehhl());
						scene.unregisterTouchArea(objetosSolo.getFloorr());
						scene.detachChild(objetosSolo.getOpenhhl());
						scene.detachChild(objetosSolo.getClosehhl());
						scene.detachChild(objetosSolo.getFloorr());
					}

					if (scene.getChildIndex(objetosSolo.getChina()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getChina());
						scene.detachChild(objetosSolo.getChina());
					}
					if (scene.getChildIndex(objetosSolo.getCrash2()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getCrash2());
						scene.detachChild(objetosSolo.getCrash2());
					}
					if (scene.getChildIndex(objetosSolo.getKick2()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getKick2());
						scene.detachChild(objetosSolo.getKick2());
					}
					if (scene.getChildIndex(objetosSolo.getBlock()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getBlock());
						scene.detachChild(objetosSolo.getBlock());
					}
					if (scene.getChildIndex(objetosSolo.getCowbell()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getCowbell());
						scene.detachChild(objetosSolo.getCowbell());
					}
					if (scene.getChildIndex(objetosSolo.getTambourine()) > -1) {
						scene.unregisterTouchArea(objetosSolo.getTambourine());
						scene.detachChild(objetosSolo.getTambourine());
					}

					scene.unregisterTouchArea(objetosSolo.getKick1());
					scene.unregisterTouchArea(objetosSolo.getSnare());
					scene.unregisterTouchArea(objetosSolo.getTom1());
					scene.unregisterTouchArea(objetosSolo.getTom2());
					scene.unregisterTouchArea(objetosSolo.getTom3());
					scene.unregisterTouchArea(objetosSolo.getCrash1());
					scene.unregisterTouchArea(objetosSolo.getSplash());
					scene.unregisterTouchArea(objetosSolo.getRide());
					scene.unregisterTouchArea(objetosSolo.getSine());

					scene.unregisterTouchArea(objetosSolo.getBotaoConfig());
					scene.unregisterTouchArea(objetosSolo.getBotaoStop());
					scene.unregisterTouchArea(objetosSolo.getBotaoPlay());
					scene.unregisterTouchArea(objetosSolo.getBotaoFlip());
					scene.unregisterTouchArea(objetosSolo.getBotaoRecordAtivo());

				} else {

					isPrimeiraVezCarregado = false;
				}
				scene.attachChild(objetosSolo.getFundo());
				switch (Preferences.getKick2To()) {
				case 0:
					scene.attachChild(objetosSolo.getKick2());
					scene.registerTouchArea(objetosSolo.getKick2());
					break;
				case 1:
					scene.attachChild(objetosSolo.getBlock());
					scene.registerTouchArea(objetosSolo.getBlock());
					break;
				case 2:
					scene.attachChild(objetosSolo.getCowbell());
					scene.registerTouchArea(objetosSolo.getCowbell());
					break;
				case 3:
					scene.attachChild(objetosSolo.getTambourine());
					scene.registerTouchArea(objetosSolo.getTambourine());
					break;
				}
				scene.attachChild(objetosSolo.getKick1());
				if (Preferences.isRimshot()) {
					scene.attachChild(RealDrumActivity.this.objetosSolo
							.getRimshot());
				}
				scene.attachChild(objetosSolo.getSnare());
				scene.attachChild(objetosSolo.getTom1());
				scene.attachChild(objetosSolo.getTom2());
				scene.attachChild(objetosSolo.getTom3());
				if (Preferences.isFloorLeft()) {
					// attachChild : Openhhr,Closehhr,Floorl
					scene.attachChild(objetosSolo.getOpenhhr());
					scene.attachChild(objetosSolo.getClosehhr());
					scene.attachChild(objetosSolo.getFloorl());
					scene.registerTouchArea(objetosSolo.getOpenhhr());
					scene.registerTouchArea(objetosSolo.getClosehhr());
					scene.registerTouchArea(objetosSolo.getFloorl());
				} else {
					// attachChild : Openhhl,Closehhl,Floorr
					scene.attachChild(objetosSolo.getOpenhhl());
					scene.attachChild(objetosSolo.getClosehhl());
					scene.attachChild(objetosSolo.getFloorr());
					scene.registerTouchArea(objetosSolo.getOpenhhl());
					scene.registerTouchArea(objetosSolo.getClosehhl());
					scene.registerTouchArea(objetosSolo.getFloorr());
				}

				if (Preferences.isCrash2ToChina()) {
					scene.attachChild(objetosSolo.getChina());
					scene.registerTouchArea(objetosSolo.getChina());
				} else {
					scene.attachChild(objetosSolo.getCrash2());
					scene.registerTouchArea(objetosSolo.getCrash2());
				}
				scene.attachChild(objetosSolo.getRide());
				scene.attachChild(objetosSolo.getSine());
				scene.attachChild(objetosSolo.getCrash1());
				scene.attachChild(objetosSolo.getSplash());

				scene.attachChild(objetosSolo.getCabecalho());
				scene.attachChild(objetosSolo.getLogo());
				scene.attachChild(objetosSolo.getBotaoConfig());
				scene.attachChild(objetosSolo.getBotaoFlip());

				if ((recordManager.isPlaying())
						|| (recordManager.isRecording())) {
					scene.detachChild(objetosSolo.getBotaoPlay());
					scene.unregisterTouchArea(objetosSolo.getBotaoPlay());
					scene.attachChild(objetosSolo.getBotaoStop());
					scene.registerTouchArea(objetosSolo.getBotaoStop());
				} else {
					scene.detachChild(objetosSolo.getBotaoStop());
					scene.unregisterTouchArea(objetosSolo.getBotaoStop());
					scene.attachChild(objetosSolo.getBotaoPlay());
					scene.registerTouchArea(objetosSolo.getBotaoPlay());
				}

				scene.attachChild(objetosSolo.getBotaoRecordInativo());
				scene.attachChild(objetosSolo.getBotaoRecordAtivo());

				// Set registerTouch
				scene.registerTouchArea(objetosSolo.getBotaoConfig());
				scene.registerTouchArea(objetosSolo.getBotaoFlip());
				scene.registerTouchArea(objetosSolo.getBotaoRecordAtivo());

				scene.registerTouchArea(objetosSolo.getKick1());
				if (Preferences.isRimshot()) {
					scene.registerTouchArea(objetosSolo.getRimshot());
				}
				scene.registerTouchArea(objetosSolo.getSnare());
				scene.registerTouchArea(objetosSolo.getTom1());
				scene.registerTouchArea(objetosSolo.getTom2());
				scene.registerTouchArea(objetosSolo.getTom3());
				scene.registerTouchArea(objetosSolo.getCrash1());
				scene.registerTouchArea(objetosSolo.getSplash());
				scene.registerTouchArea(objetosSolo.getRide());
				scene.registerTouchArea(objetosSolo.getSine());
			}
		});

	}

	public void loadResources() {

		Preferences.initPreferences(getSharedPreferences(getPackageName(), 0),
				this);
		// Rung
		vibrator = ((Vibrator) getSystemService("vibrator"));
		// Khoi tao cac hinh anh
		textureRegions = new TextureRegions();
		sons = new Sons(this);
		Pad.setBase(this);
		Pad.setSons(sons);
		// Duong dan hinh anh
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		// Load hình trên menu trên
		BitmapTextureAtlas localBitmapTextureAtlas1 = new BitmapTextureAtlas(
				1024, 128, TextureOptions.BILINEAR);
		textureRegions.setCabecalho(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas1, this,
						"cabecalho.png", 0, 0));
		textureRegions.setLogo(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas1, this, "logo.png", 1,
						0));
		textureRegions.setBotaoFlip(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas1, this, "bt_flip.png",
						578, 0));
		textureRegions.setBotaoPlay(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas1, this, "bt_play.png",
						650, 0));
		textureRegions.setBotaoStop(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas1, this, "bt_stop.png",
						722, 0));
		textureRegions
				.setBotaoRecordInativo(BitmapTextureAtlasTextureRegionFactory
						.createFromAsset(localBitmapTextureAtlas1, this,
								"bt_record_inativo.png", 793, 0));
		textureRegions
				.setBotaoRecordAtivo(BitmapTextureAtlasTextureRegionFactory
						.createFromAsset(localBitmapTextureAtlas1, this,
								"bt_record_ativo.png", 866, 0));
		textureRegions.setBotaoConfig(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas1, this,
						"bt_config.png", 949, 0));
		// Load hinh nen
		BitmapTextureAtlas localBitmapTextureAtlas2 = new BitmapTextureAtlas(
				1024, 512, TextureOptions.BILINEAR);
		textureRegions.setFundo(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas2, this, "fundo.jpg",
						0, 0));
		// Load các hình chính
		BitmapTextureAtlas localBitmapTextureAtlas3 = new BitmapTextureAtlas(
				2048, 2048, TextureOptions.BILINEAR);
		textureRegions.setKick1(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "kick1.png",
						0, 0));
		textureRegions.setKick2(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "kick2.png",
						512, 0));
		textureRegions.setSnare(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "snare.png",
						1040, 0));
		textureRegions.setTom1(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "tom1.png",
						1456, 0));
		textureRegions.setTambourine(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this,
						"tambourine.png", 1696, 0));
		textureRegions.setTom2(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "tom2.png", 0,
						512));
		textureRegions.setTom3(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "tom3.png",
						320, 512));
		textureRegions.setCrash1(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "crash1.png",
						640, 512));
		textureRegions.setCrash2(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "crash2.png",
						1040, 512));
		textureRegions.setSine(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "sine.png",
						1376, 512));
		textureRegions.setSplash(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "splash.png",
						0, 1024));
		textureRegions.setRide(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "ride.png",
						256, 1024));
		textureRegions.setClosehhl(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this,
						"closehhl.png", 480, 1024));
		textureRegions.setClosehhr(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this,
						"closehhr.png", 816, 1024));
		textureRegions.setChina(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "china.png",
						1152, 1024));
		textureRegions.setRimshot(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "rimshot.png",
						1488, 1024));
		textureRegions.setOpenhhl(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "openhhl.png",
						0, 1536));
		textureRegions.setOpenhhr(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "openhhr.png",
						288, 1536));
		textureRegions.setFloorl(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "floorl.png",
						560, 1536));
		textureRegions.setFloorr(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "floorr.png",
						848, 1536));
		textureRegions.setBlock(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "block.png",
						1136, 1536));
		textureRegions.setCowbell(BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(localBitmapTextureAtlas3, this, "cowbell.png",
						1416, 1536));

		// Load hinh anh len
		mEngine.getTextureManager().loadTextures(new ITexture[]{localBitmapTextureAtlas1,localBitmapTextureAtlas2,localBitmapTextureAtlas3});

		SoundFactory.setAssetBasePath("sfx/");
		SoundManager localSoundManager = new SoundManager(13);
		try {
			sons.setKick(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "kick.ogg"));
			sons.setSnare(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "snare.ogg"));
			sons.setRimshot(SoundFactory.createSoundFromAsset(
					localSoundManager, this, "rimshot.ogg"));
			sons.setTom1(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "tom1.ogg"));
			sons.setTom2(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "tom2.ogg"));
			sons.setTom3(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "tom3.ogg"));
			sons.setFloor(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "floor.ogg"));
			sons.setCrash1(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "crash1.ogg"));
			sons.setCrash2(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "crash2.ogg"));
			sons.setSplash(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "splash.ogg"));
			sons.setRide(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "ride.ogg"));
			sons.setOpenhh(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "openhh.ogg"));
			sons.setClosehh(SoundFactory.createSoundFromAsset(
					localSoundManager, this, "closehh.ogg"));
			sons.setSine(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "sine.ogg"));
			sons.setChina(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "china.ogg"));
			sons.setBlock(SoundFactory.createSoundFromAsset(localSoundManager,
					this, "block.ogg"));
			sons.setCowbell(SoundFactory.createSoundFromAsset(
					localSoundManager, this, "cowbell.ogg"));
			sons.setTambourine(SoundFactory.createSoundFromAsset(
					localSoundManager, this, "tambourine.ogg"));
			objetosSolo = new Objetos(camera.getWidth(), camera.getHeight(),
					metrics.density, textureRegions, this, 0xF & getResources()
							.getConfiguration().screenLayout);

		} catch (IOException localIOException) {
		}
	}

	// Call config menu
	public boolean config() {
		runOnUiThread(new Runnable() {
			public void run() {
				openOptionsMenu();
			}
		});
		return true;
	}

	@Override
	protected synchronized void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.mEngine.stop();
		this.onPauseGame();
	}

	@Override
	protected synchronized void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (this.mEngine.isRunning()) {
			this.mEngine.stop();
		} else {
			this.mEngine.start();
			this.onResumeGame();
		}
		while (true) {
			if (this.loadingProgressBar == null)
				this.loadingProgressBar = ((ProgressBar) findViewById(R.id.loading));
			return;
		}
	}
}
