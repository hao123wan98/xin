package com.zhenhr.tools;

public class EmojiFilter {

	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (source == null || source.isEmpty()) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		boolean ret = (codePoint == 0x0) || (codePoint == 0x9)
				|| (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
		return !ret;
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (source == null || source.isEmpty()) {
			return "";
		}

		StringBuilder buf = null;
		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (!isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
				buf.append(codePoint);
			}
		}
		if (buf == null) {
			return "";
		} else {
			return buf.toString();
		}
	}

	public static void main(String[] args) {
		String name = EmojiFilter.filterEmoji("s👋sss👄");
		System.out.println(name);
	}
}
