package com.zhenhr.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * word 模版替换类
 * 
 * @author guoyongshi
 *
 */
public class WordTemplateReplaceUtil {
	public class ParseObj {
		boolean keyStartFlag = false;
		boolean keyEndFlag = false;
		boolean keyInFlag = false;
		String key = "";

		public XWPFRun startRun;
		int startPos;
		public XWPFRun endRun;
		int endPos;

		List<XWPFRun> runList = null;
		public ParseObj() {

		}

		/**
		 * 重置对象
		 */
		public void reset() {
			keyStartFlag = false;
			keyEndFlag = false;
			keyInFlag = false;
			key = "";

			startRun = null;
			endRun = null;
			startPos = 0;
			endPos = 0;

			runList = null;
		}

		/**
		 * 新增
		 * 
		 * @param run
		 */
		public void addRunObj(XWPFRun run) {
			if (runList == null) {
				runList = new ArrayList<>();
			}

			for (XWPFRun old : runList) {
				if (old == run) {
					return;
				}
			}
			runList.add(run);

		}

	}

	/**
	 * 替换word
	 * 
	 * @param stream
	 */
	public XWPFDocument replaceWordTemplate(InputStream stream,
			Map<String, Object> params) {
		try {
			// 获得word的pack对象
			// 获得XWPFDocument对象
			XWPFDocument doc = new XWPFDocument(stream);

			// 处理页眉
			List<XWPFHeader> headers = doc.getHeaderList();
			for (XWPFHeader header : headers) {
				List<IBodyElement> bodys = header.getBodyElements();
				for (IBodyElement body : bodys) {
					this.readBody(body, params);
				}
				List<XWPFPictureData> picList = header.getAllPictures();
				for (XWPFPictureData pic : picList) {
					System.out.println(pic.getPictureType());
				}
			}

			// 输出doc body中包含的元素个数
			List<IBodyElement> bodys = doc.getBodyElements();
			for (IBodyElement body : bodys) {
				this.readBody(body, params);
			}

			// doc.close();
			// stream.close();
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void readBody(IBodyElement body, Map<String, Object> params) {
		if (body.getElementType() == BodyElementType.TABLE) { // 表格
			XWPFTable table = (XWPFTable) body;
			this.readTable(table, params);
		} else if (body.getElementType() == BodyElementType.PARAGRAPH) {// 段落
			XWPFParagraph para = (XWPFParagraph) body;
			this.readParagraph(para, params);
		}
	}
	private void readTable(XWPFTable table, Map<String, Object> params) {
		List<XWPFTableRow> rows = table.getRows();
		for (XWPFTableRow row : rows) {
			List<XWPFTableCell> cells = row.getTableCells();

			for (XWPFTableCell cell : cells) {
				List<IBodyElement> list = cell.getBodyElements();
				for (IBodyElement body : list) {
					this.readBody(body, params);
				}
			}
		}
	}

	private void readParagraph(XWPFParagraph para, Map<String, Object> params) {
		// String text = para.getText();
		// System.out.println(text);
		// System.out.println(para.getText());

		ParseObj po = new ParseObj();
		List<XWPFRun> runs = para.getRuns();
		for (int i = 0; i < runs.size(); i++) {
			XWPFRun run = runs.get(i);
			String text = run.getText(0);
			if (text == null || text.length() == 0 || text.trim() == "") {
				continue;
			}

			// 解析
			this.handleOneRun(run, po, params);
		}
	}

	// 处理一个run
	private void handleOneRun(XWPFRun run, ParseObj po, Map<String, Object> params) {
		String text = run.getText(0);
		for (int m = 0; m < text.length(); m++) {
			char ch = text.charAt(m);
			if (ch == '{') {
				if (po.keyStartFlag) { // 发现了开始标记
					po.keyInFlag = true;
					po.startRun = run;
					po.startPos = m - 1;
					po.addRunObj(run);
				}
				po.keyStartFlag = true;
			} else if (ch == '}') {
				if (po.keyEndFlag) { // 发现了结束标记
					if (po.key.length() > 0) {
						String replaceStr = this.replaceStr(po.key, params);
						System.out.println(po.key + ":" + replaceStr);

						po.endPos = m;
						po.endRun = run;
						this.replaceRun(replaceStr, po);

						po.reset();
						this.handleOneRun(run, po, params);
						return;
					}
					po.keyStartFlag = false;
					po.keyInFlag = false;
					po.keyEndFlag = false;
				} else {
					if (po.keyInFlag) {
						po.keyEndFlag = true;
					} else {
						po.keyStartFlag = false;
						po.keyInFlag = false;
					}
				}

			} else {
				if (po.keyInFlag) {
					po.key += ch;
					po.addRunObj(run);
				}
			}

		}
	}

	// 替换一个内容
	private void replaceRun(String replaceStr, ParseObj po) {
		String oldText = po.startRun.getText(0);
		String newText = "";
		if (po.startPos > 0) { // '{{'
			newText += oldText.substring(0, po.startPos);
		}
		newText += replaceStr;

		// 处理结束
		if (po.endRun == po.startRun) { // 同样的
			if (po.endPos < oldText.length() - 1) {
				newText += oldText.substring(po.endPos + 1);
			}
			po.startRun.setText(newText, 0);
		} else {// 不同的
			po.startRun.setText(newText, 0);

			// 中间的全部设空
			for (int i = 1; i < po.runList.size(); i++) {
				XWPFRun run = po.runList.get(i);
				if (run != po.endRun) {
					run.setText("", 0);
				}
			}
			// 处理最后的'}}'
			String endText = po.endRun.getText(0);
			String newEndText = "";
			if (po.endPos < endText.length() - 1) {
				newEndText += endText.substring(po.endPos + 1);
			}
			po.endRun.setText(newEndText, 0);
		}

	}

	// 查找可以替换的关键字
	private String replaceStr(String key, Map<String, Object> params) {
		for (Entry<String, Object> entry : params.entrySet()) {
			if (key.equals(entry.getKey())) {
				return String.valueOf(entry.getValue());
			}
		}

		return " ";
	}

	// 主函数
	public static void main(String[] args) {
		WordTemplateReplaceUtil importService = new WordTemplateReplaceUtil();

		try {
			InputStream stream = new FileInputStream(
					"/Volumes/work/test/template_2.docx");

			Map<String, Object> params = new HashMap<>();
			params.put("companyName", "测试公司");
			params.put("employeeName", "史国勇");
			params.put("bodyId", "110105198008176122");
			params.put("contractStart", "2016-12-15");
			params.put("contractEnd", "2017-12-15");

			XWPFDocument doc = importService.replaceWordTemplate(stream, params);
			// WordUtil.insertPicture(doc, "",null, width, height);

			stream.close();

			File file = new File("/Volumes/work/test/template_new.docx");
			if (file.exists()) {
				file.delete();
			}

			FileOutputStream outputStream = new FileOutputStream(
					"/Volumes/work/test/template_new.docx");
			doc.write(outputStream);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
