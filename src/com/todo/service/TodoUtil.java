package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "카테고리를 입력하세요 > \n");
		category = sc.nextLine();
		
		System.out.println("\n"
				+ "제목을 입력하세요 > \n");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("중복되는 이름이 있습니다");
			return;
		}
		
		sc.nextLine();
		System.out.println("\n"
				 + "설명을 입력하세요 > \n");
		desc = sc.nextLine().trim();
		
		System.out.println("\n"
				+ "마감일자를 입력하세요 > \n");
		
		due_date = sc.nextLine();
		
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("항목이 추가되었습니다");
	}

	public static void deleteItem(TodoList l) {
		int i = 0;
		
		System.out.println("\n"
				+ "삭제할 항목의 번호를 입력하세요\n"
				+ "\n");
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		
		
		for (TodoItem item : l.getList()) {
			i++;
			if (i == num) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		int i = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "수정할 항목의 번호 입력하세요\n"
				+ "\n");
		int num = sc.nextInt();
		if (l.getList().size() < num) {
			System.out.println("그런번호는 없읍니다.");
			return;
		}

		System.out.println("새로운 제목을 입력하세요");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("동일한 이름이 이미 있습니다");
			return;
		}
		sc.nextLine();
		System.out.println("새로운 설명을 입력하세요");
		String new_description = sc.nextLine().trim();
		
		System.out.println("새로운 카테코리를 입력하세요");
		String new_category = sc.nextLine().trim();
		
		System.out.println("새로운 마감일자를 입력하세요");
		String new_due_date = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			i++;
			if (i == num) {
				item.setTitle(new_title);
				item.setDesc(new_description);
				item.setDue_date(new_due_date);
				item.setCategory(new_category);
				break;
			}
		}

	}

	public static void listAll(TodoList l) {
		int i = 0;
		System.out.println("갯수 " +l.getList().size());
		for (TodoItem item : l.getList()) {
			i++;
			System.out.println(i + ". " + "카테고리 " + item.getCategory() + " 제목 : " + item.getTitle() + 
					" 설명 : " + item.getDesc() + " 생성된 시간 : " +item.getCurrent_date() + " 마감일자 :" + item.getDue_date());
		}
	}
	public static void saveList(TodoList l, String filename) {
		
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("할일저장됨!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void loadList(TodoList l, String filename) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				String category = st.nextToken();
				String due_date = st.nextToken();
				TodoItem a = new TodoItem(title, desc, category, due_date);
				a.setCurrent_date(date);
				l.addItem(a);
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("불러올 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void findItem(TodoList l) {
		int i = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("찾을 제목이나 내용을 입력하세요");
		String content = sc.nextLine();
		for (TodoItem item : l.getList()) {
			i++;
			if (item.getTitle().contains(content) || item.getDesc().contains(content)) {
				System.out.println(i + ". " + "카테고리 " + item.getCategory() + " 제목 : " + item.getTitle() + 
						" 설명 : " + item.getDesc() + " 생성된 시간 : " +item.getCurrent_date() + " 마감일자 :" + item.getDue_date());
			}
		}

		
	}
}
