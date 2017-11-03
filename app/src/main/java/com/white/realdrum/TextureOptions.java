package com.white.realdrum;


import javax.microedition.khronos.opengles.GL10;

public class TextureOptions
{
  public static final TextureOptions BILINEAR;
  public static final TextureOptions BILINEAR_PREMULTIPLYALPHA;
  public static final TextureOptions DEFAULT;
  public static final TextureOptions NEAREST = new TextureOptions(9728, 9728, 33071, 33071, false);
  public static final TextureOptions NEAREST_PREMULTIPLYALPHA;
  public static final TextureOptions REPEATING_BILINEAR;
  public static final TextureOptions REPEATING_BILINEAR_PREMULTIPLYALPHA;
  public static final TextureOptions REPEATING_NEAREST;
  public static final TextureOptions REPEATING_NEAREST_PREMULTIPLYALPHA;
  public final int mMagFilter;
  public final int mMinFilter;
  public final boolean mPreMultipyAlpha;
  public final float mWrapS;
  public final float mWrapT;

  static
  {
    BILINEAR = new TextureOptions(9729, 9729, 33071, 33071, false);
    REPEATING_NEAREST = new TextureOptions(9728, 9728, 10497, 10497, false);
    REPEATING_BILINEAR = new TextureOptions(9729, 9729, 10497, 10497, false);
    NEAREST_PREMULTIPLYALPHA = new TextureOptions(9728, 9728, 33071, 33071, true);
    BILINEAR_PREMULTIPLYALPHA = new TextureOptions(9729, 9729, 33071, 33071, true);
    REPEATING_NEAREST_PREMULTIPLYALPHA = new TextureOptions(9728, 9728, 10497, 10497, true);
    REPEATING_BILINEAR_PREMULTIPLYALPHA = new TextureOptions(9729, 9729, 10497, 10497, true);
    DEFAULT = NEAREST_PREMULTIPLYALPHA;
  }

  public TextureOptions(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    this.mMinFilter = paramInt1;
    this.mMagFilter = paramInt2;
    this.mWrapT = paramInt3;
    this.mWrapS = paramInt4;
    this.mPreMultipyAlpha = paramBoolean;
  }

  public void apply(GL10 paramGL10)
  {
    paramGL10.glTexParameterf(3553, 10241, this.mMinFilter);
    paramGL10.glTexParameterf(3553, 10240, this.mMagFilter);
    paramGL10.glTexParameterf(3553, 10242, this.mWrapS);
    paramGL10.glTexParameterf(3553, 10243, this.mWrapT);
    paramGL10.glTexEnvf(8960, 8704, 8448.0F);
  }
}