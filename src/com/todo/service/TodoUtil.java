package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		int difficulty, significance;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "카테고리를 입력하세요 > \n");
		category = sc.nextLine();
		
		System.out.println("\n"
				+ "제목을 입력하세요 > \n");
		
		title = sc.nextLine();
		if (l.isDuplicate(title)) {
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
		
		System.out.println("\n"
				+ "난이도를 입력하세요 > \n");
		difficulty = sc.nextInt();
		
		System.out.println("\n"
				+ "중요도를 입력하세요 > \n");
		significance = sc.nextInt();
		
		
		TodoItem t = new TodoItem(title, desc, category, due_date, difficulty, significance);
		if(l.addItem(t)>0)
			System.out.println("항목이 추가되었습니다");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 항목의 번호들을 입력하세요");
		
		String indexs = sc.nextLine();
		
		String[]index = indexs.split(" ");
		
		for(int i=0; i<index.length; i++) {
			l.deleteItem(Integer.parseInt(index[i]));
		}
		System.out.println("삭제되었습니다.");
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
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("동일한 이름이 이미 있습니다");
			return;
		}
		sc.nextLine();
		System.out.println("새로운 설명을 입력하세요");
		String new_desc = sc.nextLine().trim();
		System.out.println("새로운 카테코리를 입력하세요");
		String new_category = sc.nextLine().trim();
		System.out.println("새로운 마감일자를 입력하세요");
		String new_due_date = sc.nextLine().trim();
		System.out.println("새로운 난이도를 입력하세요");
		int new_difficulty = sc.nextInt();
		System.out.println("새로운 중요도를 입력하세요");
		int new_significance = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			i++;
			if (i == num) {
				//item.setTitle(new_title);
				//item.setDesc(new_desc);
				//item.setDue_date(new_due_date);
				//item.setCategory(new_category);
				break;
			}
		}
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_difficulty, new_significance);
		t.setId(i);
		if(l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");

	}

	public static void listAll(TodoList l) {
		System.out.printf("갯수 : %d\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.print(item.getId() + " : ");
			System.out.println(item.toString());
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
		System.out.println("load");
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				String due_date = st.nextToken();
				String difficulty = st.nextToken();
				String significance = st.nextToken();
				System.out.println(significance);
				TodoItem a = new TodoItem(title, desc, category, due_date, Integer.parseInt(difficulty), Integer.parseInt(significance));
				a.setCurrent_date(date);
				l.addItem(a);
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("불러올 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for (TodoItem item : l.getLists(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void findComp(TodoList l, int keyword) {
		int count=0;
		for (TodoItem item : l.getListss(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
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
	
	public static void find_cate(TodoList l) {
		System.out.println("키를 입력하세요");
		Scanner sc = new Scanner(System.in);
		String key = sc.nextLine();
		int gaesu = 0;
		for(int i=0; i<l.getList().size(); i++) {
			TodoItem item = l.getList().get(i);
			if(item.getCategory().contains(key)) {
				System.out.println(i + ". " + "카테고리 " + item.getCategory() + " 제목 : " + item.getTitle() + 
						" 설명 : " + item.getDesc() + " 생성된 시간 : " +item.getCurrent_date() + " 마감일자 :" + item.getDue_date());
				gaesu++;
			}
		}
		
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", gaesu);
	}
	public static void ls_cate(TodoList l) {
		List<String> list = new ArrayList<>();
		int count = 0;
		for(TodoItem item : l.getList()) {
			list.add(item.getCategory());
			count++;
		}
		
		List<String> cate = list.stream().distinct().collect(Collectors.toList());
		
		for(int i=0; i<cate.size(); i++) {
			System.out.print(cate.get(i));
			if(i != cate.size()-1)
				System.out.print(" \\ ");
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", cate.size());
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listAlls(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	public static void completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("완료할 항목의 번호들을 입력하세요");
		
		String indexs = sc.nextLine();
		
		String[]index = indexs.split(" ");
		
		for(int i=0; i<index.length; i++) {
			l.completeItem(Integer.parseInt(index[i]));
		}
	}
	public static void cal_mean(TodoList l) {
		int i = 0;
		int denominator = l.getList().size();
		int sum_difficulty = 0;
		double difficulty_avg = 0;
		int sum_significance = 0;
		double significance_avg = 0;
		for (TodoItem item : l.getList()) {
			i++;
			sum_difficulty += item.getDifficulty();
			sum_significance += item.getSignificance();
		}
		difficulty_avg = (float) sum_difficulty / denominator;
		significance_avg = (float) sum_significance / denominator;
		System.out.println("전체 중요도의 평균은 " + significance_avg + "입니다.");
		System.out.println("전체 난이도의 평균은 " +difficulty_avg + "입니다.");
		
	}
}
