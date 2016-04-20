package com.hai.vocalockscreen2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by HP on 4/8/2016.
 */

public class DisplayService extends Service{

    WindowManager window;
    TextView voca, phienamText;
    Button one, two, three, four;
    String[] tu;
    String[] nghia;
    String[] phienam;
    int dapanDung;
    int tuIndex;
    int[] nghiaIndex;
    public final int SIZE_OF_VOCA = 200;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        window = (WindowManager)getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.x = 0;
        params.y = 0;
        params.gravity = Gravity.CENTER| Gravity.CENTER;

        LayoutInflater li = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        final RelativeLayout theme = (RelativeLayout)li.inflate(R.layout.voca_lockscreen, null);
        window.addView(theme, params);

        Random r = new Random();
        int index = r.nextInt(5);

        switch (index){
            case 0:
                theme.setBackgroundResource(R.drawable.theme1);
                break;
            case 1:
                theme.setBackgroundResource(R.drawable.theme2);
                break;
            case 2:
                theme.setBackgroundResource(R.drawable.theme3);
                break;
            case 3:
                theme.setBackgroundResource(R.drawable.theme4);
                break;
            case 4:
                theme.setBackgroundResource(R.drawable.theme5);
                break;

        }

        tu = getResources().getStringArray(R.array.tu);
        nghia = getResources().getStringArray(R.array.nghia);
        phienam = getResources().getStringArray(R.array.phienam);


        voca = (TextView)theme.findViewById(R.id.voca);
        phienamText = (TextView)theme.findViewById(R.id.phienam);
        one = (Button)theme.findViewById(R.id.one);
        two = (Button)theme.findViewById(R.id.two);
        three = (Button)theme.findViewById(R.id.three);
        four = (Button)theme.findViewById(R.id.four);

        one.setBackgroundResource(R.drawable.button);
        two.setBackgroundResource(R.drawable.button);
        three.setBackgroundResource(R.drawable.button);
        four.setBackgroundResource(R.drawable.button);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[0] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[1] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[2] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nghiaIndex[3] == tuIndex){
                    window.removeView(theme);
                    stopSelf();
                } else{
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(300);
                }
            }
        });

        //tao ngau nhien 1 tu
        r = new Random();
        tuIndex = r.nextInt(SIZE_OF_VOCA);
        voca.setText(tu[tuIndex]);
        phienamText.setText(phienam[tuIndex]);

        //tao ngau nhien dap an dung
        r = new Random();
        dapanDung = r.nextInt(4);
        nghiaIndex = new int[SIZE_OF_VOCA];
        nghiaIndex[dapanDung] = tuIndex;

        //tao cac dap an sai
        int[] temp = new int[3];
        temp[0] = rdNumber(tuIndex, tuIndex, tuIndex, SIZE_OF_VOCA);
        temp[1] = rdNumber(tuIndex, tuIndex, temp[0], SIZE_OF_VOCA);
        temp[2] = rdNumber(tuIndex, temp[0], temp[1], SIZE_OF_VOCA);
        int k = 0;
        for(int i = 0; i < 4; i++){
            if(i != dapanDung){
                nghiaIndex[i] = temp[k];
                k++;
            }
        }

        //hien 4 dap an
        one.setText(nghia[nghiaIndex[0]]);
        two.setText(nghia[nghiaIndex[1]]);
        three.setText(nghia[nghiaIndex[2]]);
        four.setText(nghia[nghiaIndex[3]]);
    }

    int rdNumber(int num1, int num2, int num3, int size){
        Random r = new Random();
        int num = r.nextInt(size);
        while (num == num1 || num == num2 || num == num3){
            r = new Random();
            num = r.nextInt(size);
        }
        return num;
    }

}

