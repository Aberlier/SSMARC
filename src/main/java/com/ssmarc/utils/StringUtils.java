/**
 * 公司名称  : 唐山启奥（Shinow）
 * 项目名称   : SHINOWIT2
 * JDK 版本号        : jdk1.6.2
 * 版本号                 : 1.0
 * 创建时间：2015年8月18日 上午9:13:16
 * 创建人：郭建
 **/
package com.ssmarc.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 类说明：字符串通用类
 * 
 * @author 郭建
 * @version Version 1.0
 * @since JDK 1.6
 */
public class StringUtils {
	/**
	 * 
	 * <p>
	 * 方法说明:判断字符串是否为空
	 * </p>
	 * 
	 * @param str
	 * @return
	 * 
	 */
	public static boolean isNull(String str) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 生成拼音字符串
	 * <p>
	 * 方法说明:讲中文转换成拼音字符串
	 * </p>
	 * 
	 * @param name
	 * @return
	 * 
	 */
	public static String generateSpellStrings(String name) throws Exception {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

		// UPPERCASE：大写 (ZHONG)
		// LOWERCASE：小写 (zhong)
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

		// WITHOUT_TONE：无音标 (zhong)
		// WITH_TONE_NUMBER：1-4数字表示英标 (zhong4)
		// WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常） (zhòng)
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		// WITH_V：用v表示ü (nv)
		// WITH_U_AND_COLON：用"u:"表示ü (nu:)
		// WITH_U_UNICODE：直接用ü (nü)
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		if (StringUtils.isNull(name)) {
			throw new Exception("姓名为空");
		}
		if (!RegexUtils.checkChinese(name)) {
			throw new Exception("姓名必须是中文");
		}
		char[] sd = name.toCharArray();
		StringBuffer sb = new StringBuffer();
		if (sd != null && sd.length > 0) {
			for (int i = 0; i < sd.length; i++) {
				String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(sd[i],
						format);
				if (pinyin != null && pinyin.length > 0) {
					sb.append(pinyin[0]);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 方法说明:将过长的字符串加上换行
	 * </p>
	 * 
	 * @param content
	 * @return
	 **/
	public static String joinStrByBr(String content) {
		String res = content.replaceAll("<[^>]*>", "");
		StringBuffer sb = new StringBuffer();
		int len = res.length();
		int x = 20;
		if (content.length() < x) {
			return content;
		}
		if (len >= x) {
			int size = len / x;
			int ls = size;
			if (len % x != 0) {
				ls += 1;
			}
			for (int i = 1; i <= ls; i++) {
				String l = null;
				if (len % x != 0 && i == ls) {
					l = res.substring(size * x + 1);
					sb.append(l);
				} else {
					if (i < ls) {
						l = res.substring((i - 1) * x, i * x);
						sb.append(l + "<br>");
					}
				}
			}
		}
		return sb.toString();
	}
	// public static void main(String[] sssss) throws Exception {
	// System.out.println(joinStrByBr(""));
	// }

}
