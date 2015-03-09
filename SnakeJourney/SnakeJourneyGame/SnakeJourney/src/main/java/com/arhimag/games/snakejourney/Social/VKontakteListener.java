package com.arhimag.games.snakejourney.Social;

import android.app.AlertDialog;
import com.arhimag.games.snakejourney.Settings;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.dialogs.VKCaptchaDialog;

/**
 * Created by arhimag on 07.01.15.
 */
public class VKontakteListener extends VKSdkListener
{
    public static final int NO_ACCESS = 0;
    public static final int WAITING = 1;
    public static final int ACCEESS = 2;
    public static Object mutex = new Object();
    public static VKontakteAuth threadAuth = new VKontakteAuth();

    private static int state = NO_ACCESS;

    @Override
    public void onCaptchaError(VKError captchaError)
    {
        synchronized (mutex)
        {
            state = WAITING;
            new VKCaptchaDialog(captchaError).show();
            mutex.notifyAll();
        }
    }

    @Override
    public void onTokenExpired(VKAccessToken expiredToken)
    {
        synchronized (mutex)
        {
            state = WAITING;
            VKSdk.authorize(Settings.vkontaktePrivates);
            mutex.notifyAll();
        }
    }

    @Override
    public void onAccessDenied(final VKError authorizationError)
    {
        synchronized (mutex)
        {
            state = NO_ACCESS;
            new AlertDialog.Builder(VKUIHelper.getTopActivity())
                    .setMessage(authorizationError.toString())
                    .show();
            mutex.notifyAll();
        }
    }

    @Override
    public void onReceiveNewToken(VKAccessToken newToken)
    {
        synchronized (mutex)
        {
            state = ACCEESS;
            newToken.saveTokenToFile(Settings.vkontakteTokenFileName);
            mutex.notifyAll();
        }
    }

    @Override
    public void onAcceptUserToken(VKAccessToken token)
    {
        synchronized (mutex)
        {
            state = ACCEESS;
            token.saveTokenToFile(Settings.vkontakteTokenFileName);
            mutex.notifyAll();
        }
    }

    public static int getState() { return state; }

    public static void setState( int newState) {/* synchronized (mutex) { */state = newState; /*mutex.notifyAll(); }*/ }
}


