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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "제목을 입력하세요 > \n");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("중복되는 이름이 있습니다");
			return;
		}
		
		sc.nextLine();
		System.out.println("설명을 입력하세요 > ");
		desc = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("항목이 추가되었습니다");
	}

	public static void deleteItem(TodoList l) {
		
		System.out.println("\n"
				+ "삭제할 항목의 이름을 입력하세요\n"
				+ "\n");
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "수정할 항목의 제목을 입력하세요\n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 제목입니다");
			return;
		}

		System.out.println("새로운 제목을 입력하세요");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("동일한 이름이 이미 있습니다");
			return;
		}
		
		System.out.println("새로운 설명을 입력하세요");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("항목의 제목 : " + item.getTitle() + "  항목의 설명 : " + item.getDesc() + " 생성된 시간 : " +item.getCurrent_date());
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
				TodoItem a = new TodoItem(title, desc);
				a.setCurrent_date(date);
				l.addItem(a);
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("불러올 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
