package com.white.realdrum;

import com.white.realdrum.Pad;
import com.white.realdrum.TextureRegions;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

public class Objetos {

	private RealDrumActivity base;
	private Pad block;
	private Sprite botaoFlip;
	private Sprite botaoPlay;
	private Sprite botaoRecordAtivo;
	private Sprite botaoRecordInativo;
	private Sprite botaoConfig;
	private Sprite botaoStop;
	private float botoesCabecalhoOffset;
	private float botoesCabecalhoSize;
	private float botoesCabecalhoY;
	private Sprite cabecalho;
	private Pad china;
	private Pad closehhl;
	private Pad closehhr;
	private Pad cowbell;
	private Pad crash1;
	private Pad crash2;
	private Pad floorl;
	private Pad floorr;
	private Sprite fundo;
	private float heightCabecalho;
	private float heightFundo;
	private Pad kick1;
	private Pad kick2;
	private Sprite logo;
	private float logoWidth;
	private Pad openhhl;
	private Pad openhhr;
	private Pad ride;
	private Pad rimshot;
	private Pad sine;
	private Pad snare;
	private Pad splash;
	private Pad tambourine;
	private TextureRegions textureRegions;
	private Pad tom1;
	private Pad tom2;
	private Pad tom3;
	private float width;

	public Objetos(float var1, float var2, float var3, TextureRegions var4,
			RealDrumActivity var5, int var6) {
		float var7;
		switch (var6) {
		case 3:
			var7 = 60.0F;
			break;
		case 4:
			var7 = 90.0F;
			break;
		default:
			var7 = 50.0F;
		}

		this.width = var1;
		this.heightCabecalho = var7 * var3;
		this.heightFundo = var2 - this.heightCabecalho;
		this.logoWidth = var3 * (320.0F * var7 / 50.0F);
		if (var6 == 1) {
			this.botoesCabecalhoSize = 0.4F * this.heightCabecalho;
		} else {
			this.botoesCabecalhoSize = 0.8F * this.heightCabecalho;
		}

		this.botoesCabecalhoOffset = 0.2F * this.heightCabecalho;
		this.botoesCabecalhoY = this.heightCabecalho / 2.0F
				- this.botoesCabecalhoSize / 2.0F;
		this.textureRegions = var4;
		this.base = var5;
		Pad.setFrameWidth(var1);
		Pad.setFrameHeight(this.heightFundo);
		Pad.setFrameY(this.heightCabecalho);
		this.criarCabecalho();
		this.criarLogo();
		this.criarBotaoConfig();
		this.criarBotaoFlip();
		this.criarBotaoPlay();
		this.criarBotaoStop();
		this.criarBotaoRecordAtivo();
		this.criarBotaoRecordInativo();
		this.criarFundo();
		this.criarKick1();
		this.criarKick2();
		this.criarSnare();
		this.criarRimshot();
		this.criarTom1();
		this.criarTom2();
		this.criarTom3();
		this.criarCrash1();
		this.criarCrash2();
		this.criarChina();
		this.criarSplash();
		this.criarRide();
		this.criarSine();
		this.criarOpenhhr();
		this.criarClosehhr();
		this.criarFloorr();
		this.criarOpenhhl();
		this.criarClosehhl();
		this.criarFloorl();
		this.criarBlock();
		this.criarCowbell();
		this.criarTambourine();
		this.ride.setSpriteAnimated(this.sine.getSprite());
		this.rimshot.setSpriteAnimated(this.snare.getSprite());
	}

	private void criarBlock() {
		this.block = new Pad(this.textureRegions.getBlock(), 14, 0.5812F,
				0.593F, 0.175F, 0.372F);
	}

	private void criarBotaoConfig() {
		this.botaoConfig = new Sprite(0.0F * this.botoesCabecalhoOffset,
				this.botoesCabecalhoY, this.botoesCabecalhoSize,
				this.botoesCabecalhoSize, this.textureRegions.getBotaoConfig()) {
			public boolean onAreaTouched(TouchEvent var1, float var2, float var3) {
				if (var1.isActionDown()) {

					Objetos.this.base.config();
				}

				return true;
			}
		};
	}

	private void criarBotaoFlip() {
		this.botaoFlip = new Sprite(0.4F * this.botoesCabecalhoOffset
				+ this.botoesCabecalhoSize, this.botoesCabecalhoY,
				this.botoesCabecalhoSize, this.botoesCabecalhoSize,
				this.textureRegions.getBotaoFlip()) {
			public boolean onAreaTouched(TouchEvent var1, float var2, float var3) {
				if (var1.isActionDown()) {
					Objetos.this.base.flip();
				}

				return true;
			}
		};
	}

