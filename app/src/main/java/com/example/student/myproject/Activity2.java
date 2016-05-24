package com.example.student.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    //設定全域變數讓其他方法共同使用
    private TextView quautityTextView;
    private TextView priceTextView;
    private Button orderButton;
    private CheckBox bagCheakBox;
    private RadioButton radioButton1, radioButton2;
    //套餐價錢的資訊
    private int[] mComboCount = {0, 0, 0, 0, 0, 0, 0, 0};
    private int[] mComboPrice = {129, 129, 129, 119, 119, 99, 99, 99};
    private int[] mComboSubtotalPrice = {0, 0, 0, 0, 0, 0, 0, 0};
    int mComboTotalPrice = 0;
    //套餐內容字串的資訊
    private int[] mComboNameXML = {R.string.comboName1, R.string.comboName2, R.string.comboName3, R.string.comboName4,
            R.string.comboName5, R.string.comboName6, R.string.comboName7, R.string.comboName8};
    private String[] mComboName = {"", "", "", "", "", "", "", ""};
    private String mComboTotalName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        findViews();
    }

    //藉由id找尋View元件使用
    private void findViews() {
        quautityTextView = (TextView) findViewById(R.id.quantity_text_view);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
        orderButton = (Button) findViewById(R.id.order);
        bagCheakBox = (CheckBox) findViewById(R.id.checkBox);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
    }

    //依點擊數判斷餐點數量
    public void getComboCount(View view) {
        switch (view.getId()) {
            case R.id.hit1:
                addComboCount(0);
                break;
            case R.id.hit2:
                addComboCount(1);
                break;
            case R.id.hit3:
                addComboCount(2);
                break;
            case R.id.hit4:
                addComboCount(3);
                break;
            case R.id.hit5:
                addComboCount(4);
                break;
            case R.id.hit6:
                addComboCount(5);
                break;
            case R.id.hit7:
                addComboCount(6);
                break;
            case R.id.hit8:
                addComboCount(7);
                break;
            case R.id.removeHit1:
                removeComboCount(0);
                break;
            case R.id.removeHit2:
                removeComboCount(1);
                break;
            case R.id.removeHit3:
                removeComboCount(2);
                break;
            case R.id.removeHit4:
                removeComboCount(3);
                break;
            case R.id.removeHit5:
                removeComboCount(4);
                break;
            case R.id.removeHit6:
                removeComboCount(5);
                break;
            case R.id.removeHit7:
                removeComboCount(6);
                break;
            case R.id.removeHit8:
                removeComboCount(7);
                break;
            case R.id.checkBox:
            case R.id.radioButton1:
            case R.id.radioButton2:
                break;
        }
        display();
    }

    //+1點餐數量資訊
    private void addComboCount(int i) {
        ++mComboCount[i];
        mComboSubtotalPrice[i] = mComboCount[i] * mComboPrice[i];
        mComboName[i] = mComboCount[i] == 0 ? " " : getString(mComboNameXML[i]) + mComboCount[i] + "     " + mComboSubtotalPrice[i];
    }

    //-1點餐數量資訊
    private void removeComboCount(int i) {
        if (mComboCount[i] > 0) {
            --mComboCount[i];
            mComboSubtotalPrice[i] = mComboCount[i] * mComboPrice[i];
            mComboName[i] = mComboCount[i] == 0 ? " " : getString(mComboNameXML[i]) + mComboCount[i] + "     " + mComboSubtotalPrice[i];
        }
    }
    //點餐結帳 輸出點餐清單
    public void submit(View view) {

        StringBuilder message = new StringBuilder();
        message.append("您的訂單如下:\n\n")
                .append(mComboTotalName)
                .append("\n")
                .append("\n")
                .append("\n")
                .append("您的總金額為:" + " " + mComboTotalPrice + " " + "$");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("確定結帳", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Activity2.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("返回點餐", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();

    }
    private void display() {
        String bagMessage = "";
        int bagPrice = 0;
        if (radioButton1.isChecked()) {
            bagCheakBox.setEnabled(false);
        } else if (radioButton2.isChecked()) {
            bagCheakBox.setEnabled(true);
            bagMessage = bagCheakBox.isChecked() == true ? getString(R.string.bag) : "";
            bagPrice = bagCheakBox.isChecked() == true ? 2 : 0;
        }

        mComboTotalName = mComboName[0] + mComboName[1] + mComboName[2] + mComboName[3] +
                mComboName[4] + mComboName[5] + mComboName[6] + mComboName[7] + bagMessage;
        mComboTotalPrice = mComboSubtotalPrice[0] + mComboSubtotalPrice[1] + mComboSubtotalPrice[2] + mComboSubtotalPrice[3] +
                mComboSubtotalPrice[4] + mComboSubtotalPrice[5] + mComboSubtotalPrice[6] + mComboSubtotalPrice[7] + bagPrice;
        quautityTextView.setText(mComboTotalName);
        priceTextView.setText(String.valueOf(mComboTotalPrice));
    }

    //取消點餐
    public void cancel(View view) {
        mComboTotalPrice = 0;
        mComboTotalName = null;
        for (int i = 0; i < mComboCount.length; i++) {
            mComboCount[i] = 0;
            mComboSubtotalPrice[i] = 0;
            mComboName[i] = "";
        }
        quautityTextView.setText(mComboTotalName);
        priceTextView.setText(String.valueOf(mComboTotalPrice));
        bagCheakBox.setChecked(false);
        bagCheakBox.setEnabled(false);
        radioButton1.setChecked(true);
    }
}