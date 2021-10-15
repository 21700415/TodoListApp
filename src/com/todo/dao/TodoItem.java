package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int id;
    private int is_completed;
    private int difficulty;
    private int significance;

    public TodoItem(String title, String desc, String category, String due_date, int difficulty, int significance){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.category=category;
        this.due_date=due_date;
        this.difficulty=difficulty;
        this.significance=significance;
        this.is_completed=0;
    }
    
    public TodoItem(String title, String desc, String category, String due_date, String difficulty, String significance){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.category=category;
        this.due_date=due_date;
        this.difficulty=Integer.parseInt(difficulty);
        this.significance=Integer.parseInt(significance);
        this.is_completed=0;
    }
    public int getSignificance() {
        return this.significance;
    }

    public void setSignificance(int significance) {
        this.significance = significance;
    }
    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDue_date() {
    	return due_date;
    }
    
    public void setDue_date(String due_date) {
    	this.due_date = due_date;
    }
    public void setId(int id) {
		this.id = id;
		
	}
    public int getId() {
		return id;
	}
    public void setIs_completed(int is_completed) {
    	this.is_completed = is_completed;
    }
    public int getIs_completed() {
    	return is_completed;
    }
    
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + current_date + "##" + due_date + "##" + is_completed + "##" + this.difficulty + "##" + this.significance +"\n";
    }
    
    @Override
    public String toString() {
    	if (is_completed == 0){
    		return "[" + category + "]" + title +  " - " +desc + " - " + current_date + " - " + due_date + " 중요도: " + this.difficulty + " 난이도: " + this.significance;
    	}
    	else {
    		return "[" + category + "]" + title + "[V]" + " - " +desc + " - " + current_date + " - " + due_date  + " 중요도: " + this.difficulty + " 난이도: " + this.significance;
    	}
    	
    }

	

	
}
