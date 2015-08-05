package com.jeff.fjb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.jeff.fjb.dal.entity.PracticeEntity;
import com.jeff.fjb.dal.service.PracticeService;

public class GenerateQuestion {
	public static void main(String[] args) throws IOException {
		PracticeService service = new PracticeService();
		for (int type = 1; type <= 4; type++) {
			File file = new File(type + ".txt");
			Reader reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			int part = 1;
			StringBuffer stringBuffer = new StringBuffer();
			StringBuffer answerTemp = new StringBuffer();
			int id = 0;
			String question=null;
			String answer=null;
			String A=null;
			String B=null;
			String C=null;
			String D=null;
			String E=null;
			char answerIndex = ' ';
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				char c = (char) tempchar;
				if (c != '\r' && c != '\n') {
					if (part == 1) {
						stringBuffer.append(c);
						if (c == '.'){
							String tempString = stringBuffer.toString().trim();
							id = Integer.valueOf(tempString.substring(1, tempString.length() - 1));
							stringBuffer = new StringBuffer();
							part++;
							continue;
						}
					}
					if (part == 2) {
						if (c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E') {
							if (c != '.') 
								stringBuffer.append(c);
							else {
								answerIndex = answerTemp.charAt(answerTemp.length()-1);
								answerTemp.deleteCharAt(answerTemp.length()-1);
								answer = answerTemp.toString();
								question = stringBuffer.deleteCharAt(stringBuffer.length()-1).toString().trim().replace("_", "___").replace(" ", "___");
								part ++;
								stringBuffer = new StringBuffer();
								answerTemp = new StringBuffer();
								continue;
							}
						} else {
							answerTemp.append(c);
							stringBuffer.append("_");
						}
					}
					if (part == 3) {
						if (c == '.') {
							char newAnswerIndex = stringBuffer.charAt(stringBuffer.length()-1);
							stringBuffer.deleteCharAt(stringBuffer.length()-1);
							if (answerIndex == 'A')
								A = stringBuffer.toString().trim();
							if (answerIndex == 'B')
								B = stringBuffer.toString().trim();
							if (answerIndex == 'C')
								C = stringBuffer.toString().trim();
							if (answerIndex == 'D')
								D = stringBuffer.toString().trim();
							if (answerIndex == 'E')
								E = stringBuffer.toString().trim();
							answerIndex = newAnswerIndex;
							stringBuffer = new StringBuffer();
							continue;
						} else if (c == '#') {
							if (answerIndex == 'A')
								A = stringBuffer.toString().trim();
							if (answerIndex == 'B')
								B = stringBuffer.toString().trim();
							if (answerIndex == 'C')
								C = stringBuffer.toString().trim();
							if (answerIndex == 'D')
								D = stringBuffer.toString().trim();
							if (answerIndex == 'E')
								E = stringBuffer.toString().trim();
							stringBuffer = new StringBuffer();
							stringBuffer.append(c);
							answerTemp = new StringBuffer();
							System.out.println(id);
							System.out.println(question);
							System.out.println(answer);
							System.out.println(A);
							System.out.println(B);
							System.out.println(C);
							System.out.println(D);
							System.out.println(E);
							PracticeEntity entity = new PracticeEntity(id, question, answer, type, A, B, C, D, E);
							service.insertPracticeEntity(entity);
							id = 0;
							question=null;
							answer=null;
							A=null;
							B=null;
							C=null;
							D=null;
							E=null;
							answerIndex = ' ';
							part = 1;
							continue;
						} else 
							stringBuffer.append(c);
					}
				}
			}
			reader.close();
		}
	}
}
