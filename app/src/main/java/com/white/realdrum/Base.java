package com.white.realdrum;

import org.anddev.andengine.entity.sprite.Sprite;

public abstract interface Base
{
  public abstract void animateSprite(Sprite paramSprite);

  public abstract void flip();

  public abstract void gravarNota(int paramInt);

  public abstract void montaTela();

  public abstract void play();

  public abstract void playNota(int paramInt);

  public abstract void rec();

  public abstract void stop();
}