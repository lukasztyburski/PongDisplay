package com.pongdisplay;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.net.wifi.*;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;

import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ConnectivityActivity extends Activity {

    public class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            sb = new StringBuilder();
            wifiList = wifiManager.getScanResults();

            sb.append("\nWiFi Connection:" + wifiList.size() + "\n\n:");

            for (int i = 0; i < wifiList.size(); i++) {
                sb.append(new Integer(i + 1).toString());
                sb.append(wifiList.get(i).toString());
                sb.append("\n\n");
            }
            tv.setText(sb);
        }
    }

    TextView tv;
    WifiManager wifiManager;
    WifiReceiver wifiReceiver;
    List<ScanResult> wifiList;
    StringBuilder sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        tv = (TextView) findViewById(R.id.test_textView);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);


        if (wifiManager.isWifiEnabled() == false) {
            Toast.makeText(getApplicationContext(), "WiFi is disabled", Toast.LENGTH_LONG);
            wifiManager.setWifiEnabled(true);
        }

        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        wifiManager.startScan();
        tv.setText("Starting scan");
    }


    @Override
    protected void onResume() {
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(wifiReceiver);
        super.onPause();
    }
}