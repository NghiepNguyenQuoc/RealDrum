package com.white.realdrum.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class IabHelper {
	public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
	public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
	public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
	public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
	public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
	public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
	public static final int BILLING_RESPONSE_RESULT_OK = 0;
	public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
	public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
	public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
	public static final int IABHELPER_BAD_RESPONSE = -1002;
	public static final int IABHELPER_ERROR_BASE = -1000;
	public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
	public static final int IABHELPER_MISSING_TOKEN = -1007;
	public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
	public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
	public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
	public static final int IABHELPER_UNKNOWN_ERROR = -1008;
	public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
	public static final int IABHELPER_USER_CANCELLED = -1005;
	public static final int IABHELPER_VERIFICATION_FAILED = -1003;
	public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
	public static final String ITEM_TYPE_INAPP = "inapp";
	public static final String ITEM_TYPE_SUBS = "subs";
	public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
	public static final String RESPONSE_CODE = "RESPONSE_CODE";
	public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
	public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
	public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
	public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
	public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
	public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
	boolean mAsyncInProgress = false;
	String mAsyncOperation = "";
	Context mContext;
	boolean mDebugLog = false;
	String mDebugTag = "IabHelper";
	String mPurchasingItemType;
	int mRequestCode;
	ServiceConnection mServiceConn;
	boolean mSetupDone = false;
	String mSignatureBase64 = null;
	boolean mSubscriptionsSupported = false;

	public IabHelper(Context paramContext, String paramString) {
		mContext = paramContext.getApplicationContext();
		mSignatureBase64 = paramString;
	}

	public static String getResponseDesc(int paramInt) {
		String[] arrayOfString1 = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned"
				.split("/");
		String[] arrayOfString2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt"
				.split("/");
		if (paramInt <= -1000) {
			int i = -1000 - paramInt;
			if ((i >= 0) && (i < arrayOfString2.length))
				return arrayOfString2[i];
			return String.valueOf(paramInt) + ":Unknown IAB Helper Error";
		}
		if ((paramInt < 0) || (paramInt >= arrayOfString1.length))
			return String.valueOf(paramInt) + ":Unknown";
		return arrayOfString1[paramInt];
	}

	void checkSetupDone(String paramString) {
		if (!mSetupDone) {
			throw new IllegalStateException(
					"IAB helper is not set up. Can't perform operation: "
							+ paramString);
		}
	}

}