package org.nerve.docker.monolithic;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * com.zeus.util
 * Created by zengxm on 2017/9/1.
 */
public class FileLoader {

	public static final Charset UTF8 = Charset.forName("utf-8");

	/**
	 * 优先从 jar 目录下加载文件（打包后，用外部文件替代classpath 下的文件）
	 *
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static String loadAsString(String name) throws IOException {
		return IOUtils.toString(load(name), UTF8);
	}

	/**
	 *
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static InputStream load(String name) throws IOException{
		// 优先从从jar同级目录加载
		File file = new File(name);

		Resource resource = new FileSystemResource(file);
		//config目录下还是找不到，那就直接用classpath下的
		if (!resource.exists()) {
			resource = new ClassPathResource(name);
		}
		return resource.getInputStream();
	}
}
