package com.arhimag.games.SnakeJourney.Social;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.arhimag.games.SnakeJourney.GameLevelDrawer;
import com.arhimag.games.SnakeJourney.LevelSequence;
import com.arhimag.games.SnakeJourney.Levels.GameLevel;
import com.arhimag.games.SnakeJourney.Maps.EggsWindowMap;
import com.arhimag.games.SnakeJourney.Maps.GameMap;
import com.arhimag.games.SnakeJourney.Screens.GameScreen;
import com.arhimag.games.SnakeJourney.Settings;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKPhotoArray;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;

/**
 * Created by arhimag on 08.01.15.
 */
public class VKontakteAuth implements Runnable
{
    private static int bitmapHeight = 125;
    private static int bitmapWidth = 300;
    private static int bittextHeight = 75;
    private static int bitmapPaddingTop = 10;
    private static int bitmapPaddingLeft = 10;
    private static final char[][] waterMark = {
            {'#','#','#','.','.','.','#','#','.','.','#','#','#','.','#','#','.','.','.','.','#','#','#','.','#','#','#'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','#','.','.','.','.','#','.','#','.','.','#','.','.','#','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'#','#','#','.','.','.','#','#','.','.','#','#','#','.','#','#','.','.','.','.','#','#','#','.','.','#','.'}
    };

    private static final int waterMarkTextColor = 0xFF00FF00;

    private final Bitmap spamBitmap = Bitmap.createBitmap( bitmapWidth, bitmapHeight + bittextHeight, Bitmap.Config.ARGB_8888);

    private void drawWaterMark( Canvas cnv )
    {
        Paint feather = new Paint();

        int pixelsizeWidth = (bitmapWidth - 2 * bitmapPaddingLeft )/waterMark[0].length ;
        int pixelsizeHeight = (bittextHeight)/waterMark.length;


        int startDrawX = bitmapPaddingLeft + ( bitmapWidth - bitmapPaddingLeft - pixelsizeWidth * waterMark[0].length ) / 2;
        int startDrawY = bitmapHeight + ( bittextHeight - pixelsizeHeight * waterMark.length ) / 2;

        feather.setColor(waterMarkTextColor);
        feather.setStyle(Paint.Style.FILL);

        for(int i = 0; i < waterMark[0].length ; i++ )
            for(int j = 0; j < waterMark.length; j++)
                if( waterMark[j][i] == '#' )
                   cnv.drawRect(startDrawX + i * pixelsizeWidth, startDrawY + j * pixelsizeHeight, startDrawX + i * pixelsizeWidth + pixelsizeWidth - 1, startDrawY + j * pixelsizeHeight + pixelsizeHeight - 1, feather);

    }

    private void drawEgg( Canvas cnv, int x, int y, int pixelsize )
    {
        Paint feather = new Paint();
        for(int i = 0; i < EggsWindowMap.Egg[0].length ; i++ )
            for(int j = 0; j < EggsWindowMap.Egg.length; j++)
            {
                feather.setColor(GameLevelDrawer.getColor(EggsWindowMap.Egg[j][i], j, i));
                feather.setStyle(Paint.Style.FILL);
                cnv.drawRect(x + i * pixelsize, y + j * pixelsize, x + i * pixelsize + pixelsize - 1,y + j * pixelsize + pixelsize - 1, feather);
            }
    }

    private void fillImage()
    {
        Canvas spamCanvas = new Canvas(spamBitmap);
        Paint feather = new Paint();

        GameMap currentMap = LevelSequence.createMap( GameScreen.vkontaktePostingLevel );

        //Рисуем карту уровня.
        int pixelSize = java.lang.Math.min((bitmapHeight - bitmapPaddingTop)/currentMap.getMapHeight(), (bitmapWidth - bitmapPaddingLeft )/currentMap.getMapWidth() );

        int startDrawX = bitmapPaddingLeft + ( bitmapWidth - bitmapPaddingLeft - pixelSize * currentMap.getMapWidth() ) / 2;
        int startDrawY = bitmapPaddingTop + ( bitmapHeight - bitmapPaddingTop - pixelSize * currentMap.getMapHeight() ) / 2;

        if( !currentMap.isColorsInited())
            currentMap.initFlatMapColor();
        for(int i = 0; i < currentMap.getMapWidth() ; i++ )
            for(int j=0; j < currentMap.getMapHeight(); j++)
            {
                feather.setColor(currentMap.getFlatMapColor(i, j));
                feather.setStyle(Paint.Style.FILL);
                spamCanvas.drawRect(startDrawX + i * pixelSize,startDrawY + j * pixelSize,startDrawX + i * pixelSize + pixelSize - 1,startDrawY + j * pixelSize + pixelSize - 1, feather);
            }

        int eggPixelSize = (2 * pixelSize) / 3;
        if (GameScreen.vkontaktePostingEggs > 0)
        {
            feather.setColor(0xFF000000);
            feather.setStyle(Paint.Style.FILL);
            spamCanvas.drawRect(startDrawX + currentMap.getMapWidth() * pixelSize - EggsWindowMap.Egg[0].length * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize, startDrawX + currentMap.getMapWidth() * pixelSize - EggsWindowMap.Egg[0].length * eggPixelSize + EggsWindowMap.Egg[0].length * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize + EggsWindowMap.Egg.length * eggPixelSize, feather);
            drawEgg(spamCanvas, startDrawX + currentMap.getMapWidth() * pixelSize - (EggsWindowMap.Egg[0].length ) * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize, eggPixelSize);
        }
        if (GameScreen.vkontaktePostingEggs > 1)
        {
            feather.setColor(0xFF000000);
            feather.setStyle(Paint.Style.FILL);
            spamCanvas.drawRect(startDrawX + currentMap.getMapWidth() * pixelSize - (2 * EggsWindowMap.Egg[0].length + 1 ) * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize, startDrawX + currentMap.getMapWidth() * pixelSize - ( 2 * EggsWindowMap.Egg[0].length + 1 ) *  eggPixelSize + EggsWindowMap.Egg[0].length * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize + EggsWindowMap.Egg.length * eggPixelSize, feather);
            drawEgg(spamCanvas, startDrawX + currentMap.getMapWidth() * pixelSize - (2 * EggsWindowMap.Egg[0].length + 1) * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize, eggPixelSize);
        }
        if (GameScreen.vkontaktePostingEggs > 2)
        {
            feather.setColor(0xFF000000);
            feather.setStyle(Paint.Style.FILL);
            spamCanvas.drawRect(startDrawX + currentMap.getMapWidth() * pixelSize - (3 * EggsWindowMap.Egg[0].length + 2 ) * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize, startDrawX + currentMap.getMapWidth() * pixelSize - ( 3 * EggsWindowMap.Egg[0].length + 2 ) *  eggPixelSize + EggsWindowMap.Egg[0].length * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize + EggsWindowMap.Egg.length * eggPixelSize, feather);
            drawEgg(spamCanvas, startDrawX + currentMap.getMapWidth() * pixelSize - (3 * EggsWindowMap.Egg[0].length + 2 ) * eggPixelSize, startDrawY + currentMap.getMapHeight() * pixelSize - EggsWindowMap.Egg.length * eggPixelSize, eggPixelSize);
        }

        drawWaterMark(spamCanvas);
    }

    public void run()
    {
        GameScreen.vkontakteOperationState = VKontakteListener.NO_ACCESS;

        while ( GameScreen.vkontakteOperationState == VKontakteListener.NO_ACCESS )
        {
            // Пытаемся восстановить старую сессию.
            if (VKSdk.wakeUpSession())
            {
                if (!VKSdk.isLoggedIn())
                {
                    synchronized (VKontakteListener.mutex)
                    {
                        VKontakteListener.setState(VKontakteListener.WAITING);
                    }
                    VKSdk.authorize(Settings.vkontaktePrivates);
                }
                else
                {
                    VKontakteListener.setState(VKontakteListener.ACCEESS);
                }
            }
            else
            {
                if (!VKSdk.isLoggedIn())
                {
                    synchronized (VKontakteListener.mutex)
                    {
                        VKontakteListener.setState(VKontakteListener.WAITING);
                    }
                    VKSdk.authorize(Settings.vkontaktePrivates);
                }
                else
                {
                    VKontakteListener.setState(VKontakteListener.ACCEESS);
                }
            }

            while (VKontakteListener.getState() == VKontakteListener.WAITING)
                synchronized (VKontakteListener.mutex)
                {
                    try
                    {
                        VKontakteListener.mutex.wait();
                    } catch (Exception e)
                    {
                        Log.d("SnakeJourney", e.toString());
                    }
                }

            if (VKontakteListener.getState() == VKontakteListener.ACCEESS)
                try
                {
                    fillImage();
                    VKRequest request = VKApi.uploadWallPhotoRequest(new VKUploadImage(spamBitmap, VKImageParameters.jpgImage(0.9f)), Long.parseLong(VKSdk.getAccessToken().userId), 0);

                    GameScreen.vkontakteOperationState = VKontakteListener.WAITING;

                    request.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            spamBitmap.recycle();

                            VKApiPhoto photoModel = ((VKPhotoArray) response.parsedModel).get(0);

                            VKRequest post = VKApi.wall().post(VKParameters.from(VKApiConst.USER_ID, VKSdk.getAccessToken().userId, VKApiConst.ATTACHMENTS, new VKAttachments(photoModel), VKApiConst.MESSAGE, "Snake Journey. Level " + Integer.toString(GameScreen.vkontaktePostingLevel + 1 ) + " \n " + "https://play.google.com/store/apps/details?id=" + GameScreen.vkontaktePostingApp));

                            post.executeWithListener(new VKRequest.VKRequestListener()
                            {
                                @Override
                                public void onComplete(VKResponse response)
                                {
                                    super.onComplete(response);
                                    new AlertDialog.Builder(VKUIHelper.getTopActivity())
                                            .setMessage("Posted!")
                                            .setNegativeButton("Ok!",new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            })
                                            .show();
                                    GameScreen.vkontakteOperationState = VKontakteListener.ACCEESS;
                                }

                                @Override
                                public void onError(VKError error)
                                {
                                    VKSdk.logout();
                                    GameScreen.vkontakteOperationState = VKontakteListener.NO_ACCESS;
                                }
                            });
                        }

                        @Override
                        public void onError(VKError error) {
                            VKSdk.logout();
                            GameScreen.vkontakteOperationState = VKontakteListener.NO_ACCESS;
                        }
                    });

                    while (GameScreen.vkontakteOperationState == VKontakteListener.WAITING)
                        try
                        {
                            Thread.sleep(1000);
                        } catch (Exception e)
                        {
                            Log.d("SnakeJourney", e.toString());
                        }
                }
                catch (Exception e)
                {
                    Log.d("SnakeJourney", e.toString());
                }
        }
    }
}
