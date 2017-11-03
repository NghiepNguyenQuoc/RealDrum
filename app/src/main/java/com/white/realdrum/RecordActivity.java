package com.white.realdrum;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.realdrum.R;
import com.white.realdrum.DialogActivity;
import com.white.realdrum.RecordManager;

public class RecordActivity extends DialogActivity {

	private boolean isEditing = false;
	private RecordManager recordManager;
	private String[] records;

	private void showEditList() {
		this.buttonEdit.setText(R.string.dialog_ok);
		this.listView.setAdapter(new RecordActivity.EditAdapter(this,
				R.layout.record_edit_row, this.records));
		this.listView.setOnItemClickListener((OnItemClickListener) null);
		if (this.records.length == 0) {
			this.finish();
		}

	}

	private void showPlayList() {
		this.buttonEdit.setText(R.string.dialog_edit);
		this.listView.setAdapter(new RecordActivity.PlayAdapter(this,
				R.layout.record_play_row, this.records));
		//Xu ly su kien play example
		this.listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView var1, View var2, int var3,
					long var4) {
				RecordActivity.this.recordManager
						.playFile(RecordActivity.this.records[var3]);
				RecordActivity.this.finish();
			}
		});
		if (this.records.length == 0) {
			this.finish();
		}

	}

	public void onCreate(Bundle var1) {
		super.onCreate(var1);
		this.textTitle.setText(R.string.record_record);
		this.buttonEdit.setOnClickListener(new OnClickListener() {
			public void onClick(View var1) {
				RecordActivity var2 = RecordActivity.this;
				boolean var3;
				if (!RecordActivity.this.isEditing) {
					var3 = true;
				} else {
					var3 = false;
				}

				var2.isEditing = var3;
				if (RecordActivity.this.isEditing) {
					RecordActivity.this.showEditList();
				} else {
					RecordActivity.this.showPlayList();
				}
			}
		});
		//Lay danh sach RecordList da ghi am
		this.recordManager = RecordManager.getInstance();
		this.records = this.recordManager.getRecordsList();
		this.showPlayList();
	}

	public class PlayAdapter extends ArrayAdapter {

		public PlayAdapter(Context var2, int var3, String[] var4) {
			super(var2, var3, var4);
		}

		public View getCustomView(int var1, ViewGroup var2) {
			View var3 = RecordActivity.this.getLayoutInflater().inflate(
					R.layout.record_play_row, var2, false);
			((TextView) var3.findViewById(R.id.textView))
					.setText(RecordActivity.this.records[var1]);
			return var3;
		}

		public View getDropDownView(int var1, View var2, ViewGroup var3) {
			return this.getCustomView(var1, var3);
		}

		public View getView(int var1, View var2, ViewGroup var3) {
			return this.getCustomView(var1, var3);
		}
	}

	public class EditAdapter extends ArrayAdapter {

		public EditAdapter(Context var2, int var3, String[] var4) {
			super(var2, var3, var4);
		}

		public View getCustomView(final int var1, ViewGroup var2) {
			View var3 = RecordActivity.this.getLayoutInflater().inflate(
					R.layout.record_edit_row, var2, false);
			((TextView) var3.findViewById(R.id.textView))
					.setText(RecordActivity.this.records[var1]);
			Button var4 = (Button) var3.findViewById(R.id.buttonRename);
			Button var5 = (Button) var3.findViewById(R.id.buttonDelete);
			var4.setOnClickListener(new OnClickListener() {
				public void onClick(View var1x) {
					Builder var2 = new Builder(RecordActivity.this);
					var2.setTitle(R.string.record_new_name);
					final EditText var4 = new EditText(RecordActivity.this);
					var4.setText(RecordActivity.this.records[var1]);
					var4.setInputType(1);
					var2.setView(var4);
					var2.setPositiveButton(
							R.string.dialog_ok,
							new android.content.DialogInterface.OnClickListener() {
								public void onClick(DialogInterface var1x,
										int var2) {
									String var3 = var4.getText().toString()
											.trim();
									if (!var3.equals("")
											&& var3.compareToIgnoreCase(RecordActivity.this.records[var1]) != 0) {
										String[] var4x = RecordActivity.this.records;
										int var5 = var4x.length;
										int var6 = 0;

										boolean var7;
										while (true) {
											var7 = false;
											if (var6 >= var5) {
												break;
											}

											if (var3.compareToIgnoreCase(var4x[var6]) == 0) {
												var7 = true;
												break;
											}

											++var6;
										}

										if (!var7) {
											RecordActivity.this.recordManager
													.renameFile(
															RecordActivity.this.records[var1],
															var3);
											RecordActivity.this.records = RecordActivity.this.recordManager
													.getRecordsList();
											RecordActivity.this.showEditList();
											return;
										}

										Toast.makeText(RecordActivity.this,
												R.string.record_name_alredy_in_use, 1).show();
									}

								}
							});
					var2.setNegativeButton(
							R.string.dialog_cancel,
							new android.content.DialogInterface.OnClickListener() {
								public void onClick(DialogInterface var1,
										int var2) {
									var1.cancel();
								}
							});
					var2.show();
				}
			});
			var5.setOnClickListener(new OnClickListener() {
				public void onClick(View var1x) {
					String var2 = RecordActivity.this.getResources().getString(
							R.string.record_record_delete_message);
					String var3 = RecordActivity.this.getResources().getString(
							R.string.dialog_yes);
					String var4 = RecordActivity.this.getResources().getString(
							R.string.dialog_no);
					AlertDialog var5 = (new Builder(RecordActivity.this))
							.create();
					var5.setTitle(R.string.app_name);
					var5.setMessage(var2);
					var5.setIcon(R.drawable.ic_launcher);
					var5.setButton(
							var3,
							new android.content.DialogInterface.OnClickListener() {
								public void onClick(DialogInterface var1x,
										int var2) {
									RecordActivity.this.recordManager
											.deleteFile(RecordActivity.this.records[var1]);
									var1x.dismiss();
									RecordActivity.this.records = RecordActivity.this.recordManager
											.getRecordsList();
									RecordActivity.this.showEditList();
								}
							});
					var5.setButton2(
							var4,
							new android.content.DialogInterface.OnClickListener() {
								public void onClick(DialogInterface var1,
										int var2) {
									var1.dismiss();
								}
							});
					var5.show();
				}
			});
			return var3;
		}

		public View getDropDownView(int var1, View var2, ViewGroup var3) {
			return this.getCustomView(var1, var3);
		}

		public View getView(int var1, View var2, ViewGroup var3) {
			return this.getCustomView(var1, var3);
		}
	}
}
