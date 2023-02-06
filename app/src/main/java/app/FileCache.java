package app;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileCache {

	private File cacheDir;

	public FileCache(Context context) {
		// Find the dir to save cached images
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
	cacheDir = new File(Environment.getExternalStorageDirectory(),"NRM");
		else 
			cacheDir = context.getCacheDir();
		if (!cacheDir.exists())
			cacheDir.mkdirs();
	}

	public File getFile(String url) {
		 Log.d("getFile is decoded ", url+"");
		 int lastIndexOf = url.lastIndexOf("/");
		 String substring = url.substring(lastIndexOf+1);
		 
		String filename = String.valueOf(url.hashCode());
		// String filename = URLEncoder.encode(url);
		 Log.d("filename is decoded ", substring+"");
		 Log.d("filename is cacheDir ", cacheDir+"");
		File f = new File(cacheDir, substring);
		return f;

	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		if (files == null)
			return;
		for (File f : files)
			f.delete();
	}

}