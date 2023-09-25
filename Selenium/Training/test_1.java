package Training;

public class test_1 {

	public static void hanDoiViTri(int a, int b) {
	    int c = a;
	    a = b;
	    b = c;
	    System.out.println("Sau khi hoán đổi: " + "a = " + a + " b= " + b);
	  }
	  public static void main(String[] args) {
	    int a = 5, b = 3;
	    System.out.println("Trước khi hoán đổi: " + "a = " + a + " b= " + b);
	    hanDoiViTri(a, b);
	  }
}
