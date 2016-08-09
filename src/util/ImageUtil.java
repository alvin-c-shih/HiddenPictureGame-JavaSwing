package util;
import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

import javax.swing.ImageIcon;


public class ImageUtil {
	public static ImageIcon getImageIcon0() {
		URL bgUrl=ImageUtil.class.getResource("img.jpg");
		return new ImageIcon(bgUrl);
	}
	
	public static final char sep=File.separatorChar;
	
	private static Random random=new Random(Calendar.getInstance().getTimeInMillis());

	public static ImageIcon getImageIcon()
	throws MalformedURLException
	{
		
		URL bgDirUrl=ImageUtil.class.getResource("/");
		File dir=new File(bgDirUrl.getFile()+sep+".."+sep+"resources");
		FilenameFilter filter=new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".jpg");
			}
		};
		File[] files=dir.listFiles(filter);
		final int n=random.nextInt(files.length);
		
		URL bgUrl=files[n].toURI().toURL();
		return new ImageIcon(bgUrl);
	}

}
