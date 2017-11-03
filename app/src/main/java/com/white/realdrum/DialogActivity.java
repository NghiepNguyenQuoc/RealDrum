package com.white.realdrum;

import com.example.realdrum.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogActivity extends Activity {

   protected Button buttonClose;
   protected Button buttonEdit;
   protected ListView listView;
   protected TextView textTitle;
   protected RelativeLayout transparentLayout;
   protected LinearLayout whiteLayout;


   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(R.layout.dialog);
      this.textTitle = (TextView)this.findViewById(R.id.textTitle);
      this.buttonClose = (Button)this.findViewById(R.id.buttonClose);
      this.buttonEdit = (Button)this.findViewById(R.id.buttonEdit);
      this.transparentLayout = (RelativeLayout)this.findViewById(R.id.transparentLayout);
      this.whiteLayout = (LinearLayout)this.findViewById(R.id.whiteLayout);
      this.listView = (ListView)this.findViewById(R.id.listView);
      this.buttonClose.setOnClickListener(new OnClickListener() {
         public void onClick(View var1) {
            DialogActivity.this.finish();
         }
      });
      this.transparentLayout.setOnTouchListener(new OnTouchListener() {
         public boolean onTouch(View var1, MotionEvent var2) {
            DialogActivity.this.finish();
            return false;
         }
      });
      this.whiteLayout.setOnTouchListener(new OnTouchListener() {
         public boolean onTouch(View var1, MotionEvent var2) {
            return true;
         }
      });
   }
}