	private void criarBotaoPlay() {
		this.botaoPlay = new Sprite(5.0F * this.botoesCabecalhoOffset
				+ this.botoesCabecalhoSize, this.botoesCabecalhoY,
				this.botoesCabecalhoSize, this.botoesCabecalhoSize,
				this.textureRegions.getBotaoPlay()) {
			public boolean onAreaTouched(TouchEvent var1, float var2, float var3) {
				if (var1.isActionDown()) {
					Objetos.this.base.play();
					return true;
				} else {
					return false;
				}
			}
		};
	}

	private void criarBotaoRecordAtivo() {
		this.botaoRecordAtivo = new Sprite(5.0F * this.botoesCabecalhoOffset
				+ 2.0F * this.botoesCabecalhoSize, this.botoesCabecalhoY,
				this.botoesCabecalhoSize, this.botoesCabecalhoSize,
				this.textureRegions.getBotaoRecordAtivo()) {
			public boolean onAreaTouched(TouchEvent var1, float var2, float var3) {
				if (var1.isActionDown()) {
					Objetos.this.base.rec();
					return true;
				} else {
					return false;
				}
			}
		};
	}

	private void criarBotaoRecordInativo() {
		this.botaoRecordInativo = new Sprite(5.0F * this.botoesCabecalhoOffset
				+ 2.0F * this.botoesCabecalhoSize, this.botoesCabecalhoY,
				this.botoesCabecalhoSize, this.botoesCabecalhoSize,
				this.textureRegions.getBotaoRecordInativo());
	}

	private void criarBotaoStop() {
		this.botaoStop = new Sprite(5.0F * this.botoesCabecalhoOffset
				+ this.botoesCabecalhoSize, this.botoesCabecalhoY,
				this.botoesCabecalhoSize, this.botoesCabecalhoSize,
				this.textureRegions.getBotaoStop()) {
			public boolean onAreaTouched(TouchEvent var1, float var2, float var3) {
				if (var1.isActionDown()) {
					Objetos.this.base.stop();
					return true;
				} else {
					return false;
				}
			}
		};
	}

	private void criarCabecalho() {
		this.cabecalho = new Sprite(0.0F, 0.0F, this.width,
				this.heightCabecalho, this.textureRegions.getCabecalho());
	}

	private void criarChina() {
		this.china = new Pad(this.textureRegions.getChina(), 8, 0.52F, 0.0F,
				0.21F, 0.28F);
	}

	private void criarClosehhl() {
		this.closehhl = new Pad(this.textureRegions.getClosehhl(), 12, 0.0F,
				0.4F, 0.21F, 0.38F);
	}

	private void criarClosehhr() {
		this.closehhr = new Pad(this.textureRegions.getClosehhr(), 12, 0.79F,
				0.4F, 0.21F, 0.37F);
	}

	private void criarCowbell() {
		this.cowbell = new Pad(this.textureRegions.getCowbell(), 14, 0.5875F,
				0.6395F, 0.1937F, 0.3023F);
	}

	private void criarCrash1() {
		this.crash1 = new Pad(this.textureRegions.getCrash1(), 7, 0.04F, 0.0F,
				0.25F, 0.47F);
	}

	private void criarCrash2() {
		this.crash2 = new Pad(this.textureRegions.getCrash2(), 8, 0.52F, 0.0F,
				0.21F, 0.28F);
	}

	private void criarFloorl() {
		this.floorl = new Pad(this.textureRegions.getFloorl(), 6, 0.0F, 0.43F,
				0.18F, 0.54F);
	}

	private void criarFloorr() {
		this.floorr = new Pad(this.textureRegions.getFloorr(), 6, 0.82F, 0.43F,
				0.18F, 0.54F);
	}

	private void criarFundo() {
		this.fundo = new Sprite(0.0F, this.heightCabecalho, this.width,
				this.heightFundo, this.textureRegions.getFundo());
	}

	private void criarKick1() {
		this.kick1 = new Pad(this.textureRegions.getKick1(), 1, 0.18F, 0.54F,
				0.32F, 0.46F);
	}

	private void criarKick2() {
		this.kick2 = new Pad(this.textureRegions.getKick2(), 14, 0.5F, 0.54F,
				0.33F, 0.46F);
	}

