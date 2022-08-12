package com.tianma.xsmscode.xp.hook.code.action.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.github.tianma8023.xposed.smscode.R;
import com.tianma.xsmscode.common.utils.XSPUtils;
import com.tianma.xsmscode.data.db.entity.SmsMsg;
import com.tianma.xsmscode.xp.hook.code.action.RunnableAction;

import de.robv.android.xposed.XSharedPreferences;

/**
 * 显示验证码Toast
 */
public class ToastAction extends RunnableAction {

    public ToastAction(Context pluginContext, Context phoneContext, SmsMsg smsMsg, XSharedPreferences xsp) {
        super(pluginContext, phoneContext, smsMsg, xsp);
    }

    @Override
    public Bundle action() {
        if (XSPUtils.shouldShowToast(xsp)) {
            showCodeToast();
        }
        return null;
    }

    private void showCodeToast() {
        String text = mPluginContext.getString(R.string.current_sms_code, mSmsMsg.getSmsCode());
        if (mPhoneContext != null) {
            Toast.makeText(mPhoneContext, text, Toast.LENGTH_LONG).show();
        }
    }
}
