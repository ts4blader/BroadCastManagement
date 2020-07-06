package dto;

import java.rmi.Naming;

public class TheLoai {
	private String maTheLoai;		//1
	private String tenTheLoai;		//2
	

	public TheLoai(){
		tenTheLoai = "";
	}

	public String getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(String maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	public TheLoai(String maTheLoai, String tenTheLoai) {
		super();
		this.maTheLoai = maTheLoai;
		this.tenTheLoai = tenTheLoai;
	}

	public int getNumberOfCols () {
		return 2;
	}

	public String getStringBySTT (int i) {
		switch (i) {
		case 1: return maTheLoai;
		case 2: return tenTheLoai;
		}
		return null;
	}

	public int getIntBySTT (int i) {
		return -1;
	}

	public int[] getStringFields ()
	{
		return new int[] {1, 2};
	}

	public int[] getIntFields ()
	{
		return new int[0];
	}

	//to String

	@Override
	public String toString() {
		return tenTheLoai;
	}
}
