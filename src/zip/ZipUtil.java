package zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 用于上传索引压缩解压
 * 
 * @author yicha
 * 
 */
public class ZipUtil {
	static final int BUFFER = 2048;

	/**
	 * 递归压缩路径下所有文件 :不建议使用  因为要手动关闭 zos流
	 * @param inFile
	 * @param zos
	 * @param dir
	 * @throws IOException
	 */
	static void zipFile(File inFile, ZipOutputStream zos, String dir)
			throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file : files)
				zipFile(file, zos, dir + File.separator + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + File.separator + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}
	}
	
	 /**
     * 递归压缩文件
     * @param source 源路径,可以是文件,也可以目录
     * @param destinct  目标路径,压缩文件名
     * @throws IOException
     */
    private static void zipFile(String source,String destinct) throws IOException {
        List<File> fileList = loadFilename(new File(source));
        ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(new File(destinct)));
        
        byte[] buffere=new byte[8192];
        int length;
        BufferedInputStream bis;
        
        for(int i=0;i<fileList.size();i++) {
            File file=(File) fileList.get(i);
            zos.putNextEntry(new ZipEntry(getEntryName(source,file)));
            bis=new BufferedInputStream(new FileInputStream(file));
            
            while(true) {
                length=bis.read(buffere);
                if(length==-1) break;
                zos.write(buffere,0,length);
            }
            bis.close();
            zos.closeEntry();
        }
        zos.close();
    }
    
    /**
     * 递归获得该文件下所有文件名(不包括目录名)
     * @param file
     * @return
     */
    private static List<File> loadFilename(File file) {
        List<File> filenameList=new ArrayList<File>();
        if(file.isFile()) {
            filenameList.add(file);
        }
        if(file.isDirectory()) {
            for(File f:file.listFiles()) {
                filenameList.addAll(loadFilename(f));
            }
        }
        return filenameList;
    }
    /**
     * 获得zip entry 字符串
     * @param base
     * @param file
     * @return
     */
    private static String getEntryName(String base,File file) {
        File baseFile=new File(base);
        String filename=file.getPath();
        //int index=filename.lastIndexOf(baseFile.getName());
        if(baseFile.getParentFile().getParentFile()==null)
            return filename.substring(baseFile.getParent().length());
        return filename.substring(baseFile.getParent().length()+1);
    }



	 /**
     *   递归解压ZIP文件
     * @param zipFile  要解压的ZIP文件路径
     * @param destination  解压到哪里
     * @throws IOException
     */
    public static void unZipFile(String zipFile,String destination) throws IOException {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<?> en = zip.entries();
        ZipEntry entry=null;
        byte[] buffer=new byte[BUFFER];
        int length = -1;
        InputStream input=null;
        BufferedOutputStream bos=null;
        File file=null;
        
        while(en.hasMoreElements()) {
            entry = (ZipEntry)en.nextElement();
            if(entry.isDirectory()) {
                System.out.println("directory");
                continue;
            }
            
            input = zip.getInputStream(entry);
            file=new File(destination,entry.getName());
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            
            while(true) {
                length=input.read(buffer);
                if(length==-1) break;
                bos.write(buffer,0,length);
            }
            bos.close();
            input.close();
        }
        zip.close();
    }

	/**
	 * 只压缩下一层目录
	 * @param sourcePath
	 * @param desPath
	 */
	public static void zip(String sourcePath, String desPath) {
		try {
			BufferedInputStream bis = null;

			byte[] array = new byte[BUFFER];
			File f = new File(sourcePath);
			File file[] = f.listFiles();

			FileOutputStream fos = new FileOutputStream(desPath);
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(
					fos));

			for (int i = 0; i < file.length; i++) {
				FileInputStream fis = new FileInputStream(file[i]);
				bis = new BufferedInputStream(fis, BUFFER);
				ZipEntry zipEntry = new ZipEntry(file[i].getName());
				zos.putNextEntry(zipEntry);

				while ((bis.read(array, 0, BUFFER)) != -1) {
					zos.write(array, 0, BUFFER);
				}
				bis.close();
			}
			zos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 解压文件，只解压一层
	 * @param sourcePath
	 * @param desPath
	 */
	public static void unzip(String sourcePath, String desPath) {
		try {
			ZipFile zipFile = new ZipFile(sourcePath);
			Enumeration<?> enu = zipFile.entries();

			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();
				if (zipEntry.isDirectory()) {
					new File(desPath + zipEntry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile
						.getInputStream(zipEntry));
				File file = new File(desPath + zipEntry.getName());
				File parent = file.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);

				byte[] array = new byte[BUFFER];
				while ((bis.read(array, 0, BUFFER)) != -1) {
					bos.write(array, 0, BUFFER);
				}

				bos.flush();
				bos.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// 如果出现中文名字 则会出现乱码
		//ZipUtil.zip("F:\\Java\\1234", "F:\\Java\\1234.zip");
		// 解压缩之后又会还原回来
		//ZipUtil.unzip("F:\\Java\\1234.zip", "F:\\Java\\1234temp\\");
		
		// 如果出现中文名字 则会出现乱码
		ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("F:\\Java\\123.zip")));
		ZipUtil.zipFile(new File("F:\\Java\\123"), zos,	"");
		zos.close();
		ZipUtil.unZipFile("F:\\Java\\123.zip", "F:\\Java\\123temp");
		
		ZipUtil.zipFile("F:\\Java\\123", "F:\\123.zip");
		ZipUtil.unZipFile("F:\\123.zip", "F:\\123temp");
		
		
	}
}
