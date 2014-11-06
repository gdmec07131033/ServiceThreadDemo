package cn.edu.gdmec.s07131033.servicethreaddemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceThread extends Service {
	private Runnable backgroundWork = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (!Thread.interrupted()) {
					double randomDouble = Math.random();
					MainActivity.UpdateGUI(randomDouble);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	};
	private Thread WorkThread;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "The Service Has Created.", 1000).show();
		WorkThread = new Thread(null, backgroundWork, "Workthread");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "The Service Has Stopped.", 1000).show();
		WorkThread.interrupt();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "The Service Has Started.", 1000).show();
		if (!WorkThread.isAlive()) {
			WorkThread.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}

}
