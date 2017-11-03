package com.white.realdrum;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.realdrum.R;
import com.white.realdrum.DialogActivity;
import com.white.realdrum.Preferences;

import java.util.ArrayList;
import java.util.List;

public class PreferencesActivity extends DialogActivity {

	private static final int PREFERENCES_ANIMATION = 0;
	private static final int PREFERENCES_CRASH_TO_CHINA = 4;
	private static final int PREFERENCES_KICK_2_TO = 5;
	private static final int PREFERENCES_REPEAT = 1;
	private static final int PREFERENCES_RIMSHOT = 3;
	private static final int PREFERENCES_VIBRATE = 2;

	String[] changeKickList;
	List preferenceItens = new ArrayList();

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.textTitle.setText(R.string.menu_preferences);
		this.buttonEdit.setEnabled(false);
		((LinearLayout) this.buttonEdit.getParent())
				.removeView(this.buttonEdit);
		this.changeKickList = this.getResources().getStringArray(
				R.array.changeKick);// set string into item in changeKichList
		this.preferenceItens.add(this.getResources()
				.getText(R.string.preferences_animation).toString());
		this.preferenceItens.add(this.getResources()
				.getText(R.string.preferences_repeat).toString());
		this.preferenceItens.add(this.getResources()
				.getText(R.string.preferences_vibrate).toString());
		this.preferenceItens.add(this.getResources()
				.getText(R.string.preferences_rimshot).toString());
		this.preferenceItens.add(this.getResources()
				.getText(R.string.preferences_crash_to_china).toString());
		this.preferenceItens.add(this.getResources()
				.getText(R.string.preferences_kick_2_to).toString());
		this.listView.setAdapter(new PreferencesActivity.Adapter(this,
				R.layout.preferences_row, this.preferenceItens));
	}

	public class Adapter extends ArrayAdapter {

		public Adapter(Context var2, int var3, List var4) {
			super(var2, var3, var4);
		}

		public View getCustomView(int i, ViewGroup viewgroup) {
			View view = PreferencesActivity.this.getLayoutInflater().inflate(
					R.layout.preferences_row, viewgroup, false);
			ImageView imageview = (ImageView) view.findViewById(R.id.imageView);
			((TextView) view.findViewById(R.id.textView))
					.setText((CharSequence) PreferencesActivity.this.preferenceItens
							.get(i));
			final TextView textview = (TextView) view
					.findViewById(R.id.summaryView);
			final CheckBox checkbox = (CheckBox) view
					.findViewById(R.id.checkBox);
			switch (i) {
			case 0:
				imageview.setImageResource(R.drawable.animation);
				((LinearLayout) textview.getParent()).removeView(textview);
				checkbox.setChecked(Preferences.isAnimado());
				checkbox.setOnClickListener(new OnClickListener() {
					public void onClick(View var1) {
						Preferences.setAnimado(checkbox.isChecked());
					}
				});
				return view;
			case 1:
				imageview.setImageResource(R.drawable.repeat);
				((LinearLayout) textview.getParent()).removeView(textview);
				checkbox.setChecked(Preferences.isRepeat());
				checkbox.setOnClickListener(new OnClickListener() {
					public void onClick(View var1) {
						Preferences.setRepeat(checkbox.isChecked());
					}
				});
				return view;
			case 2:
				imageview.setImageResource(R.drawable.vibrate);
				((LinearLayout) textview.getParent()).removeView(textview);
				checkbox.setChecked(Preferences.isVibrate());
				checkbox.setOnClickListener(new OnClickListener() {
					public void onClick(View var1) {
						Preferences.setVibrate(checkbox.isChecked());
					}
				});
				return view;
			case 3:
				imageview.setImageResource(R.drawable.snare);
				((LinearLayout) textview.getParent()).removeView(textview);
				checkbox.setChecked(Preferences.isRimshot());
				checkbox.setOnClickListener(new OnClickListener() {
					public void onClick(View var1) {
						Preferences.setRimshot(checkbox.isChecked());
					}
				});
				return view;
			case 4:
				imageview.setImageResource(R.drawable.crash);
				((LinearLayout) textview.getParent()).removeView(textview);
				checkbox.setChecked(Preferences.isCrash2ToChina());
				checkbox.setOnClickListener(new OnClickListener() {
					public void onClick(View var1) {
						Preferences.setCrash2ToChina(checkbox.isChecked());
					}
				});
				return view;
			case 5:
				imageview.setImageResource(R.drawable.kick);
				((LinearLayout) checkbox.getParent()).removeView(checkbox);
				textview.setText(PreferencesActivity.this.changeKickList[Preferences
						.getKick2To()]);
				view.setOnClickListener(new OnClickListener() {
					public void onClick(View var1) {
						Builder var2 = new Builder(PreferencesActivity.this);
						var2.setTitle(R.string.preferences_kick_2_to);
						var2.setSingleChoiceItems(
								PreferencesActivity.this.changeKickList,
								Preferences.getKick2To(),
								new android.content.DialogInterface.OnClickListener() {
									public void onClick(DialogInterface var1,
											int var2) {
										var1.dismiss();
										Preferences.setKick2To(var2);
										textview.setText(PreferencesActivity.this.changeKickList[Preferences
												.getKick2To()]);
									}
								});
						var2.create().show();
					}
				});
				return view;
			default:
				return view;
			}
		}

		public View getDropDownView(int var1, View var2, ViewGroup var3) {
			return this.getCustomView(var1, var3);
		}

		public View getView(int var1, View var2, ViewGroup var3) {
			return this.getCustomView(var1, var3);
		}
	}
}
