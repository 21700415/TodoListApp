package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("새로운 항목을 추가하려면 add 입력");
        System.out.println("기존의 항목을 삭제하려면 del 입력");
        System.out.println("기존의 항목을 수정하려면 edit 입력");
        System.out.println("현재의 항목을 조회하려면 ls 입력");
        System.out.println("현재의 항목을 이름순으로 오름차순 정렬을 하려면 ls_name_asc 입력");
        System.out.println("현재의 항목을 이름순으로 내림차순 정렬을 하려면 ls_name_desc 입력");
        System.out.println("현재의 항목을 날짜순으로 정렬을 하려면 ls_date 입력");
        System.out.println("프로그램을 종료하려면 exit 입력");
    }
}
