package com.white.realdrum;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.example.realdrum.R;
import com.white.realdrum.util.Utils;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Pad {

	private static RealDrumActivity base;
	private static float frameHeight;
	private static float frameWidth;
	private static float frameY;
	private static Sons sons;
	private Bitmap bitmap;
	private int sound;
	private Sprite sprite;
	private Sprite spriteAnimated;
	private int[] arrDrawable = { R.drawable.kick1, R.drawable.snare,
			R.drawable.tom1, R.drawable.tom2, R.drawable.tom3,
			R.drawable.floorl, R.drawable.crash1, R.drawable.crash2,
			R.drawable.splash, R.drawable.ride, R.drawable.openhhl,
			R.drawable.closehhl, R.drawable.sine, R.drawable.kick2,
			R.drawable.rimshot };

	public Pad(final TextureRegion textureregion, final int i, float f, float f1,
			float f2, float f3)// i: stt cua sprite, f:
	{
		spriteAnimated = null;
		sound = i;
		float f4 = f * frameWidth;
		float f5 = f1 * frameHeight + frameY;
		final float f6 = f2 * frameWidth;
		final float f7 = f3 * frameHeight;
		sprite = new Sprite(f4, f5, f6, f7, textureregion) {

			public boolean onAreaTouched(TouchEvent touchevent, float f8,
					float f9) {
				boolean flag1;
				boolean flag;
				int j;
				try {
					flag = touchevent.isActionDown();
				} catch (Exception exception) {
					return false;
				}
				if (!flag) {
					return false;
				}

				flag1 = false;
				bitmap = Bitmap.createScaledBitmap(
						Utils.drawableToBitmap(base.getApplicationContext()
								.getResources().getDrawable(arrDrawable[i-1])), (int) f6,
						(int) f7, false);
				j = Color.alpha(bitmap.getPixel((int) f8, (int) f9));
				if (j == 0) {
					return false;
				}
				Pad.sons.play(sound);
				if (spriteAnimated != null) {
					Pad.base.animateSprite(spriteAnimated);

				}
				Pad.base.animateSprite(sprite);
				flag1 = true;
				return flag1;
			}

		};
		
	}

	public static void setBase(RealDrumActivity base1) {
		base = base1;
	}

	public static void setFrameHeight(float f) {
		frameHeight = f;
	}

	public static void setFrameWidth(float f) {
		frameWidth = f;
	}

	public static void setFrameY(float f) {
		frameY = f;
	}

	public static void setSons(Sons sons1) {
		sons = sons1;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSpriteAnimated(Sprite sprite1) {
		spriteAnimated = sprite1;
	}

}
