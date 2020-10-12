package com.example.diabetescarelink;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static final String PREF_CONTACT= "contact";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefContact(Context ctx, String contact)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_CONTACT, contact);
        editor.commit();
    }

    public static String getPrefContact(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_CONTACT, "");
    }
}
