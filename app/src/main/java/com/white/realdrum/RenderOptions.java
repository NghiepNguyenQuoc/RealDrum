package com.white.realdrum;

public class RenderOptions
{
  private boolean mDisableExtensionDrawTexture = false;
  private boolean mDisableExtensionVertexBufferObjects = false;

  public RenderOptions disableExtensionDrawTexture()
  {
    return setDisableExtensionDrawTexture(true);
  }

  public RenderOptions disableExtensionVertexBufferObjects()
  {
    return setDisableExtensionVertexBufferObjects(true);
  }

  public RenderOptions enableExtensionDrawTexture()
  {
    return setDisableExtensionDrawTexture(false);
  }

  public RenderOptions enableExtensionVertexBufferObjects()
  {
    return setDisableExtensionVertexBufferObjects(false);
  }

  public boolean isDisableExtensionDrawTexture()
  {
    return this.mDisableExtensionDrawTexture;
  }

  public boolean isDisableExtensionVertexBufferObjects()
  {
    return this.mDisableExtensionVertexBufferObjects;
  }

  public RenderOptions setDisableExtensionDrawTexture(boolean paramBoolean)
  {
    this.mDisableExtensionDrawTexture = paramBoolean;
    return this;
  }

  public RenderOptions setDisableExtensionVertexBufferObjects(boolean paramBoolean)
  {
    this.mDisableExtensionVertexBufferObjects = paramBoolean;
    return this;
  }
}