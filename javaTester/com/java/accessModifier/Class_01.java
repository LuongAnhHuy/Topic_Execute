package com.java.accessModifier;

public class Class_01 {
	
	// Access Modifier (private/ default/ protected/ public)
	// OOP - Encapsolution: ( Đóng gói che dấu dữ liệu )
	
	// Fields/ Variable
	
	// Private Fields
	private String name = "Automatin";
	
	// Default Fields
	String address = "Doi Can";
	
	// Protected Fields
	protected String city = "Ha Noi";
	
	// Public fields
	public String phone = "123456";
	
	// Method/ Function
	// getter/ setter (getter: để lấy dữ liệu; setter: để set dữ liệu)
	
	// Private Method
	private String getName() {
		return name;
	}
	
	private void setName (String name) {
		// Nếu trùng tên biến thì dùng biến this
		// Nếu không trùng tên biến thì không cần dùng biến this
		this.name = name;
	}
	
	// Default Method
	String getAddress () {
		return address;
	}
	
	void setAddress (String newAddress) {
		address = newAddress;
	}
	
	// Protected Method
	protected String getCity () {
		return city;
	}
	
	protected void setCity (String city) {
		this.city = city;
	}
	
	// Public Method
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	public static void main (String[] args) {
		Class_01 class01 = new Class_01();
		System.out.println(class01.getName());
		class01.setName("HuyLA");
		System.out.println(class01.getName());
	}
	
}
