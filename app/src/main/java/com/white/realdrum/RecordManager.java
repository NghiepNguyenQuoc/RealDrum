package com.white.realdrum;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.Toast;

import com.example.realdrum.R;
import com.white.realdrum.Base;
import com.white.realdrum.Preferences;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.ui.activity.LayoutGameActivity;

public class RecordManager {

	private static RecordManager instance;
	private AssetManager assetManager;
	private RealDrumActivity base;
	private Sprite botaoPlay;
	private Sprite botaoRecord;
	private Sprite botaoStop;
	private File directory;
	private String gravacao = "";
	private long inicioGravacao;
	private long inicioReproducao;
	private boolean isPlaying;
	private boolean isRecording;
	private LayoutGameActivity layoutGameActivity;
	private Scene scene;

	public RecordManager(LayoutGameActivity var1, Scene var2, Sprite var3,
			Sprite var4, Sprite var5, RealDrumActivity var6, AssetManager var7) {
		this.layoutGameActivity = var1;
		this.scene = var2;
		this.botaoPlay = var3;
		this.botaoStop = var4;
		this.botaoRecord = var5;
		this.base = var6;
		this.assetManager = var7;
		this.isRecording = false;
		this.isPlaying = false;
		this.directory = new File(Environment.getExternalStorageDirectory()
				+ "/" + this.appName() + "/");
		if (!Preferences.isExamplesCopied()) {
			this.copyExampleFiles();
			Preferences.setExamplesCopied(true);
		}

		instance = this;
	}

	// $FF: synthetic method
	static long access$300(RecordManager var0, String var1) {
		return var0.capturaTempo(var1);
	}

	// $FF: synthetic method
	static int access$400(RecordManager var0, String var1) {
		return var0.capturaNota(var1);
	}

	// $FF: synthetic method
	static long access$500(RecordManager var0) {
		return var0.inicioReproducao;
	}

	// $FF: synthetic method
	static long access$502(RecordManager var0, long var1) {
		var0.inicioReproducao = var1;
		return var1;
	}

	// $FF: synthetic method
	static long access$600(RecordManager var0) {
		return var0.currentMillis();
	}

	// $FF: synthetic method
	static boolean access$700(RecordManager var0) {
		return var0.isPlaying;
	}

	// $FF: synthetic method
	static boolean access$702(RecordManager var0, boolean var1) {
		var0.isPlaying = var1;
		return var1;
	}

	// $FF: synthetic method
	static Base access$800(RecordManager var0) {
		return var0.base;
	}

	private void animateRecord(final Sprite var1) {
		synchronized (this) {
		}

		try {
			this.layoutGameActivity.getEngine().registerUpdateHandler(
					new TimerHandler(0.05F, new ITimerCallback() {

						float alpha = 1.0F;
						boolean desaparecendo = true;

						public void onTimePassed(TimerHandler var1x) {
							if (RecordManager.this.isRecording) {
								if (this.desaparecendo) {
									this.alpha -= 0.1F;
									if (this.alpha <= 0.0F) {
										this.desaparecendo = false;
									}
								} else {
									this.alpha += 0.1F;
									if (this.alpha >= 1.0F) {
										this.desaparecendo = true;
									}
								}

								var1.setAlpha(this.alpha);
								var1x.reset();
							} else {
								var1.setAlpha(1.0F);
								RecordManager.this.layoutGameActivity
										.getEngine().unregisterUpdateHandler(
												var1x);
							}
						}
					}));
		} finally {
			;
		}

	}

	private String appName() {
		return this.layoutGameActivity.getResources().getString(
				R.string.app_name);
	}

	private int capturaNota(String var1) {
		try {
			int var3 = Integer.parseInt(var1.split(";")[1]);
			return var3;
		} catch (Exception var4) {
			this.isPlaying = false;
			return 0;
		}
	}

	private long capturaTempo(String var1) {
		int var3;
		try {
			var3 = Integer.parseInt(var1.split(";")[0]);
		} catch (Exception var4) {
			this.isPlaying = false;
			return 0L;
		}

		return (long) var3;
	}

