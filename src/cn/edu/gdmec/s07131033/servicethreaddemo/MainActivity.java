package cn.edu.gdmec.s07131033.servicethreaddemo;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	static TextView myTv;
	Button myBt1, myBt2;
	Intent myIt = new Intent("cn.edu.gdmec.servicethread");
	static Handler myHandler = new Handler();
	private static double randomDouble;
	private static Runnable Refreshable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			myTv.setText(String.valueOf(randomDouble));
		}
	};

	public static void UpdateGUI(double refreshDouble) {
		randomDouble = refreshDouble;
		myHandler.post(Refreshable);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myTv = (TextView) findViewById(R.id.textView1);
		myBt1 = (Button) findViewById(R.id.button1);
		myBt2 = (Button) findViewById(R.id.button2);
		myBt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startService(myIt);

			}
		});
		myBt2.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				stopService(myIt);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
