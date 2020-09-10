package com.vetologic.ktap.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author VIKASH : 7 Sep, 2020
 *
 */
public class FileUploader {

	/**
	 * @author VIKASH : 7 Sep, 2020
	 *
	 * This API is Used to Create a Path to Upload Sound
	 *
	 * @param file
	 * @param subFolderName
	 * @return
	 */
	public boolean uploadSound(MultipartFile file,
			String subFolderName) {
		try {
			if ((file != null) && (!file.isEmpty())) {
				byte[] bytes = file.getBytes();

				Path rootPath = FileSystems.getDefault().getPath("")
						.toAbsolutePath();
				File dir = new File(rootPath + File.separator + "Uploads"
						+ File.separator + "Sounds" + File.separator
						+ subFolderName);

				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + "_"
								+ file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author VIKASH : 8 Sep, 2020
	 *
	 * This API is Used to Create a Path to Upload thumbline
	 *
	 * @param profilePicture
	 * @param soundNo
	 * @param string
	 * @return
	 */
	public boolean uploadThum(MultipartFile file, int soundNo, String subFolderName) {
		try {
			if ((file != null) && (!file.isEmpty())) {
				byte[] bytes = file.getBytes();

				Path rootPath = FileSystems.getDefault().getPath("")
						.toAbsolutePath();
				File dir = new File(rootPath + File.separator + "Uploads"
						+ File.separator + "Thumblines" + File.separator
						+ soundNo + File.separator + subFolderName);

				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + soundNo + "_"
								+ file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
