package com.sosohanya.leveldiary.diary;

public class Diary {
	private Long id;
	private String diaryDate;
	private String contents;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDiaryDate() {
		return diaryDate;
	}
	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Diary(String diaryDate, String contents) {
		this.diaryDate = diaryDate;
		this.contents = contents;
	}
	
	public void update(Diary updateDiary) {
		this.diaryDate = updateDiary.diaryDate;
		this.contents = updateDiary.contents;
	}
	
	@Override
	public String toString() {
		return "Diary [id=" + id + ", diaryDate=" + diaryDate + ", contents=" + contents + "]";
	}
}
