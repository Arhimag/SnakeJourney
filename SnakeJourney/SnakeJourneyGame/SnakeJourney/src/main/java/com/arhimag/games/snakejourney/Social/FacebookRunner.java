package com.arhimag.games.snakejourney.Social;

/**
 * Created by Arhimag on 09.01.15.
 *
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import com.arhimag.games.snakejourney.GameLevelDrawer;
import com.arhimag.games.snakejourney.LevelSequence;
import com.arhimag.games.snakejourney.Maps.EggsWindowMap;
import com.arhimag.games.snakejourney.Maps.GameMap;
import com.arhimag.games.snakejourney.Screens.GameScreen;
import com.arhimag.games.snakejourney.framework.impl.AndroidGame;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.vk.sdk.VKUIHelper;

import java.util.ArrayList;
import java.util.List;

public class FacebookRunner implements Runnable
{
    private static int bitmapHeight = 295;
    private static int bitmapWidth = 720;
    private static int bitmapTextHeight = 185;
    final int bitmapPaddingLeft = 10;
    final int bitmapPaddingTop = 10;

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

    private final Bitmap spamBitmap = Bitmap.createBitmap( bitmapWidth, bitmapHeight + bitmapTextHeight, Bitmap.Config.ARGB_8888);

    private void drawWaterMark( Canvas cnv )
    {
        Paint feather = new Paint();

        int watermarkPixelSizeWidth = (bitmapWidth - 2 * bitmapPaddingLeft )/waterMark[0].length ;
        int watermarkPixelSizeHeight = (bitmapTextHeight - 2 * bitmapPaddingTop )/waterMark.length;


        int startDrawX = bitmapPaddingLeft + ( bitmapWidth - bitmapPaddingLeft - watermarkPixelSizeWidth * waterMark[0].length ) / 2;
        int startDrawY = bitmapHeight + bitmapPaddingTop + ( bitmapTextHeight - watermarkPixelSizeHeight * waterMark.length ) / 2;

        feather.setColor(waterMarkTextColor);
        feather.setStyle(Paint.Style.FILL);

        for(int i = 0; i < waterMark[0].length ; i++ )
            for(int j = 0; j < waterMark.length; j++)
                if( waterMark[j][i] == '#' )
                    cnv.drawRect(startDrawX + i * watermarkPixelSizeWidth, startDrawY + j * watermarkPixelSizeHeight, startDrawX + i * watermarkPixelSizeWidth + watermarkPixelSizeWidth - 1, startDrawY + j * watermarkPixelSizeHeight + watermarkPixelSizeHeight - 1, feather);

    }

    private void drawEgg( Canvas cnv, int x, int y, int eggPixelSize )
    {
        Paint feather = new Paint();
        for(int i = 0; i < EggsWindowMap.Egg[0].length ; i++ )
        {
            for (int j = 0; j < EggsWindowMap.Egg.length; j++)
            {
                feather.setColor(GameLevelDrawer.getColor(EggsWindowMap.Egg[j][i], j, i));
                feather.setStyle(Paint.Style.FILL);
                cnv.drawRect(x + i * eggPixelSize, y + j * eggPixelSize, x + i * eggPixelSize + eggPixelSize - 1, y + j * eggPixelSize + eggPixelSize - 1, feather);
            }
        }
    }

    private void fillImage()
    {
        Canvas spamCanvas = new Canvas(spamBitmap);
        Paint feather = new Paint();

        GameMap currentMap = LevelSequence.createMap( GameScreen.vkontaktePostingLevel );


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

    public static FacebookRunner fbRunner = new FacebookRunner();

    private Session.StatusCallback callback = new Session.StatusCallback() {

        @Override
        public void call(Session session, SessionState state, Exception exception) {

            if (state.isOpened()) {
                publishFeedDialog();
            }
        }
    };

    private void publishFeedDialog() {

        Bundle params = new Bundle();
        params.putString("name", "SnakeJourney");
        params.putString("caption", "Level " + Integer.toString(GameScreen.vkontaktePostingLevel + 1 ) );
        params.putString("description", "I did it! " + Integer.toString(GameScreen.vkontaktePostingEggs) + " Eggs! ");
        params.putString("link", "https://play.google.com/store/apps/details?id=" + GameScreen.vkontaktePostingApp);


        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(AndroidGame.faceBookActivityLink,
                        Session.getActiveSession(),
                        params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null)
                            {
                                new AlertDialog.Builder(VKUIHelper.getTopActivity())
                                        .setMessage("Posted story, id: " + postId)
                                        .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .show();
                            }
                            else
                            {
                                new AlertDialog.Builder(VKUIHelper.getTopActivity())
                                        .setMessage("Publish cancelled")
                                        .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .show();
                            }
                        }
                        else if (error instanceof FacebookOperationCanceledException)
                        {
                            new AlertDialog.Builder(VKUIHelper.getTopActivity())
                                    .setMessage("Publish cancelled")
                                    .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                                    .show();
                            // User clicked the "x" button
                        }
                        else
                        {
                            new AlertDialog.Builder(VKUIHelper.getTopActivity())
                                    .setMessage("Error posting story")
                                    .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                                    .show();
                        }
                    }

                })
                .build();
        feedDialog.show();
    }


    public void run()
    {
        if (FacebookDialog.canPresentShareDialog(AndroidGame.faceBookActivityLink.getApplicationContext(),
                FacebookDialog.ShareDialogFeature.SHARE_DIALOG))
        {
            OpenGraphObject eggs = OpenGraphObject.Factory.createForPost("arhimagssnakejourney:eggs");
            OpenGraphAction finlev =  OpenGraphAction.Factory.createForPost("arhimagssnakejourney:finlev");

            fillImage();
            List<Bitmap> images = new ArrayList<>();
            images.add( spamBitmap );


            eggs.setTitle("Level " + Integer.toString(GameScreen.vkontaktePostingLevel + 1));
            eggs.setUrl("https://play.google.com/store/apps/details?id=" + GameScreen.vkontaktePostingApp);
            eggs.setDescription("Level " + Integer.toString(GameScreen.vkontaktePostingLevel + 1) + "  I did it! " + Integer.toString(GameScreen.vkontaktePostingEggs) + " Eggs! ");



            finlev.setProperty("eggs", eggs);

            FacebookDialog shareDialog = new FacebookDialog.OpenGraphActionDialogBuilder(AndroidGame.faceBookActivityLink, finlev, "eggs")
                    .setImageAttachmentsForAction(images,true)
                    .build();

            AndroidGame.uiHelper.trackPendingDialogCall(shareDialog.present());
        }
        else
        {
            if (Session.getActiveSession() == null || !Session.getActiveSession().isOpened()) {
                Session.openActiveSession(AndroidGame.faceBookActivityLink, true, callback);
            } else {
                publishFeedDialog();
            }
        }
    }

}