	private void criarLogo() {
		this.logo = new Sprite(this.width - this.logoWidth, 0.0F,
				this.logoWidth, this.heightCabecalho,
				this.textureRegions.getLogo());
	}

	private void criarOpenhhl() {
		this.openhhl = new Pad(this.textureRegions.getOpenhhl(), 11, 0.0F,
				0.66F, 0.18F, 0.34F);

	}

	private void criarOpenhhr() {
		this.openhhr = new Pad(this.textureRegions.getOpenhhr(), 11, 0.83F,
				0.66F, 0.17F, 0.34F);
	}

	private void criarRide() {
		this.ride = new Pad(this.textureRegions.getRide(), 10, 0.72F, 0.0F,
				0.28F, 0.47F);
	}

	private void criarRimshot() {
		this.rimshot = new Pad(this.textureRegions.getRimshot(), 15, 0.37F,
				0.36F, 0.26F, 0.44F);
	}

	private void criarSine() {
		this.sine = new Pad(this.textureRegions.getSine(), 13, 0.72F, 0.0F,
				0.28F, 0.47F);
	}

	private void criarSnare() {
		this.snare = new Pad(this.textureRegions.getSnare(), 2, 0.37F, 0.36F,
				0.26F, 0.44F);
	}

	private void criarSplash() {
		this.splash = new Pad(this.textureRegions.getSplash(), 9, 0.29F, 0.0F,
				0.16F, 0.3F);
	}

	private void criarTambourine() {
		this.tambourine = new Pad(this.textureRegions.getTambourine(), 14,
				0.5375F, 0.5581F, 0.2187F, 0.4186F);
	}

	private void criarTom1() {
		this.tom1 = new Pad(this.textureRegions.getTom1(), 3, 0.27F, 0.23F,
				0.15F, 0.28F);
	}

	private void criarTom2() {
		this.tom2 = new Pad(this.textureRegions.getTom2(), 4, 0.4F, 0.08F,
				0.2F, 0.33F);
	}

	private void criarTom3() {
		this.tom3 = new Pad(this.textureRegions.getTom3(), 5, 0.58F, 0.19F,
				0.2F, 0.37F);
	}

	public Sprite getBlock() {
		return this.block.getSprite();
	}

	public Sprite getBotaoConfig() {
		return this.botaoConfig;
	}

	public Sprite getBotaoFlip() {
		return this.botaoFlip;
	}

	public Sprite getBotaoPlay() {
		return this.botaoPlay;
	}

	public Sprite getBotaoRecordAtivo() {
		return this.botaoRecordAtivo;
	}

	public Sprite getBotaoRecordInativo() {
		return this.botaoRecordInativo;
	}

	public Sprite getBotaoStop() {
		return this.botaoStop;
	}

	public Sprite getCabecalho() {
		return this.cabecalho;
	}

	public Sprite getChina() {
		return this.china.getSprite();
	}

	public Sprite getClosehhl() {
		return this.closehhl.getSprite();
	}

	public Sprite getClosehhr() {
		return this.closehhr.getSprite();
	}

	public Sprite getCowbell() {
		return this.cowbell.getSprite();
	}

	public Sprite getCrash1() {
		return this.crash1.getSprite();
	}

	public Sprite getCrash2() {
		return this.crash2.getSprite();
	}

	public Sprite getFloorl() {
		return this.floorl.getSprite();
	}

	public Sprite getFloorr() {
		return this.floorr.getSprite();
	}

	public Sprite getFundo() {
		return this.fundo;
	}

	public Sprite getKick1() {
		return this.kick1.getSprite();
	}

	public Sprite getKick2() {
		return this.kick2.getSprite();
	}

	public Sprite getLogo() {
		return this.logo;
	}

	public Sprite getOpenhhl() {
		return this.openhhl.getSprite();
	}

	public Sprite getOpenhhr() {
		return this.openhhr.getSprite();
	}

	public Sprite getRide() {
		return this.ride.getSprite();
	}

	public Sprite getRimshot() {
		return this.rimshot.getSprite();
	}

	public Sprite getSine() {
		return this.sine.getSprite();
	}

	public Sprite getSnare() {
		return this.snare.getSprite();
	}

	public Sprite getSplash() {
		return this.splash.getSprite();
	}

	public Sprite getTambourine() {
		return this.tambourine.getSprite();
	}

	public Sprite getTom1() {
		return this.tom1.getSprite();
	}

	public Sprite getTom2() {
		return this.tom2.getSprite();
	}

	public Sprite getTom3() {
		return this.tom3.getSprite();
	}
}
