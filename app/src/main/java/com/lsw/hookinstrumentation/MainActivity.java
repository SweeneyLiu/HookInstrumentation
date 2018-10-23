package com.lsw.hookinstrumentation;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject("android.app.Activity", this, "mInstrumentation");
        Instrumentation specialInstrumentation = new SpecialInstrumentation(mInstrumentation);

        RefInvoke.setFieldObject("android.app.Activity", this, "mInstrumentation", specialInstrumentation);

        Button tv = new Button(this);
        tv.setText("测试界面");
        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
