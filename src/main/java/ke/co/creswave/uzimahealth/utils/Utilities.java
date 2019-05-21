package ke.co.creswave.uzimahealth.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.gson.Gson;

public class Utilities {
	public static String getRequestAsString(Object object) {
		String request = null;
		Gson gson = new Gson();
		request = gson.toJson(object);
		return request;

	}

	public static <E> Collection<E> makeCollection(Iterable<E> iter) {
		Collection<E> list = new ArrayList<E>();
		for (E item : iter) {
			list.add(item);
		}
		return list;
	}

	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public static String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public static void compress(int compression, BufferedImage bi, String location, String fileType)
			throws FileNotFoundException, IOException {
		Iterator<ImageWriter> i = ImageIO.getImageWritersByFormatName(fileType);
		ImageWriter itemWriter = i.next();

		// Set the compression quality
		ImageWriteParam param = itemWriter.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(0.05f * compression);

		// Write the image to a file
		FileImageOutputStream out = new FileImageOutputStream(new File(location));
		itemWriter.setOutput(out);
		itemWriter.write(null, new IIOImage(bi, null, null), param);
		itemWriter.dispose();
		out.close();
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static String encryptPassword(String plainPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encryptedPassword = encoder.encode(plainPassword);
		return encryptedPassword;
	}

	public static String generatePin() {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		return id;
	}

	public static String getLikeString(String value) {
		value = "%" + value + "%";
		return value;
	}

	public static String formatNumber(String number) {
		if (number.startsWith("07")) {
			number = number.replaceFirst("07", "2547");
		} else if (number.startsWith("7")) {
			number = number.replaceFirst("7", "2547");
		} else if (number.startsWith("+254")) {
			number = number.replaceFirst("\\+254", "254");
		}
		return number;
	}

	public static int getUserType(HttpServletRequest request) {
		// userType
		Integer userType = (Integer) request.getAttribute("userType");
		if (userType == null)
			return 0;
		else
			return userType;
	}

	public static int getUserId(HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId");
		return userId;

	}

	public static boolean isUserAdmin(HttpServletRequest request) {
		Integer admin = (Integer) request.getAttribute("admin");
		if (admin == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static int generateRandomInteger() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(1000000);
		return randomNumber;
	}

	public static boolean IsBirthdayToday(Date dateOfBirth) {
		boolean isBirthToday = false;

		Calendar birthDayCal = Calendar.getInstance();
		birthDayCal.setTime(dateOfBirth);
		int birthDay = birthDayCal.get(Calendar.DAY_OF_MONTH);
		int birthMonth = birthDayCal.get(Calendar.MONTH);

		Calendar calToday = Calendar.getInstance();
		int today = calToday.get(Calendar.DAY_OF_MONTH);
		int month = calToday.get(Calendar.MONTH);

		if ((birthDay == today) && (birthMonth == month)) {
			return true;
		}

		return isBirthToday;
	}
}