	private void copyExampleFiles() {
		String[] var2;
		label34: {
			String[] var14;
			try {
				var14 = this.assetManager.list("examples");
			} catch (IOException var17) {
				var2 = null;
				break label34;
			}

			var2 = var14;
		}

		this.directory.mkdirs();
		String var4 = this.layoutGameActivity.getResources().getString(
				R.string.record_example);
		String[] var5 = var2;
		int var6 = var2.length;

		for (int var7 = 0; var7 < var6; ++var7) {
			String var8 = var5[var7];

			InputStream var10;
			FileOutputStream var12;
			try {
				var10 = this.assetManager.open("examples/" + var8);
				String var11 = var4 + " " + var8;
				var12 = new FileOutputStream(new File(this.directory, var11));
			} catch (IOException var16) {
				continue;
			}

			try {
				this.copyFile(var10, var12);
				var10.close();
				var12.flush();
				var12.close();
			} catch (IOException var15) {
				;
			}
		}

	}

	private void copyFile(InputStream var1, OutputStream var2)
			throws IOException {
		byte[] var3 = new byte[1024];

		while (true) {
			int var4 = var1.read(var3);
			if (var4 == -1) {
				return;
			}

			var2.write(var3, 0, var4);
		}
	}

	private long currentMillis() {
		synchronized (this) {
		}

		long var2;
		try {
			var2 = Calendar.getInstance().getTimeInMillis();
		} finally {
			;
		}

		return var2;
	}

	public static RecordManager getInstance() {
		return instance;
	}

