package com.white.realdrum;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.opengl.texture.source.ITextureAtlasSource;
import org.anddev.andengine.util.Debug;

public abstract interface ITexture {
	public abstract void bind(GL10 paramGL10);

	public abstract int getHardwareTextureID();

	public abstract int getHeight();

	public abstract TextureOptions getTextureOptions();

	public abstract ITextureStateListener getTextureStateListener();

	public abstract int getWidth();

	public abstract boolean hasTextureStateListener();

	public abstract boolean isLoadedToHardware();

	public abstract boolean isUpdateOnHardwareNeeded();

	public abstract void loadToHardware(GL10 paramGL10) throws IOException;

	public abstract void reloadToHardware(GL10 paramGL10) throws IOException;

	public abstract void setLoadedToHardware(boolean paramBoolean);

	public abstract void setUpdateOnHardwareNeeded(boolean paramBoolean);

	public abstract void unloadFromHardware(GL10 paramGL10);

	public static abstract interface ITextureStateListener {
		public abstract void onLoadedToHardware(ITexture paramITexture);

		public abstract void onUnloadedFromHardware(ITexture paramITexture);

		public static class DebugTextureStateListener<T extends ITextureAtlasSource>
				implements ITexture.ITextureStateListener {
			public void onLoadedToHardware(ITexture paramITexture) {
				Debug.d("Texture loaded: " + paramITexture.toString());
			}

			public void onUnloadedFromHardware(ITexture paramITexture) {
				Debug.d("Texture unloaded: " + paramITexture.toString());
			}
		}

		public static class TextureStateAdapter<T extends ITextureAtlasSource>
				implements ITexture.ITextureStateListener {
			public void onLoadedToHardware(ITexture paramITexture) {
			}

			public void onUnloadedFromHardware(ITexture paramITexture) {
			}
		}
	}
}