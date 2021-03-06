package com.zhuruqiao.baseutils.utils;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.x91tec.appshelf.converter.TextUtils;

/**
 * 打开或关闭软键盘
 * 
 * @author zhy
 * 
 */
public class KeyBoardUtils
{
	/**
	 * 打开软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}
	public static void toogleSoftKeyboard(Context context) {
		((InputMethodManager)context.getSystemService(
				Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static void showSoftKeyboard(Dialog dialog) {
		dialog.getWindow().setSoftInputMode(4);
	}

	public static void copyTextToBoard(Context mContext,String string) {
		if (TextUtils.isEmpty(string))
			return;
		ClipboardManager clip = (ClipboardManager) mContext
				.getSystemService(Context.CLIPBOARD_SERVICE);
		clip.setPrimaryClip(ClipData.newPlainText(null, string));
	}
}