	public void deleteFile(String var1) {
		try {
			(new File(this.directory + File.separator + var1)).delete();
		} catch (Exception var3) {
			this.layoutGameActivity.runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(layoutGameActivity,
							R.string.record_no_record_found,
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	public String[] getRecordsList() {
		String[] var1 = this.directory.list();

		try {
			Arrays.sort(var1, Collator.getInstance());
			return var1;
		} catch (Exception var3) {
			return this.directory.list();
		}
	}

	public void gravarNota(int var1) {
		if (this.isRecording) {
			long var2 = this.currentMillis();
			if (this.inicioGravacao == 0L) {
				this.inicioGravacao = var2;
			} else {
				this.gravacao = this.gravacao + "\n";
			}

			long var4 = var2 - this.inicioGravacao;
			this.gravacao = this.gravacao + var4 + ";" + var1;
		}

	}

	public boolean isPlaying() {
		return this.isPlaying;
	}

	public boolean isRecording() {
		return this.isRecording;
	}

	public void play() {
		try {
			if (this.getRecordsList().length > 0) {
				Intent var2 = new Intent(this.layoutGameActivity,
						RecordActivity.class);
				this.layoutGameActivity.startActivity(var2);
			} else {
				this.layoutGameActivity.runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(layoutGameActivity,
								R.string.record_no_record_found,
								Toast.LENGTH_LONG).show();
					}
				});
				
			}
		} catch (Exception var3) {
			this.layoutGameActivity.runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(layoutGameActivity,
							R.string.record_no_record_found,
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	public void playFile(String paramString) {

		FileInputStream localFileInputStream;
		StringBuffer localStringBuffer;
		try {
			localFileInputStream = new FileInputStream(new File(directory
					+ File.separator + paramString));
			localStringBuffer = new StringBuffer("");
			while (true) {
				int i = localFileInputStream.read();
				if (i == -1)
					break;
				localStringBuffer.append((char) i);
			}
		} catch (Exception localException) {
			this.layoutGameActivity.runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(layoutGameActivity,
							R.string.record_no_record_found,
							Toast.LENGTH_LONG).show();
				}
			});
			return;
		}
		try {
			localFileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final String str = localStringBuffer.toString();
		isPlaying = true;
		layoutGameActivity.runOnUpdateThread(new Runnable() {
			public void run() {
				try {
					scene.unregisterTouchArea(botaoPlay);
					scene.detachChild(botaoPlay);
					scene.attachChild(botaoStop);
					scene.registerTouchArea(botaoStop);
					new Thread(new Runnable() {
						public void run() {
							String as[] = str.split(System
									.getProperty("line.separator"));
							long l = as.length;
							int i = 1;
							if (l > 0L) {
								long l1 = capturaTempo(as[0]);
								int j = capturaNota(as[0]);
								inicioReproducao = currentMillis();
								do {
									if (!isPlaying) {
										break;
									}
									if (currentMillis() - inicioReproducao >= l1) {
										if (j == 0) {
											if (Preferences.isRepeat()) {
												i = 0;
												inicioReproducao = currentMillis();
											}
										} else {
											base.playNota(j);
										}
										if ((long) (++i) > l) {
											isPlaying = false;
										} else {
											l1 = capturaTempo(as[i - 1]);
											j = capturaNota(as[i - 1]);
										}
									}
								} while (true);
							}
							if (!isRecording)
								layoutGameActivity
										.runOnUpdateThread(new Runnable() {
											public void run() {
												// if
												// (scene.getChildIndex(botaoStop)
												// > -1);
												try {
													scene.detachChild(botaoStop);
													scene.unregisterTouchArea(botaoStop);
													scene.attachChild(botaoPlay);
													scene.registerTouchArea(botaoPlay);
													return;
												} catch (Exception localException) {
												}
											}
										});
						}
					}).start();
					return;
				} catch (Exception localException) {

				}
			}
		});
	}

	public void rec() {
		if (!this.isRecording) {
			this.gravacao = "";
			this.isRecording = true;
			this.inicioGravacao = 0L;
			this.animateRecord(this.botaoRecord);
			if (!this.isPlaying) {
				this.layoutGameActivity.runOnUpdateThread(new Runnable() {
					public void run() {
						try {
							RecordManager.this.scene
									.unregisterTouchArea(RecordManager.this.botaoPlay);
							RecordManager.this.scene
									.detachChild(RecordManager.this.botaoPlay);
							RecordManager.this.scene
									.attachChild(RecordManager.this.botaoStop);
							RecordManager.this.scene
									.registerTouchArea(RecordManager.this.botaoStop);
						} catch (Exception var2) {
							;
						}
					}
				});
			}
		}

	}

	public void renameFile(String var1, String var2) {
		try {
			(new File(this.directory + File.separator + var1))
					.renameTo(new File(this.directory + File.separator + var2));
		} catch (Exception var4) {
			
			this.layoutGameActivity.runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(layoutGameActivity,
							R.string.record_name_not_possible,
							Toast.LENGTH_LONG).show();
				}
			});
			
		}
	}

	public void stop() {
		if (this.isRecording || this.isPlaying) {
			if (this.isRecording && !this.gravacao.equals("")) {
				try {
					this.gravarNota(0);
					this.directory.mkdirs();
					if (this.directory.canWrite()) {
						String var3 = this.layoutGameActivity.getResources()
								.getString(R.string.record_record);
						Calendar var4 = Calendar.getInstance();
						SimpleDateFormat var5 = new SimpleDateFormat(
								"yyyy-MM-dd HH-mm-ss", Locale.getDefault());
						String var6 = var3 + " " + var5.format(var4.getTime());
						BufferedWriter var7 = new BufferedWriter(
								new FileWriter(new File(this.directory, var6)));
						var7.write(this.gravacao);
						var7.close();
						this.layoutGameActivity.runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(layoutGameActivity,
										R.string.record_record_saved,
										Toast.LENGTH_LONG).show();
							}
						});

					}
				} catch (IOException var8) {
					this.layoutGameActivity.runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(layoutGameActivity,
									R.string.record_record_error,
									Toast.LENGTH_LONG).show();
						}
					});

				}
			}

			this.isPlaying = false;
			this.isRecording = false;
			this.layoutGameActivity.runOnUpdateThread(new Runnable() {
				public void run() {

					// if(RecordManager.this.scene.getChildIndex(RecordManager.this.botaoStop)
					// > -1) {
					try {
						RecordManager.this.scene
								.detachChild(RecordManager.this.botaoStop);
						RecordManager.this.scene
								.unregisterTouchArea(RecordManager.this.botaoStop);
						RecordManager.this.scene
								.attachChild(RecordManager.this.botaoPlay);
						RecordManager.this.scene
								.registerTouchArea(RecordManager.this.botaoPlay);
					} catch (Exception var2) {
						return;
					}
					// }

				}
			});
		}

	}
}