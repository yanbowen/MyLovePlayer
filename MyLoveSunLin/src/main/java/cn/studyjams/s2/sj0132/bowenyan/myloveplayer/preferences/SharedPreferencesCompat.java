package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.preferences;

import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yanbowen on 4/21/2017.
 */

public class SharedPreferencesCompat {
    private static final Method sApplyMethod = findApplyMethod();

    private static Method findApplyMethod() {
        try {
            Class<SharedPreferences.Editor> cls = SharedPreferences.Editor.class;
            return cls.getMethod("apply");
        } catch (NoSuchMethodException unused) {
            //$FALL-THROUGH$
        }
        return null;
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (sApplyMethod != null) {
            try {
                sApplyMethod.invoke(editor);
                return;
            } catch (InvocationTargetException unused) {
                //$FALL-THROUGH$
            } catch (IllegalAccessException unused) {
                //$FALL-THROUGH$
            }
        }
        editor.commit();
    }
}